package com.thiago.chamado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.chamado.entity.Motivo;

public interface MotivoRepository extends JpaRepository<Motivo, Long>{

}
