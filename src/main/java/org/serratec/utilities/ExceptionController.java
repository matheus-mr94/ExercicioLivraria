package org.serratec.utilities;

import org.serratec.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Essa classe é para poder colocar o tipo de retorno e uma mensagem no header
@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler (EntityNotFoundException.class)
	public ResponseEntity<String> tratarEntityNotFoundException(EntityNotFoundException e){
		return ResponseEntity.notFound().header("x-error-msg", e.getMessage()).build();
	}
	
	
}
