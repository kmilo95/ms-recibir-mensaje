package com.contacto.recibirMensaje.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public",name = "respuestas")
public class Respuesta {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Integer id;

	@Column(name = "idcanal")
	private Integer idCanal;

	@Column(name = "source")
	private String source;

	@Column(name = "numero")
	private String numero;

	@Column(name = "mensaje")
	private String mensaje;

	@Column(name = "fecha")
	private LocalDateTime fecha;

	@Column(name = "idmensaje")
	private String idMensaje;

	@Column(name = "reference_1")
	private String reference1;
}
