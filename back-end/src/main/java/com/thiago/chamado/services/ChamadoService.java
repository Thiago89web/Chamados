package com.thiago.chamado.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.thiago.chamado.dto.ChamadoDTO;
import com.thiago.chamado.entity.Chamado;
import com.thiago.chamado.entity.Colaborador;
import com.thiago.chamado.repository.ChamadoRepository;
import com.thiago.chamado.services.exceptions.ObjectNotFoundException;



@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private ColaboradorService colaboradorService;
		
	public Chamado findById(Long id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Chamado.class.getName()));
	}	

	public Chamado create(ChamadoDTO obj) {
		obj.setId(null);		
		return chamadoRepository.save(newChamado(obj));
		//return obj;
	}
	
	public Chamado update(Long id, ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);
		return chamadoRepository.save(oldObj);
	}
	
	private Chamado newChamado(ChamadoDTO obj) {
		Colaborador colaborador = colaboradorService.find(obj.getColaborador());		
		Chamado chamado = new Chamado();
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		chamado.setTitulo(obj.getTitulo());
		chamado.setDescricao(obj.getDescricao());
		chamado.setFinalizado(obj.getFinalizado());
		
		chamado.setColaborador(colaborador);		
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
		//return chamadoRepository.findAllPageClose(page, col);
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
		//Page<Chamado> obj = chamadoRepository.findColOpen(page, col);
		//return obj;		
		String perfi = col.getPerfis().toString();		
		if(perfi.equals("[COLABORADOR]")) {	
			Page<Chamado> obj = chamadoRepository.findColOpen(page, col);
			return obj;
		}else {
			Page<Chamado> obj = chamadoRepository.findAllOpenAdm(page);
			return obj;	
		}											
	}

	/*public Page<Chamado> listAllOpen(PageRequest page) {
		// TODO Auto-generated method stub
		return chamadoRepository.findAllOpen(page);
	}*/	

}
