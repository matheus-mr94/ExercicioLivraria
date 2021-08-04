package org.serratec.controllers;

import java.util.List;

import javax.validation.Valid;

import org.serratec.dtos.LivroDTO;
import org.serratec.exceptions.EntityNotFoundException;
import org.serratec.services.LivroService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	LivroService service;

	@GetMapping
	public ResponseEntity<List<LivroDTO>> getAll() {
		return new ResponseEntity<List<LivroDTO>>(service.getTodos(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> getById(@PathVariable Long id) throws EntityNotFoundException {
		return new ResponseEntity<LivroDTO>(service.getById(id), HttpStatus.OK);

	}
	
	@PostMapping
	public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO dto){
		return new ResponseEntity<LivroDTO> (service.create(dto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Long id, @Valid @RequestBody LivroDTO dto) throws EntityNotFoundException{
		return new ResponseEntity<LivroDTO> (service.update(id, dto),HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		return new ResponseEntity<String> (service.delete(id),HttpStatus.OK);
	}
}
