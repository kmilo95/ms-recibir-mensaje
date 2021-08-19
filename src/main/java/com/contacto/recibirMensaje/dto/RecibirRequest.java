package com.contacto.recibirMensaje.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class RecibirRequest {


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private String date;

	@NotNull(message = "El campo sc no puede ser nulo")
	@NotEmpty(message = "El campo sc no puede ser vacio")
	private String sc;

	@NotNull(message = "El campo originator no puede ser nulo")
	@NotEmpty(message = "El campo originator no puede ser vacio")
	private String originator;

	@NotNull(message = "El campo id no puede ser nulo")
	@NotEmpty(message = "El campo id no puede ser vacio")
	private String id;

	@NotNull(message = "El campo sms no puede ser nulo")
	@NotEmpty(message = "El campo sms no puede ser vacio")
	private String sms;

	@NotNull(message = "El campo reference_1 no puede ser nulo")
	@NotEmpty(message = "El campo reference_1 no puede ser vacio")
	private String reference_1;
}
