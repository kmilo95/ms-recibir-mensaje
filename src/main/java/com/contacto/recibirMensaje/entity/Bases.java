package com.contacto.recibirMensaje.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bases")
public class Bases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "idempresa")
    private Long idempresa;

    @Column(name = "idusuario")
    private Long idusuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "registros")
    private Long registros;

    @Column(name = "errores")
    private Long errores;

    @Column(name = "archusuario")
    private String archusuario;

    @Column(name = "archconsolidado")
    private String archconsolidado;

    @Column(name = "ip")
    private String ip;

    @Column(name = "archoriginal")
    private String archoriginal;

    @Column(name = "enviados")
    private Long enviados;

    @Column(name = "estado")
    private String estado;

    @Column(name = "dobles")
    private Long dobles;

    @Column(name = "path")
    private String path;

    @Column(name = "conteo")
    private Long conteo;
}
