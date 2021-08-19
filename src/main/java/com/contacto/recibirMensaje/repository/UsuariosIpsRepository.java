package com.contacto.recibirMensaje.repository;

import com.contacto.recibirMensaje.entity.Usuarios;
import com.contacto.recibirMensaje.entity.UsuariosIps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuariosIpsRepository extends JpaRepository<UsuariosIps, Long> {

	Optional<UsuariosIps> findAllByIdusuario(@Param("idUsuario") Long idusuario);

	Optional<UsuariosIps> findAllByIdusuarioAndIp(@Param("idUsuario") Long idusuario, @Param("ip") String ip);
}
