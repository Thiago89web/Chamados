package com.thiago.chamado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiago.chamado.entity.Motivo;
import com.thiago.chamado.exceptions.ResourceNotFoundException;
import com.thiago.chamado.repository.MotivoRepository;

@Service
public class MotivoService {
	
	@Autowired
	private MotivoRepository motivoRepository;

	public List<Motivo> findAll() {
		
		return motivoRepository.findAll();
	}
	
	
	public Motivo find(Long id) {		
		
		Optional<Motivo> obj = motivoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Motivo.class.getName()));		
	}
	
	

}
