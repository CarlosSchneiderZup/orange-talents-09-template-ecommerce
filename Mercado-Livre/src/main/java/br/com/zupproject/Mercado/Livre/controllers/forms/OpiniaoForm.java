package br.com.zupproject.Mercado.Livre.controllers.forms;

import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupproject.Mercado.Livre.customizations.IdFinder;
import br.com.zupproject.Mercado.Livre.entidades.Opiniao;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.repositorios.ProdutoRepository;

public class OpiniaoForm {

	@NotBlank
	private String titulo;
	@Min(1)
	@Max(5)
	private Integer avaliacao;
	@NotBlank
	@Size(max = 500)
	private String descricao;
	@NotNull
	@IdFinder(domainClass = Produto.class, fieldName = "id")
	private Long idProduto;

	public String getTitulo() {
		return titulo;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Opiniao converter(ProdutoRepository produtoRepository, Usuario usuario) {
		Optional<Produto> produto = produtoRepository.findById(idProduto);
		
		if(produto.isPresent()) {
			return new Opiniao(titulo, avaliacao, descricao, produto.get(), usuario);
		}
		return null;
	}
}
