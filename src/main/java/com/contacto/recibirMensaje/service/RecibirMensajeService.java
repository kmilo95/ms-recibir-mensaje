package com.contacto.recibirMensaje.service;

import com.contacto.recibirMensaje.dto.*;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

public interface RecibirMensajeService {

	public Mono<RecibirResponse> adicionarRespuesta(RecibirRequest credencial);

	public Mono<ConsultarResponse> consultar(ConsultarRequest credencial);

	public Mono<RecibirResponse> enviarMensaje(EnviarMensajeRequest credencial, HttpServletRequest header);
}
