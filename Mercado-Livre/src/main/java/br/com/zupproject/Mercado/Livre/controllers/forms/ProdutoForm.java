package br.com.zupproject.Mercado.Livre.controllers.forms;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupproject.Mercado.Livre.commons.validators.IdFinder;
import br.com.zupproject.Mercado.Livre.entidades.Categoria;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.repositorios.CategoriaRepository;

public class ProdutoForm {

	@NotBlank
	private String nome;
	@NotNull
	@Min(1)
	private BigDecimal valor;
	@NotNull
	@Min(1)
	private Integer quantidade;
	@Size(min = 3)
	private Map<String, String> caracteristicas = new HashMap<>();
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	@NotNull
	@IdFinder(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	public ProdutoForm(@NotBlank String nome, @NotNull @Min(1) BigDecimal valor, @NotNull @Min(1) Integer quantidade,
			@Size(min = 3) Map<String, String> caracteristicas, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Long idCategoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}

	public Produto converter(CategoriaRepository categoriaRepository, Usuario dono) {
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

		if (categoria.isPresent()) {
			return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria.get(), dono);
		}
		return null;
	}
}
