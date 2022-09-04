package com.thiago.chamado.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thiago.chamado.entity.Chamado;

public class ChamadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O nome da escola é obrigatório!")
	private String titulo;
	
	@Length(max=255, message="Descrição deve ter no maxímo  255 caracteres!")
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	
	private Boolean finalizado = false;
	
	private Long colaborador;
	
	@NotNull(message = "O Motivo é obrigatório!")
	private Long motivo;
	
	private Long fileEntity;
	
	public ChamadoDTO() {

	}

	public ChamadoDTO(Chamado obj) {
		super();
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		this.descricao = obj.getDescricao();
		this.dataCriacao = obj.getDataCriacao();
		this.finalizado = obj.getFinalizado();
		this.colaborador = obj.getColaborador().getId();
		this.motivo = obj.getMotivo().getId();
		this.fileEntity = obj.getFileEntity().getId();
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Long getColaborador() {
		return colaborador;
	}

	public void setColaborador(Long colaborador) {
		this.colaborador = colaborador;
	}

	public Long getMotivo() {
		return motivo;
	}

	public void setMotivo(Long motivo) {
		this.motivo = motivo;
	}

	public Long getFileEntity() {
		return fileEntity;
	}

	public void setFileEntity(Long fileEntity) {
		this.fileEntity = fileEntity;
	}

}
