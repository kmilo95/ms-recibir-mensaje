package com.contacto.recibirMensaje.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
public class ConsultarDetalleResponse {

	private Integer idRespuesta;

	private Integer idCanal;

	private String source;

	private String mensaje;

	private LocalDateTime fecha;

	private String reference1;
}
