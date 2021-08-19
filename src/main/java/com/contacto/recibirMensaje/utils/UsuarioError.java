package com.contacto.recibirMensaje.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class UsuarioError {

	@ResponseBody
	@ExceptionHandler(PerfilException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String perfilNoEncontrado(PerfilException ex) throws JsonProcessingException {
		List<String> errorMsgList = new ArrayList();
		errorMsgList.add("Usuario y clave erronea");
		ExceptionResponse response = ExceptionResponse.builder().res(MensajeContantes.mensajeBad).validaciones(errorMsgList).build();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(response);
	}
}
