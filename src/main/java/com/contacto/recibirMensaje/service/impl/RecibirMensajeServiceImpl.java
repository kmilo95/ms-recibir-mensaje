package com.contacto.recibirMensaje.service.impl;

import com.contacto.recibirMensaje.dto.*;
import com.contacto.recibirMensaje.entity.*;
import com.contacto.recibirMensaje.repository.*;
import com.contacto.recibirMensaje.service.RecibirMensajeService;
import com.contacto.recibirMensaje.utils.MensajeContantes;
import com.contacto.recibirMensaje.utils.PerfilException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class RecibirMensajeServiceImpl implements RecibirMensajeService {

	@Autowired
	RespuestaRepository repository;

	@Autowired
	UsuariosRepository repositoryUsuarios;

	@Autowired
	UsuariosIpsRepository repositoryUsuariosIp;

	@Autowired
	BasesRepository repositoryBases;

	@Autowired
	BlacklistRepository repositoryBlackList;

	@Autowired
	CarriesRepository repositoryCarries;

	@Autowired
	ServiciosRepository repositoryServicios;

	@Autowired
	RegistrosRepository repositoryRegistros;

	private final String LOCALHOST_IPV4 = "127.0.0.1";

	private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

	@Override
	public Mono<RecibirResponse> adicionarRespuesta(RecibirRequest credencial) {
		return Mono.justOrEmpty(repository.findAllByIdMensaje(credencial.getId()))
				.flatMap(item -> {
					if(item.isEmpty()){
						String str = credencial.getDate();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

						Respuesta id = repository.save(Respuesta.builder().mensaje(credencial.getSms()).idMensaje(credencial.getId())
											.fecha(dateTime).idCanal(2).source(credencial.getSc()).numero(credencial.getOriginator()).reference1(credencial.getReference_1()).build());
						return Mono.just(RecibirResponse.builder().res(MensajeContantes.mensajeOK).mensaje(MensajeContantes.mensajeOkRespuesta+id.getId()).build());
					}else{
						return Mono.just(RecibirResponse.builder().res(MensajeContantes.mensajeBad).mensaje(MensajeContantes.mensajeBadrespuesta).build());
					}
				});
	}

	@Override
	public Mono<ConsultarResponse> consultar(ConsultarRequest credencial) {
		return Mono.justOrEmpty(repository.findAllByNumero(credencial.getNumeroTelefono()))
						.flatMap(item -> {
							List<ConsultarDetalleResponse> lista = new ArrayList<>();
							item.forEach(i -> {
								lista.add(ConsultarDetalleResponse.builder().mensaje(i.getMensaje()).idCanal(i.getIdCanal()).source(i.getSource()).mensaje(i.getMensaje()).fecha(i.getFecha()).idRespuesta(i.getId()).reference1(i.getReference1()).build());
							});
							return Mono.just(ConsultarResponse.builder().id(item.size()>0?1:-1).numero(credencial.getNumeroTelefono()).registros(item.size()).respuesta(lista).build());
						});
	}

	@Override
	public Mono<RecibirResponse> enviarMensaje(EnviarMensajeRequest credencial, HttpServletRequest header) {
		return Mono.justOrEmpty(repositoryUsuarios.findAllByUsuarioAndClave(credencial.getUsuario(), Base64.getEncoder().encodeToString(credencial.getClave().getBytes(StandardCharsets.UTF_8))))
				.switchIfEmpty(Mono.error(new PerfilException()))
				.flatMap(item -> {
					String ip = getIp(header);
					Bases idBase = null;
					Optional<UsuariosIps> usuarioIp = repositoryUsuariosIp.findAllByIdusuario(item.getId());
					if(usuarioIp.isPresent()){
						if(!repositoryUsuariosIp.findAllByIdusuarioAndIp(item.getId(),ip).isPresent()){
							return Mono.just(RecibirResponse.builder().res(MensajeContantes.mensajeBad).mensaje(MensajeContantes.mensajeBadIpRegistrada).build());
						}
					}

					log.info("ip remota: "+ip);

					List<Bases> listBases = repositoryBases.findAllByNombreContainingAndIdusuarioAndFechaGreaterThanEqual("ws",item.getId(),new Date());
					if(listBases.size()>1){
						listBases.forEach(i -> {
							i.setRegistros(i.getRegistros()+1);
						});
						repositoryBases.saveAll(listBases);
					}else{
						Date localDate = new Date();
						 idBase = repositoryBases.save(Bases.builder().idempresa(item.getIdempresa()).idusuario(item.getId())
								.nombre("ws_"+item.getIdempresa()+"_"+item.getId()+"_"+localDate).fecha(new Date())
								.registros(1L).ip(ip).build());
					}

					List<Blacklist> listBlack = repositoryBlackList.findAllByNumeroAndStatusIdAndUserId(credencial.getNumero(),1L,item.getId());
					if(listBlack.size()>1){
						return Mono.just(RecibirResponse.builder().res(MensajeContantes.mensajeBad).mensaje(MensajeContantes.mensajeListaNegra).build());
					}

					log.info("numero prefijo: "+credencial.getNumero().substring(0,3));
					List<Carries> carries = repositoryCarries.findAllByPrefijosIsLike("%"+credencial.getNumero().substring(0,3)+"%");
					log.info("tamaño {}",carries.size());
					if(carries.size()>0){
						Long consumo = item.getEnviados()+item.getPendientes();
						log.info("consumo {}",consumo);
						Servicios servicios = repositoryServicios.findAllById(item.getIdservicio());
						log.info("servicios {}",servicios.getMaximo());
						Long disponible = servicios.getMaximo() - consumo;
						log.info("disponble {}",disponible);
						if(disponible >0L){
							String[] preferencias = item.getPreferencias().split(",");
							log.info("preferencias");
							log.info("tamaño split {} ",preferencias.length);
							Long idCanal = Long.parseLong(preferencias[Integer.parseInt(carries.get(0).getCodigo())-1]);
							Registros idRegistro = repositoryRegistros.save(Registros.builder().idbase(idBase.getId()).nota(credencial.getNota())
									.estado("2").orden(1).cargue("ws").fechacargue(LocalDateTime.now())
									.idcarrie(carries.get(0).getId()).idcanal(idCanal).fechaprogramado(credencial.getProgramado())
									.numero(credencial.getNumero()).mensaje(credencial.getMensaje()).build());
							return Mono.just(RecibirResponse.builder().res(MensajeContantes.mensajeOK).mensaje(MensajeContantes.mensajeOkMensaje+": "+idRegistro.getId()).build());
						}else{
							return Mono.just(RecibirResponse.builder().res(MensajeContantes.mensajeBad).mensaje(MensajeContantes.mensajeSinCupo).build());
						}
					}else{
						return Mono.just(RecibirResponse.builder().res(MensajeContantes.mensajeBad).mensaje(MensajeContantes.mensajeNoOperador).build());
					}
				});
	}

	public String getIp(HttpServletRequest request){
		String ipAddress = request.getHeader("X-Forwarded-For");
		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}

		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}

		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					ipAddress = inetAddress.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}

		if(!StringUtils.isEmpty(ipAddress)
				&& ipAddress.length() > 15
				&& ipAddress.indexOf(",") > 0) {
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
		}

		return ipAddress;
	}


}
