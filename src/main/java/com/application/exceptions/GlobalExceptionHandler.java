package com.application.exceptions;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		for (FieldError f : ex.getBindingResult().getFieldErrors()) {
			erros.add(f.getField() + ":" + f.getDefaultMessage());
		}

		ErroResposta er = new ErroResposta(status.value(), "Existem campos invalidos", LocalDateTime.now(), erros);
		return super.handleExceptionInternal(ex, er, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErroResposta er = new ErroResposta(status.value(), "Campos invalidos foram inseridos, favor verificar",
				LocalDateTime.now(), null);
		return super.handleExceptionInternal(ex, er, headers, status, request);
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage());
		
		ErroResposta er = new ErroResposta(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				LocalDateTime.now(), erros);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
		
	}
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<Object> handleEmailException(EmailException ex){	
		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage());
		
		ErroResposta er = new ErroResposta(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				LocalDateTime.now(), erros);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
		
	
	}
	

	@ExceptionHandler(LoginIncorretoException.class)
	public ResponseEntity<Object> hadleLoginIncorretoException(LoginIncorretoException ex){
		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage());
		
		ErroResposta er = new ErroResposta(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				LocalDateTime.now(), erros);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
	}
	
//	@ExceptionHandler(LoginIncorretoException.class)
//    public ResponseEntity<String> handleLoginIncorretoException(LoginIncorretoException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
//    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token invalido ou expirado.");
    }
}
