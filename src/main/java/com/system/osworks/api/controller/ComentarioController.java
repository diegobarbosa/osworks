package com.system.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.osworks.api.model.ComentarioInput;
import com.system.osworks.api.model.ComentarioModel;
import com.system.osworks.domain.exception.NotFoundException;
import com.system.osworks.domain.model.Comentario;
import com.system.osworks.domain.model.OrdemServico;
import com.system.osworks.domain.repository.ComentarioRepository;
import com.system.osworks.domain.repository.OrdemServicoRepository;
import com.system.osworks.domain.service.OrdemServicoService;

@RestController//Componente do Spring
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private OrdemServicoService service;
	
	@Autowired
	private OrdemServicoRepository osRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
		
		OrdemServico ordemServico = osRepo.findById(ordemServicoId)
				.orElseThrow(()-> new NotFoundException("Ordem de Serviço não encontrada"));
		
		return ordemServico.getComentarios()
				.stream().map(comentario-> toModel(comentario))
				.collect( Collectors.toList());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId, 
			@Valid @RequestBody ComentarioInput comentarioInput) {
				
		Comentario comentario = service.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
		
		return toModel(comentario);
		
	}
	
	
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
}
