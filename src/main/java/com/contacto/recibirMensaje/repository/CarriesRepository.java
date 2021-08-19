package com.contacto.recibirMensaje.repository;

import com.contacto.recibirMensaje.entity.Carries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarriesRepository extends JpaRepository<Carries, Long> {

	List<Carries> findAllByPrefijosIsLike(@Param("prefijos") String prefijos);
}
