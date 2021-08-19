package com.contacto.recibirMensaje.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servicios")
public class Servicios {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tiposervicio")
    private Integer tiposervicio;

    @Column(name = "maximo")
    private Long maximo;

    @Column(name = "acumula")
    private Integer acumula;
}
