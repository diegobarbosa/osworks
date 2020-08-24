package com.system.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.system.osworks.domain.exception.DomainException;

@ControllerAdvice//Componente do Spring
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var campos = new ArrayList<Problema.Campo>();
		
		for(ObjectError error: ex.getBindingResult().getAllErrors()) {
			
			var nome = ((FieldError)error).getField();
			//error.getDefaultMessage();
			var mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			var campo = new Problema.Campo();
			campo.setNome(nome);
			campo.setMensagem(mensagem);
			
			campos.add(campo);
			
		}
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Campos inválidos");
		problema.setDataHora(LocalDateTime.now());
		problema.setCampos(campos);
				
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
	

	//Informa que esse método deve tratar as exceções do tipo DomainException
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(LocalDateTime.now());
		
		
		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	

	
	
}
