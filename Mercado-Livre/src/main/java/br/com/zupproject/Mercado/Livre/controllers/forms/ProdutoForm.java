package br.com.zupproject.Mercado.Livre.controllers.forms;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupproject.Mercado.Livre.customizations.IdFinder;
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

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Map<String, String> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Produto converter(CategoriaRepository categoriaRepository, Usuario dono) {
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

		if (categoria.isPresent()) {
			return new Produto(nome, valor, quantidade, caracteristicas, descricao, categoria.get(), dono);
		}
		return null;
	}
}
