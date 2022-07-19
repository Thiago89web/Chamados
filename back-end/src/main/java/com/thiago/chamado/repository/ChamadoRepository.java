package com.thiago.chamado.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thiago.chamado.entity.Chamado;
import com.thiago.chamado.entity.Colaborador;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
	
	@Query("SELECT obj FROM Chamado obj WHERE obj.finalizado = false ORDER BY obj.id DESC")
	Page<Chamado> findAllOpenAdm(PageRequest page);
	
	@Query("SELECT obj FROM Chamado obj WHERE obj.finalizado = true ORDER BY obj.id DESC")
	Page<Chamado> findAllCloseAdm(PageRequest page);
	
	
	@Query("SELECT obj FROM Chamado obj WHERE obj.finalizado = false AND obj.colaborador = ?1 ORDER BY obj.id DESC")
	Page<Chamado>findColOpen(PageRequest page, Colaborador col);

	@Query("SELECT obj FROM Chamado obj WHERE obj.finalizado = false AND obj.colaborador = :col ORDER BY obj.id DESC")
	Page<Chamado> findAllPageOpen(PageRequest page, Colaborador col);

	@Query("SELECT obj FROM Chamado obj WHERE obj.finalizado = true AND obj.colaborador = :col ORDER BY obj.id DESC")
	Page<Chamado> findAllPageClose(PageRequest page, Colaborador col);	
	
}
