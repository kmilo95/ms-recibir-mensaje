package com.contacto.recibirMensaje.utils;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class ExceptionResponse {

	private String res;

	private List<String> validaciones;

}
