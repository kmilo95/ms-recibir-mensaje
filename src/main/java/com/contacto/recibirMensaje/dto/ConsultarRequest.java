package com.contacto.recibirMensaje.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
public class ConsultarRequest {

	public ConsultarRequest() {
	}

	@NotNull(message = "El campo numeroTelefono no puede ser nulo")
	@NotEmpty(message = "El campo numeroTelefono no puede ser vacio")
	private String numeroTelefono;
}
