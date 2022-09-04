package com.thiago.chamado.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thiago.chamado.entity.Chamado;
import com.thiago.chamado.entity.Colaborador;
import com.thiago.chamado.entity.Motivo;
import com.thiago.chamado.entity.Tutorial;
import com.thiago.chamado.entity.enums.Perfil;
import com.thiago.chamado.repository.ChamadoRepository;
import com.thiago.chamado.repository.ColaboradorRepository;
import com.thiago.chamado.repository.MotivoRepository;
import com.thiago.chamado.repository.TutorialRepository;

@Service
public class DBService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private MotivoRepository motivoRepository;
	
	@Autowired
	private TutorialRepository liveRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Colaborador co1 = new Colaborador(null, "Thaigo", "negocionet69@gmail.com", pe.encode("123456"));		
		co1.addPerfil(Perfil.ADMIN);
		
		Colaborador co2 = new Colaborador(null, "Usuário", "negocionet67@gmail.com", pe.encode("123456"));		
		co2.addPerfil(Perfil.COLABORADOR);
		//pontosisgep@gmail.com
		Colaborador co3 = new Colaborador(null, "Maria Silvia", "virtualves@gmail.com", pe.encode("123456"));		
		co2.addPerfil(Perfil.COLABORADOR);
		
		Motivo mt1 = new Motivo(null, "Impressora sem papel");
		Motivo mt2 = new Motivo(null, "Problema na impressora do relógio");
		Motivo mt3 = new Motivo(null, "Relógio queimado");
		Motivo mt4 = new Motivo(null, "Relógio bloqueado");
		Motivo mt5 = new Motivo(null, "Servidor está fora de escala");
		Motivo mt6 = new Motivo(null, "Servidor não está no relógio");
		Motivo mt7 = new Motivo(null, "Não aparece as batidas de ponto do servidor");
		Motivo mt8 = new Motivo(null, "Servidor com pis errado");
		Motivo mt9 = new Motivo(null, "Servidor com matrícula errada");
		Motivo mt10 = new Motivo(null, "Servidor fora de gestão");
		Motivo mt11 = new Motivo(null, "Mudança de setor");
		Motivo mt12 = new Motivo(null, "Alocação de batidas");
		Motivo mt13 = new Motivo(null, "Problema na gerência do sistema de ponto");
		Motivo mt14 = new Motivo(null, "Ajuste de escala do servidor");
		Motivo mt15 = new Motivo(null, "Outro motivo");
					
		LocalDate dataCriacao = LocalDate.now();
				
		Chamado c1 = new Chamado(null, "Estudar", "Estudar Spring Boot", dataCriacao, false, co3, mt1, null);
		Chamado c2 = new Chamado(null, "Ajuste de horário", "Ajustar horário", dataCriacao, false, co3, mt1, null);
		Chamado c3 = new Chamado(null, "Relógio apagado", "relógio não liga", dataCriacao, false, co3, mt2, null);
		Chamado c4 = new Chamado(null, "Estudar", "Estudar Spring Boot 2 e Angular 11", dataCriacao, false, co3, mt1, null);
		Chamado c5 = new Chamado(null, "Relógio travado", "Rélogio não funciona", dataCriacao, false, co3, mt1, null);
		Chamado c6 = new Chamado(null, "Relógio queimado", "Relógio bloqueado", dataCriacao, false, co3, mt1, null);
		Chamado c7 = new Chamado(null, "Glemiria", "Relógio bloqueado", dataCriacao, false, co3, mt13, null);
		Chamado c8 = new Chamado(null, "Ana Sandra", "Relógio bloqueado por queda de energia.", dataCriacao, false, co3, mt4, null);
		Chamado c9 = new Chamado(null, "Pingo de Gente", "Servidora está fora de escala", dataCriacao, true, co3, mt1, null);
									
		motivoRepository.saveAll(Arrays.asList(mt1, mt2, mt3, mt4, mt5, mt6, mt7, mt8, mt9, mt10, mt11, mt12, mt13, mt14, mt15));
		
		colaboradorRepository.saveAll(Arrays.asList(co1, co2, co3));
		
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));
		
		/*private String id;
		private String liveName;
		private String channelName;
		private LocalDateTime liveDate;
		private String liveLink;
		private LocalDateTime registrationDate;*/
			
		//LocalDateTime.parse("2022-02-20T06:30:00")		
		//LocalDateTime dataRegistrada = LocalDateTime.now();
		
		Tutorial lv1 = new Tutorial(null, "Como Trocar a Bobina", "TecSistemas", "https://www.youtube.com/embed/WVOK8mabjK4", LocalDateTime.parse("2022-02-20T06:30:00"));
		Tutorial lv2 = new Tutorial(null, "Como Cadastrar uma Digital", "TecSistemas", "https://www.youtube.com/embed/dbAoDtseUpQ", LocalDateTime.parse("2022-02-20T06:30:00"));
		Tutorial lv3 = new Tutorial(null, "Coleta Via Pen Driver", "TecSistemas", "https://www.youtube.com/embed/YqC1od3AEUs", LocalDateTime.parse("2022-02-20T06:30:00"));
		
		liveRepository.saveAll(Arrays.asList(lv1, lv2, lv3));
	}
	
	
	
	
	

}
