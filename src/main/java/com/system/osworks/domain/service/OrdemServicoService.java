package com.system.osworks.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.system.osworks.domain.exception.DomainException;
import com.system.osworks.domain.exception.NotFoundException;
import com.system.osworks.domain.model.Cliente;
import com.system.osworks.domain.model.Comentario;
import com.system.osworks.domain.model.OrdemServico;
import com.system.osworks.domain.model.StatusOrdemServico;
import com.system.osworks.domain.repository.ClienteRepository;
import com.system.osworks.domain.repository.ComentarioRepository;
import com.system.osworks.domain.repository.OrdemServicoRepository;

@Service//Componente do Spring
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repo;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(()-> new DomainException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		
		return repo.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		
		OrdemServico ordemServico = repo.findById(ordemServicoId)
				.orElseThrow(()-> new NotFoundException("Ordem de serviço não encontrada"));
		
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(LocalDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		comentarioRepository.save(comentario);
		
		return comentario;
	}
	
	public void finalizar(Long ordemServicoId) {
		
		OrdemServico ordemServico = repo.findById(ordemServicoId)
				.orElseThrow(()-> new NotFoundException("Ordem de serviço não encontrada"));
		
		
		ordemServico.finalizar();
		
		repo.save(ordemServico);
	}
	
	
}
