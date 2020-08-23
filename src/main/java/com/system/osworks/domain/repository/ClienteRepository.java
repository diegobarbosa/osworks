package com.system.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.osworks.domain.model.Cliente;

// Define um componente do Spring
@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
	List<Cliente> findByNomeContaining(String nome);
	
	Cliente findByEmail(String email);
	
	
}
