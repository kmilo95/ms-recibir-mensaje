package com.contacto.recibirMensaje.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class EnviarMensajeRequest {

	public EnviarMensajeRequest() {
	}

	@NotEmpty(message = "El numero no puede ser vacio")
	@Pattern(regexp = "^3(\\d{9})$", flags = Pattern.Flag.UNICODE_CASE, message = "El numero no tiene el formato correcto")
	private String numero;

	@NotEmpty(message = "El numero no puede ser vacio")
	@Size(min = 1, max = 160, message
			= "mensaje es bastante largo")
	private String mensaje;

	private String nota;

	private String usuario;

	private String clave;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime programado;
}
