package com.contacto.recibirMensaje.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecibirResponse {

	private String res;

	private String mensaje;
}
