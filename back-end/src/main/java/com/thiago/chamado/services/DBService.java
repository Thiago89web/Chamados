package com.thiago.chamado.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thiago.chamado.entity.Chamado;
import com.thiago.chamado.entity.Colaborador;
import com.thiago.chamado.entity.enums.Perfil;
import com.thiago.chamado.repository.ChamadoRepository;
import com.thiago.chamado.repository.ColaboradorRepository;

@Service
public class DBService {
	
	@Autowired
	private ColaboradorRepository repository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Colaborador co1 = new Colaborador(null, "Thaigo", "thiago@gmail.com", pe.encode("123456"));		
		co1.addPerfil(Perfil.ADMIN);
		
		Colaborador co2 = new Colaborador(null, "Usuário", "usuario@gmail.com", pe.encode("123456"));		
		co2.addPerfil(Perfil.COLABORADOR);
		
					
		LocalDate dataCriacao = LocalDate.now();
				
		Chamado c1 = new Chamado(null, "Estudar", "Estudar Spring Boot", dataCriacao, false, co1);
		Chamado c2 = new Chamado(null, "Ajuste de horário", "Ajustar horário", dataCriacao, false, co1);
		Chamado c3 = new Chamado(null, "Relógio apagado", "relógio não liga", dataCriacao, false, co2);
		Chamado c4 = new Chamado(null, "Estudar", "Estudar Spring Boot 2 e Angular 11", dataCriacao, false, co2);
		Chamado c5 = new Chamado(null, "Relógio travado", "Rélogio não funciona", dataCriacao, false, co2);
		Chamado c6 = new Chamado(null, "Relógio queimado", "Relógio bloqueado", dataCriacao, false, co2);
		Chamado c7 = new Chamado(null, "Relógio queimado", "Relógio bloqueado", dataCriacao, false, co2);
		Chamado c8 = new Chamado(null, "Relógio queimado", "Relógio bloqueado", dataCriacao, false, co2);
		Chamado c9 = new Chamado(null, "Servidor fora", "Servidora está fora de escala", dataCriacao, true, co2);
										
		repository.saveAll(Arrays.asList(co1, co2));
		
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));
		
	}
	
	
	
	
	

}
