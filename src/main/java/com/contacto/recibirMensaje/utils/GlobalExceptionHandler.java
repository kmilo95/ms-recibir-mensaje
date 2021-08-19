package com.contacto.recibirMensaje.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> notValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		List<String> errorMsgList = new ArrayList();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorMsgList.add(fieldError.getDefaultMessage());
		}

		ExceptionResponse response = ExceptionResponse.builder().res(MensajeContantes.mensajeBad).validaciones(errorMsgList).build();
		log.info(errorMsgList.toString());
		return new ResponseEntity<Object>(response,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
}
