package br.com.zupproject.Mercado.Livre.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupproject.Mercado.Livre.commons.exceptions.PagamentoInvalidoException;
import br.com.zupproject.Mercado.Livre.controllers.feign.GeradorDeRanking;
import br.com.zupproject.Mercado.Livre.controllers.feign.GeradorNotaFiscal;
import br.com.zupproject.Mercado.Livre.controllers.forms.PagamentoPagseguroForm;
import br.com.zupproject.Mercado.Livre.controllers.forms.PagamentoPaypalForm;
import br.com.zupproject.Mercado.Livre.entidades.Compra;
import br.com.zupproject.Mercado.Livre.entidades.Pagamento;
import br.com.zupproject.Mercado.Livre.entidades.enums.StatusPagamento;
import br.com.zupproject.Mercado.Livre.mocks.MockServicoEmail;
import br.com.zupproject.Mercado.Livre.repositorios.CompraRepository;
import br.com.zupproject.Mercado.Livre.repositorios.PagamentoRepository;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private MockServicoEmail mailer;

	@Autowired
	private GeradorNotaFiscal geradorNotaFiscal;

	@Autowired
	private GeradorDeRanking geradorRanking;

	@PostMapping("/paypal")
	public void cadastraPagamentoPaypal(@RequestBody @Valid PagamentoPaypalForm form) {
		Pagamento pagamento = form.converter(compraRepository);
		Compra compra = pagamento.getCompra();

		executaControleDePagamento(pagamento, compra);
	}

	@PostMapping("/pagseguro")
	public void cadastraPagamentoPagseguro(@RequestBody @Valid PagamentoPagseguroForm form) {
		Pagamento pagamento = form.converter(compraRepository);
		Compra compra = pagamento.getCompra();

		executaControleDePagamento(pagamento, compra);
	}

	private void executaControleDePagamento(Pagamento pagamento, Compra compra) {

		if (!compra.jaPossuiPagamentoDeSucesso()) {
			executaProcessoDePagamento(pagamento, compra);
		} else {
			throw new PagamentoInvalidoException("Este pedido já possui um pagamento processado");
		}
	}

	private void executaProcessoDePagamento(Pagamento pagamento, Compra compra) {
		pagamentoRepository.save(pagamento);
		if (pagamento.getStatusPagamento().equals(StatusPagamento.SUCESSO)) {
			compra.alteraStatusPagamentoParaPago();
			mailer.enviaEmailPagamentoAceito(compra.getId(), compra.getProduto().getNome(),
					compra.getUsuario().getUsername());

			geradorNotaFiscal.enviar(compra.getId(), compra.getUsuario().getId());
			geradorRanking.enviar(compra.getId(), compra.getUsuario().getId());

		} else {
			mailer.enviaEmailPagamentoRecusado(compra.getId(), compra.getProduto().getNome(),
					compra.getServicoPagamento().toString(), compra.getUsuario().getUsername());
		}
	}
}
