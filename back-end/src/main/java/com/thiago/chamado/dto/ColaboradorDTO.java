package com.thiago.chamado.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thiago.chamado.entity.Colaborador;
import com.thiago.chamado.entity.enums.Perfil;



public class ColaboradorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório!")
	@Length(min=5, max=120, message="O nome deve ter entre 5 e 120 caracteres!")
	private String nome;
	
	@NotBlank(message = "Email é obrigatório!")
	@Email(message="Email inválido")
	private String email;
	
	@NotBlank(message = "A senha é obrigatória!")
	private String senha;
	
	protected Set<Integer> perfis = new HashSet<>();
	
	public ColaboradorDTO() {
		addPerfil(Perfil.COLABORADOR);
	}
	
	public ColaboradorDTO(Colaborador obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCod()).collect(Collectors.toSet());
		addPerfil(Perfil.COLABORADOR);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCod());
	}
	
	
}
