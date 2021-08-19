package com.contacto.recibirMensaje.repository;

import com.contacto.recibirMensaje.entity.Servicios;
import com.contacto.recibirMensaje.entity.UsuariosIps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiciosRepository extends JpaRepository<Servicios, Long> {

	Servicios findAllById(@Param("id") Long id);
}
