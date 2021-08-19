package com.contacto.recibirMensaje.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registros")
public class Registros {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "idbase")
    private Long idbase;

    @Column(name = "numero")
    private String numero;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fechacargue")
    private LocalDateTime fechacargue;

    @Column(name = "fechaenvio")
    private LocalDateTime fechaenvio;

    @Column(name = "respuesta")
    private String respuesta;

    @Column(name = "idcanal")
    private Long idcanal;

    @Column(name = "nota")
    private String nota;

    @Column(name = "idcarrie")
    private Long idcarrie;

    @Column(name = "cargue")
    private String cargue;

    @Column(name = "fechaprogramado")
    private LocalDateTime fechaprogramado;

    @Column(name = "flash")
    private Long flash;

    @Column(name = "correo")
    private String correo;
}
