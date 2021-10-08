package br.com.zupproject.Mercado.Livre.controllers.forms;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupproject.Mercado.Livre.commons.validators.IdFinder;
import br.com.zupproject.Mercado.Livre.entidades.Compra;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.entidades.enums.ServicoPagamento;
import br.com.zupproject.Mercado.Livre.repositorios.ProdutoRepository;

public class CompraForm {

	@Positive
	@NotNull
	private Integer quantidade;
	@NotNull
	private ServicoPagamento servicoPagamento;
	@NotNull
	@IdFinder(domainClass = Produto.class, fieldName = "id")
	private Long idProduto;

	public Integer getQuantidade() {
		return quantidade;
	}

	public ServicoPagamento getServicoPagamento() {
		return servicoPagamento;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Compra converter(ProdutoRepository produtoRepository, Usuario usuario) {
		Optional<Produto> produto = produtoRepository.findById(idProduto);

		if (produto.isPresent()) {
			BigDecimal valor = produto.get().getValor().multiply(BigDecimal.valueOf(quantidade));
			return new Compra(quantidade, valor, servicoPagamento, usuario, produto.get());

		}
		return null;
	}
}
