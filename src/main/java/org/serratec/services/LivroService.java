package org.serratec.services;

import java.util.List;
import java.util.stream.Collectors;

import org.serratec.dtos.LivroDTO;
import org.serratec.entities.Livro;
import org.serratec.exceptions.EntityNotFoundException;
import org.serratec.mappers.LivroMapper;
import org.serratec.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class LivroService {
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	LivroMapper mapper;
	
	public List<LivroDTO> getTodos(){ //Somento o DTO que é utilizado para manipular as informações
		return livroRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
									 /* map converte para dto o tipo livro*//* collect realiza um foreach*/
	}
	
	public LivroDTO getById (Long id) throws EntityNotFoundException { 
		return mapper.toDto(this.findById(id));// é chamado o metodo findById criado abaixo para poder retornar o id
							
		/*Todos os métodos(findById, save, findAll,...) do próprio Spring sempre retornam um tipo entidade, 
		então é necessário sempre fazer a conversão( caso passe a entidade não há necessidade)*/
	}
	
	/*Esse método foi criado para não retornar um id nulo*/
	public Livro findById(Long id) throws EntityNotFoundException {
		return livroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " não encontrado."));
	}
	
	public LivroDTO create(LivroDTO dto) {
		return mapper.toDto(livroRepository.save(mapper.toEntity(dto)));
		/*para executar o método save ele precisa ser de um tipo entity, então primeiro ele converte em
		entidade e depois ele converte para DTO para poder dar o retorno que está em DTO*/
	}
	
	
	/*Primeiro foi encontrado por ID o livro a ser alterado
	 *Depois foi pego a informação do Json que está em formato dto e está setando em entity
	 *Por último foi transformado novamente para dto e foi salvo o que foi setado
	 */
	public LivroDTO update(Long id, LivroDTO dto ) throws EntityNotFoundException {
		Livro livroEntity =  this.findById(id);
		livroEntity.setTitulo(dto.getTitulo());
		livroEntity.setCategoria(dto.getCategoria());
		livroEntity.setAutor(dto.getAutor());
		livroEntity.setDataPublicacao(dto.getDataPublicacao());
		
		return mapper.toDto(livroRepository.save(livroEntity));
		
	}
	
	public String delete(Long id) {
		livroRepository.deleteById(id);
		return "Deletado com sucesso!";
		
		//como ja é do tipo entity não é necessário nenhuma conversão
		
	}

}
