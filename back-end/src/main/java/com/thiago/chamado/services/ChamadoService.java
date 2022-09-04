package com.thiago.chamado.services;

import java.util.Optional;
import java.util.stream.Stream;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.thiago.chamado.dto.ChamadoDTO;
import com.thiago.chamado.entity.Chamado;
import com.thiago.chamado.entity.Colaborador;
import com.thiago.chamado.entity.FileEntity;
import com.thiago.chamado.entity.Motivo;
import com.thiago.chamado.exceptions.ResourceNotFoundException;
import com.thiago.chamado.repository.ChamadoRepository;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
	private MotivoService motivoService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private FileService fileService;
		
	public Chamado findById(Long id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Chamado.class.getName()));
	}	

	public Chamado create(ChamadoDTO obj) {
		obj.setId(null);		
		return chamadoRepository.save(newChamado(obj));
	}
	
	public Chamado update(Long id, ChamadoDTO objDTO) throws MessagingException {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
						
		oldObj = newChamado(objDTO);
		
		if(oldObj.getFinalizado() == true) {												
			emailService.enviarEmail(oldObj);					
		}
		
		return chamadoRepository.save(oldObj);
	}
	
	private Chamado newChamado(ChamadoDTO obj) {						
		Chamado chamado = new Chamado();
							
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getColaborador() != null) {
			Colaborador colaborador = colaboradorService.find(obj.getColaborador());		
			chamado.setColaborador(colaborador);
		}
		
		if(obj.getMotivo() != null) {
			Motivo motivo = motivoService.find(obj.getMotivo());		
			chamado.setMotivo(motivo);		
		}
		
		if(obj.getFileEntity() != null) {
			FileEntity fileEntity = fileService.getFileId(obj.getFileEntity());		
			chamado.setFileEntity(fileEntity);			
		}
				
		chamado.setTitulo(obj.getTitulo());
		chamado.setDescricao(obj.getDescricao());
		chamado.setFinalizado(obj.getFinalizado());
	
		return chamado;
	}
			
	public void delete(Long id) {
		chamadoRepository.deleteById(id);
	}
	
	public Page<Chamado> listAllPageAdm(PageRequest page) {
		return chamadoRepository.findAllOpenAdm(page);
	}

	public Page<Chamado> listAllPageOpen(PageRequest page, Colaborador col) {
		return chamadoRepository.findAllPageOpen(page, col);
	}

	public Page<Chamado> listAllPageClose(PageRequest page, Colaborador col) {
		String perfi = col.getPerfis().toString();		
		if(perfi.equals("[COLABORADOR]")) {
			Page<Chamado> obj = chamadoRepository.findAllPageClose(page, col);
			return obj;
		}else {
			Page<Chamado> obj = chamadoRepository.findAllCloseAdm(page);
			return obj;	
		}
	}

	public Page<Chamado> findColOpen(PageRequest page, Colaborador col) {					
		String perfi = col.getPerfis().toString();		
		if(perfi.equals("[COLABORADOR]")) {	
			Page<Chamado> obj = chamadoRepository.findColOpen(page, col);
			return obj;
		}else {
			Page<Chamado> obj = chamadoRepository.findAllOpenAdm(page);
			return obj;	
		}											
	}
	
	public Stream<Chamado> getChamadoAndFileId(Long id) {				  
		  Optional<Stream<Chamado>> obj = Optional.ofNullable(chamadoRepository.findChamadoFileId(id).stream());
			return obj.orElseThrow(() -> new IllegalArgumentException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Chamado.class.getName()));
		}
		
}
