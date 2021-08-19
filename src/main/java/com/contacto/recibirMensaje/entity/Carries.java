package com.contacto.recibirMensaje.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carries")
public class Carries {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "prefijos")
    private String prefijos;

    @Column(name = "codigo")
    private String codigo;

}
