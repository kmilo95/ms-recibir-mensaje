package com.contacto.recibirMensaje.repository;

import com.contacto.recibirMensaje.entity.Bases;
import com.contacto.recibirMensaje.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BasesRepository extends JpaRepository<Bases, Long> {

	List<Bases> findAllByNombreContainingAndIdusuarioAndFechaGreaterThanEqual(@Param("nombre") String nombre, @Param("idusuario") Long idUsuario, @Param("fecha") Date fecha);
}

