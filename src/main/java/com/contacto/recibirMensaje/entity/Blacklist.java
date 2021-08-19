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
@Table(name = "blacklist")
public class Blacklist {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "numero")
    private String numero;

    @Column(name = "date_insert")
    private Date dateInsert;

    @Column(name = "date_update")
    private Date dateUpdate;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "archivo_id")
    private Long archivoId;

}
