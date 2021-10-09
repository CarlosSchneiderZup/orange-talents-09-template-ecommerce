package br.com.zupproject.Mercado.Livre.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupproject.Mercado.Livre.controllers.forms.CompraForm;
import br.com.zupproject.Mercado.Livre.entidades.Compra;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.mocks.MockServicoEmail;
import br.com.zupproject.Mercado.Livre.repositorios.CompraRepository;
import br.com.zupproject.Mercado.Livre.repositorios.ProdutoRepository;
import br.com.zupproject.Mercado.Livre.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private MockServicoEmail mailer;

	@PostMapping
	@ResponseStatus(HttpStatus.FOUND)
	public String cadastraCompra(@RequestBody @Valid CompraForm form, UriComponentsBuilder builder)
			throws BindException {
		Optional<Usuario> usuarioLogado = usuarioRepository.findById(1L);

		if (usuarioLogado.isPresent()) {
			Compra compra = form.converter(produtoRepository, usuarioLogado.get());
			Produto produtoComprado = compra.getProduto();

			if (produtoComprado.existeEstoqueParaCompra(compra.getQuantidade())) {

				produtoComprado.abateEstoque(compra.getQuantidade());
				compraRepository.save(compra);

				mailer.enviaEmailIntencaoCompra(produtoComprado);

				String servicoPagamento = compra.getServicoPagamento().toString().toLowerCase();
				String retorno = builder.path(servicoPagamento + "/{id}").buildAndExpand(compra.getId()).toString();

				return servicoPagamento + ".com?buyerId=" + compra.getId() + "&redirectUrl=" + retorno;
			}
		}
		BindException bindException = new BindException(form, "Nova compra");
		bindException.reject(null, "Não foi possivel realizar esta compra; quantidade maior que o estoque");
		throw bindException;
	}
}
