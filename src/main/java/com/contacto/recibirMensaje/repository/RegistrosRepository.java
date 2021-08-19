package com.contacto.recibirMensaje.repository;

import com.contacto.recibirMensaje.entity.Carries;
import com.contacto.recibirMensaje.entity.Registros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrosRepository extends JpaRepository<Registros, Long> {
}
