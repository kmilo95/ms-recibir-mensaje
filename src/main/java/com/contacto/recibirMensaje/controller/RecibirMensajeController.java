package com.contacto.recibirMensaje.controller;

import com.contacto.recibirMensaje.dto.*;
import com.contacto.recibirMensaje.service.RecibirMensajeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Clase encargada de  las peticiones rest/http
 * @author Camilo Cárdenas - Everis Colombia - para Banco Popular
 * @date 26/05/2021
 * @since 1.0
 */

@RestController
@RequestMapping("${application.contacto.api.path}")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@Api(tags = "contacto-gestion")
public class RecibirMensajeController {

	@Autowired
	RecibirMensajeService _service;

	@PostMapping(value = "/adicionarrespuesta", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Adicionar respuesta", response = RecibirRequest.class, httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecutó satisfactoriamente."),
			@ApiResponse(code = 400, message = "El cliente envió datos incorrectos.", response = Throwable.class),
			@ApiResponse(code = 500, message = "Error inesperado.", response = Throwable.class) })
	public Mono<RecibirResponse> recibirMensje(@Valid @RequestBody RecibirRequest credencial) {
		return _service.adicionarRespuesta(credencial);
	}

	@PostMapping(value = "/consultarxtelefono", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Consultar x telefono", response = ConsultarRequest.class, httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecutó satisfactoriamente."),
			@ApiResponse(code = 400, message = "El cliente envió datos incorrectos.", response = Throwable.class),
			@ApiResponse(code = 500, message = "Error inesperado.", response = Throwable.class) })
	public Mono<ConsultarResponse> consultarxNumero(@Valid @RequestBody ConsultarRequest credencial) {
		return _service.consultar(credencial);
	}

	@PostMapping(value = "/enviarmensaje", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "enviar mensaje", response = EnviarMensajeRequest.class, httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecutó satisfactoriamente."),
			@ApiResponse(code = 400, message = "El cliente envió datos incorrectos.", response = Throwable.class),
			@ApiResponse(code = 500, message = "Error inesperado.", response = Throwable.class) })
	public Mono<RecibirResponse> enviarMensaje(@RequestBody @Validated EnviarMensajeRequest credencial, HttpServletRequest encabezado) {
		return _service.enviarMensaje(credencial,encabezado);
	}
}
