package com.thiago.chamado.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import com.thiago.chamado.entity.Tutorial;

public class TutorialDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@NotBlank(message = "Nome do tutorial é obrigatório")
	private String liveName;
	
	@NotBlank(message = "Nome do canal é obrigatório")
	private String channelName;
	
	@NotBlank(message = "Link do vídeo é obrigatório")
	@URL(regexp = "^(http|ftp).*", message = "URL do vídeo é inválida")
	private String liveLink;
	
	private LocalDateTime registrationDate;
	
	public TutorialDTO() {
	}
	
	public TutorialDTO(Tutorial obj) {
		this.id = obj.getId();
		this.liveName = obj.getLiveName();
		this.channelName = obj.getChannelName();
		this.liveLink = obj.getLiveLink();
		this.registrationDate = obj.getRegistrationDate();	
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLiveName() {
		return liveName;
	}

	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getLiveLink() {
		return liveLink;
	}

	public void setLiveLink(String liveLink) {
		this.liveLink = liveLink;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

}
