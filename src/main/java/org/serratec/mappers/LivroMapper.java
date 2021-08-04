package org.serratec.mappers;

import org.serratec.dtos.LivroDTO;
import org.serratec.entities.Livro;
import org.springframework.stereotype.Component;

@Component //É necessário para poder instânciar a classe 
public class LivroMapper {
	
	
	//Está sendo passado em entity e setando em dto(Convertendo entity em dto)
	public LivroDTO toDto(Livro livroEntity ) {
		LivroDTO dto = new LivroDTO();
		dto.setAutor(livroEntity.getAutor());
		dto.setCategoria(livroEntity.getCategoria());
		dto.setTitulo(livroEntity.getTitulo());
		dto.setDataPublicacao(livroEntity.getDataPublicacao());
		
		return dto;
	}
	
	//Convertendo de dto para entity
	public Livro toEntity(LivroDTO dto) {
		Livro livroEntity = new Livro();
		livroEntity.setAutor(dto.getAutor());
		livroEntity.setCategoria(dto.getCategoria());
		livroEntity.setTitulo(dto.getTitulo());
		livroEntity.setDataPublicacao(dto.getDataPublicacao());
		
		return livroEntity;
	}

}
