package com.system.osworks.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.system.osworks.domain.model.StatusOrdemServico;

public class OrdemServicoModel {

	private Long id;
	private Long clienteId;
	private OrdemServicoCliente cliente;
	
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFinalizacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	
			
	public OrdemServicoCliente getCliente() {
		return cliente;
	}
	public void setCliente(OrdemServicoCliente cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public StatusOrdemServico getStatus() {
		return status;
	}
	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public LocalDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
}
