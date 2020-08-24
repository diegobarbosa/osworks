package com.system.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.osworks.api.model.OrdemServicoCriar;
import com.system.osworks.api.model.OrdemServicoModel;
import com.system.osworks.domain.model.OrdemServico;
import com.system.osworks.domain.repository.OrdemServicoRepository;
import com.system.osworks.domain.service.OrdemServicoService;

@RestController
@RequestMapping("ordens-servico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService service;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoCriar ordemCriar) {
		
		OrdemServico ordem = modelMapper.map(ordemCriar, OrdemServico.class);
		
		return toModel(service.criar(ordem));
	}
	
	@GetMapping
	public List<OrdemServicoModel> listar(){
		return ordemServicoRepo.findAll()
				.stream()
				.map((os)-> toModel(os)).collect(Collectors.toList());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId){
		
	Optional<OrdemServico> ordemServico = ordemServicoRepo.findById(ordemServicoId);
	
	if(ordemServico.isPresent()) {
		//OrdemServicoModel model = new OrdemServicoModel();
		//model.setId(ordemServico.get().getId());
		//model.setDescricao(ordemServico.get().getDescricao());
		
		return ResponseEntity.ok(toModel(ordemServico.get()));
	}
		
		return ResponseEntity.notFound().build();
		
	}
	
	
	@PutMapping("/{ordemServicoId}/finalizacao")//Put é idempontende
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizacao(@PathVariable Long ordemServicoId) {
		
		service.finalizar(ordemServicoId);
	}
	
	
	OrdemServicoModel toModel(OrdemServico ordem) {
		return modelMapper.map(ordem, OrdemServicoModel.class);
	}
	
}
