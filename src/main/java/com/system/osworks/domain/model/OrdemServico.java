package com.system.osworks.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.system.osworks.domain.exception.DomainException;

@Entity
public class OrdemServico {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	//@JoinColumn(name="cliente_id") cliente_id é o padrão
	private Cliente cliente;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@JsonProperty(access=Access.READ_ONLY)//Especifica que se esse dado for informado via json, ele não será colocado no objeto. É somente JSON readonly 
	@Enumerated(EnumType.STRING)//Especifica que o dado será persistido como String
	private StatusOrdemServico status;
	
	@JsonProperty(access=Access.READ_ONLY)
	private LocalDateTime dataAbertura;
	
	@JsonProperty(access=Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;
	
	//Uma Ordem de Serviço pode ter vários comentários
	@OneToMany(mappedBy="ordemServico")//propriedade do outro lado que é a ligação entre os dois
	private List<Comentario> comentarios = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
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
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}
	
	public boolean podeSerFinalizada() {
		return getStatus().equals(StatusOrdemServico.ABERTA);
	}
	
	public void finalizar() {
		
		if(!podeSerFinalizada()) {
			throw new DomainException("A ordem de serviço só pode ser finalizada se estiver ABERTA.");
		}
				
		setStatus(StatusOrdemServico.FINALIZADA);
		setDataFinalizacao(LocalDateTime.now());
		

		
	}
}
