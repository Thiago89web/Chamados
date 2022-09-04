package com.thiago.chamado.controller;

import java.net.URI;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thiago.chamado.dto.ChamadoDTO;
import com.thiago.chamado.entity.Chamado;
import com.thiago.chamado.entity.Colaborador;
import com.thiago.chamado.services.ChamadoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Chamado> findById(@PathVariable Long id) {
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/openColaborador", method = RequestMethod.GET)
	public ResponseEntity<Page<Chamado>> listColOpen(@RequestParam(value = "value") Colaborador col) {
		PageRequest page = PageRequest.of(0, 5);
		Page<Chamado> list = chamadoService.findColOpen(page, col);
		return new ResponseEntity<Page<Chamado>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/close")
	public ResponseEntity<Page<Chamado>> listAllClose(@RequestParam(value = "value") Colaborador col) {
		PageRequest page = PageRequest.of(0, 5);
		Page<Chamado> list = chamadoService.listAllPageClose(page, col);

		return new ResponseEntity<Page<Chamado>>(list, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Chamado> create(@Valid @RequestBody ChamadoDTO objDTO) {
		Chamado newObj = chamadoService.create(objDTO);
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
			
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		chamadoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Chamado> update(@PathVariable Long id, @RequestBody ChamadoDTO obj) throws MessagingException{
		Chamado newObj = chamadoService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@GetMapping("/pageOpen/{pagina}")
	public ResponseEntity<Page<Chamado>> chamadoPaginaOpen(@PathVariable("pagina") int pagina,
			@RequestParam(value = "colaborador") Colaborador col) {

		PageRequest page = PageRequest.of(pagina, 5, Sort.by("id"));

		String perfi = col.getPerfis().toString();
		if (perfi.equals("[COLABORADOR]")) {
			Page<Chamado> list = chamadoService.listAllPageOpen(page, col);
			return new ResponseEntity<Page<Chamado>>(list, HttpStatus.OK);
		} else {
			Page<Chamado> list = chamadoService.listAllPageAdm(page);
			return new ResponseEntity<Page<Chamado>>(list, HttpStatus.OK);
		}
	}

	@GetMapping("/pageClose/{pagina}")
	public ResponseEntity<Page<Chamado>> chamadoPaginaClose(@PathVariable("pagina") int pagina,
			@RequestParam(value = "colaborador") Colaborador col) {

		PageRequest page = PageRequest.of(pagina, 5, Sort.by("id"));
		Page<Chamado> list = chamadoService.listAllPageClose(page, col);

		return new ResponseEntity<Page<Chamado>>(list, HttpStatus.OK);
	}
	 
}
