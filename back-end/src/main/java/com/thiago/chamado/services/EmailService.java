package com.thiago.chamado.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.thiago.chamado.entity.Chamado;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SpringTemplateEngine template;
	
	public void enviarEmail(Chamado oldObj) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = 
				new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
		
		Context context = new Context();
		context.setVariable("titulo", "Chamado Nº "+oldObj.getId()+" finalizado!");
		context.setVariable("texto", "Chamado de número "+oldObj.getId()+" finalizado com sucesso!");
		
		//Enviando o objeto chamado para página
		context.setVariable("descrition", oldObj);
		
		String html = template.process("finalizado", context);
		
		//Emai de destino setTo
		helper.setTo(oldObj.getColaborador().getEmail());
		
		helper.setText(html, true);
		helper.setSubject("Chamado Nº "+oldObj.getId()+" Finalizado");
		//Remetente setfrom
		helper.setFrom("negocionet69@gmail.com");
		
		//helper.addInline("logo", new ClassPathResource("/static/image/spring-security.png"));
		
		javaMailSender.send(message);
	}
}
