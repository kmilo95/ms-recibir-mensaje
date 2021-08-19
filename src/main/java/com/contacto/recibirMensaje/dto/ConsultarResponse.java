package com.contacto.recibirMensaje.dto;

import com.contacto.recibirMensaje.entity.Respuesta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ConsultarResponse {

	private Integer id;

	private String numero;

	private Integer registros;

	List<ConsultarDetalleResponse> respuesta;
}
