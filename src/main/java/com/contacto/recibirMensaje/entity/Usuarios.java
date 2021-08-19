package com.contacto.recibirMensaje.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "clave")
    private String clave;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "idempresa")
    private Long idempresa;

    @Column(name = "idperfil")
    private Long idperfil;

    @Column(name = "estado")
    private Long estado;

    @Column(name = "correos")
    private String correos;

    @Column(name = "idservicio")
    private Long idservicio;

    @Column(name = "concatena")
    private String concatena;

    @Column(name = "logueado")
    private Long logueado;

    @Column(name = "enviados")
    private Long enviados;

    @Column(name = "pendientes")
    private Long pendientes;

    @Column(name = "preferencias")
    private String preferencias;

    @Column(name = "fechainicio")
    private java.sql.Date fechainicio;

    @Column(name = "historicos")
    private Long historicos;

    @Column(name = "quantity_temp")
    private Long quantityTemp;

    @Column(name = "mes_anterior")
    private Long mesAnterior;

}
