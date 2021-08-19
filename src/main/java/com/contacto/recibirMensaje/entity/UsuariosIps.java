package com.contacto.recibirMensaje.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios_ips")
public class UsuariosIps {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "idusuario")
    private Long idusuario;

    @Column(name = "fecha")
    private java.sql.Date fecha;

    @Column(name = "idautoriza")
    private Long idautoriza;

    @Column(name = "ip")
    private String ip;

}
