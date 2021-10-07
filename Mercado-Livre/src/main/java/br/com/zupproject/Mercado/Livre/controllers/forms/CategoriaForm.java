package br.com.zupproject.Mercado.Livre.controllers.forms;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.zupproject.Mercado.Livre.commons.validators.IdFinder;
import br.com.zupproject.Mercado.Livre.commons.validators.UniqueValue;
import br.com.zupproject.Mercado.Livre.entidades.Categoria;
import br.com.zupproject.Mercado.Livre.repositorios.CategoriaRepository;

public class CategoriaForm {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	@IdFinder(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoriaMae;

	public String getNome() {
		return nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	public Categoria converter(CategoriaRepository categoriaRepository) {

		if (idCategoriaMae != null) {

			Optional<Categoria> categoria = categoriaRepository.findById(idCategoriaMae);

			return new Categoria(nome, categoria.get());
		}
		return new Categoria(nome, null);
	}
}
