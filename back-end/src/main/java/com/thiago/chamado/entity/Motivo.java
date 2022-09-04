package com.thiago.chamado.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thiago.chamado.dto.MotivoDTO;

@Entity
public class Motivo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipoMotivo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "motivo")
	private List<Chamado> chamados = new ArrayList<>();
	
	public Motivo() {
		super();
	}	

	public Motivo(Long id, String tipoMotivo) {
		super();
		this.id = id;
		this.tipoMotivo = tipoMotivo;
	}
	
	
	public Motivo(MotivoDTO obj) {
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

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Motivo other = (Motivo) obj;
		return Objects.equals(id, other.id);
	}
					
}
