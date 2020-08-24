package com.system.osworks.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.system.osworks.domain.exception.DomainException;
import com.system.osworks.domain.model.Cliente;
import com.system.osworks.domain.model.OrdemServico;
import com.system.osworks.domain.model.StatusOrdemServico;
import com.system.osworks.domain.repository.ClienteRepository;
import com.system.osworks.domain.repository.OrdemServicoRepository;

@Service//Componente do Spring
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repo;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(()-> new DomainException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		
		return repo.save(ordemServico);
	}
}
