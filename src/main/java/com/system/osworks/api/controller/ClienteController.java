package com.system.osworks.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.osworks.domain.repository.ClienteRepository;
import com.system.osworks.domain.service.CadastroClienteService;
import com.system.osworks.domain.model.Cliente;



@RestController//Componente do Spring
@RequestMapping("/clientes")
public class ClienteController {

	//@PersistenceContext//Injeta a instância no controller
	//private EntityManager manager;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	@GetMapping()
	//@GetMapping("/clientes")
	public List<Cliente> listar(){
		
		//JPQL
		//return manager.createQuery("from Cliente", Cliente.class)
		//		.getResultList();
		
		return clienteRepository.findAll();
		
	}
	@GetMapping("/{clienteId}")
	//@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();		
		
		//return cliente.orElse(null);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)//Define o status do response como Created
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.OK)//Status OK não é necessário. O Default já é OK (200)
	public ResponseEntity<Cliente> atualizar( @PathVariable Long clienteId,@Valid @RequestBody Cliente cliente) {
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = cadastroCliente.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroCliente.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
		
	}
		
}
	
	
	

