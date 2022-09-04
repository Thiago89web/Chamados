package com.thiago.chamado.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.chamado.dto.MotivoDTO;
import com.thiago.chamado.entity.Motivo;
import com.thiago.chamado.services.MotivoService;

@RestController
@RequestMapping(value="/motivos")
public class MotivoController {
	
	@Autowired
	private MotivoService motivoService;
	
	
	@GetMapping
	public ResponseEntity<List<MotivoDTO>> findAll() {
		List<Motivo> list = motivoService.findAll();
		List<MotivoDTO> listDTO = list.stream().map(obj -> new MotivoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

}
