package com.rentcar.api.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsername(String login);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
