package com.thiago.chamado.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.thiago.chamado.entity.Motivo;

public class MotivoDTO implements Serializable {
		
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String tipoMotivo;
	
	public MotivoDTO() {
		super();
	}
								
	public MotivoDTO(Motivo obj) {
		super();
		this.id = obj.getId();
		this.tipoMotivo = obj.getTipoMotivo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(String tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}

}
