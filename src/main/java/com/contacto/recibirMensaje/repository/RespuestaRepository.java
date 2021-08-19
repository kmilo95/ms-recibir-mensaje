package com.contacto.recibirMensaje.repository;

import com.contacto.recibirMensaje.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {

	List<Respuesta> findAllByIdMensaje(@Param("idMensaje") String idMensaje);

	List<Respuesta> findAllByNumero(@Param("numero") String numero);
}
