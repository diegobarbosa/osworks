package com.system.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)//Somente propriedades não nulas serão serializadas em Json. Assim, os campos null não serão serializados
public class Problema {
	
	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	
	private List<Campo> campos;
	
	public List<Campo> getCampos() {
		return campos;
	}
	public void setCampos(List<Problema.Campo> campos) {
		this.campos = campos;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
	public static class Campo{
		
		private String nome;
		private String mensagem;
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
	}

}
