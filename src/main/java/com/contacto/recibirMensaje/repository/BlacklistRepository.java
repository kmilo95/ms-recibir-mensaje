package com.contacto.recibirMensaje.repository;

import com.contacto.recibirMensaje.entity.Blacklist;
import com.contacto.recibirMensaje.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {

	List<Blacklist> findAllByNumeroAndStatusIdAndUserId(@Param("numero") String numero, @Param("statusId") Long statusId, @Param("userId") Long userId);
}
