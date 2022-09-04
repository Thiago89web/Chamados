package com.thiago.chamado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.chamado.entity.FileEntity;


public interface FileRepository extends JpaRepository<FileEntity, Long> {

	
}
