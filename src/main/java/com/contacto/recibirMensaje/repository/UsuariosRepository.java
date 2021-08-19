package com.contacto.recibirMensaje.repository;

import com.contacto.recibirMensaje.entity.Respuesta;
import com.contacto.recibirMensaje.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {


	Usuarios findAllByUsuarioAndClave (@Param("usuario") String usuario,@Param("clave") String clave);
}
