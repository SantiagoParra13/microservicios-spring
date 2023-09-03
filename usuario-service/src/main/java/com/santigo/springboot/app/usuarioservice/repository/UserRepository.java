package com.santigo.springboot.app.usuarioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santigo.springboot.app.usuarioservice.entities.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer> {
    
}
