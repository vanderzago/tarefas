package br.com.vanderz.login.entities;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
	private Long id;
	private String login;
	private String senha;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCriacao;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Calendar getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
}
