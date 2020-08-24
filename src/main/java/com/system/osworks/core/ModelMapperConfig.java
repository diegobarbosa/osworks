package com.system.osworks.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Define um componente de configuração do Spring
//Poderia ter uma configuração de bean para a classe OrdemServicoSerce.
//Mas como a classe é minha basta anota-la com a anotation Servico que o Spring já identifica como componente.
//O problema aqui é que ModelMapper não foi declarado com componente.
// Aqui ele é declarado e pode ser usado dentro do Spring.
@Configuration
public class ModelMapperConfig {
	
	//Define um objeto Bean, não gerenciado pelo Spring
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
