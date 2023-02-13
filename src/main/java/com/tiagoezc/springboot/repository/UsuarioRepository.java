package com.tiagoezc.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagoezc.springboot.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
