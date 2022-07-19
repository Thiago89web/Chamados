package com.thiago.chamado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.thiago.chamado.entity.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{

	Optional<Colaborador> findById(Integer id);

	void deleteById(Integer id);

	
	/*@Transactional(readOnly=true)
	Colaborador findByEmail(String email);*/
	
	@Transactional(readOnly=true)
	Optional<Colaborador> findByEmail(String email);

}
