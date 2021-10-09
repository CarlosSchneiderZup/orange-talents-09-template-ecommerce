package br.com.zupproject.Mercado.Livre.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupproject.Mercado.Livre.controllers.forms.pagamentoPagseguroForm;
import br.com.zupproject.Mercado.Livre.controllers.forms.pagamentoPaypalForm;
import br.com.zupproject.Mercado.Livre.entidades.Compra;
import br.com.zupproject.Mercado.Livre.entidades.Pagamento;
import br.com.zupproject.Mercado.Livre.entidades.enums.StatusPagamento;
import br.com.zupproject.Mercado.Livre.mocks.MockServicoEmail;
import br.com.zupproject.Mercado.Livre.mocks.MockServicoNotaFiscal;
import br.com.zupproject.Mercado.Livre.mocks.MockServicoRanking;
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
	private MockServicoNotaFiscal nf;

	@Autowired
	private MockServicoEmail mailer;

	@Autowired
	private MockServicoRanking ranking;

	@PostMapping("/paypal")
	public void cadastraPagamentoPaypal(@RequestBody @Valid pagamentoPaypalForm form) throws BindException {
		Pagamento pagamento = form.converter(compraRepository);
		Compra compra = pagamento.getCompra();

		if (!compra.jaPossuiPagamentoDeSucesso()) {
			executaProcessoDePagamento(pagamento, compra);
		} else {
			BindException bindException = new BindException(form, "Nova compra");
			bindException.reject(null, "Este pedido já possui um pagamento processado");
			throw bindException;
		}
	}

	@PostMapping("/pagseguro")
	public void cadastraPagamentoPagseguro(@RequestBody @Valid pagamentoPagseguroForm form) throws BindException {
		Pagamento pagamento = form.converter(compraRepository);
		Compra compra = pagamento.getCompra();

		if (!compra.jaPossuiPagamentoDeSucesso()) {
			executaProcessoDePagamento(pagamento, compra);
		} else {
			BindException bindException = new BindException(form, "Nova compra");
			bindException.reject(null, "Este pedido já possui um pagamento processado");
			throw bindException;
		}

	}

	private void executaProcessoDePagamento(Pagamento pagamento, Compra compra) {
		if (pagamento.getStatusPagamento().equals(StatusPagamento.SUCESSO)) {
			pagamentoRepository.save(pagamento);
			compra.alteraStatusPagamentoParaPago();
			String notaFiscal = nf.emiteNotaFiscal(compra.getId(), compra.getUsuario().getId());
			mailer.enviaEmailPagamentoAceito(notaFiscal, compra.getId(), compra.getProduto().getNome(),
					compra.getUsuario().getUsername());
			ranking.geraPontuacao(compra.getId(), compra.getUsuario().getId());
		} else {
			pagamentoRepository.save(pagamento);
			mailer.enviaEmailPagamentoRecusado(compra.getId(), compra.getProduto().getNome(),
					compra.getServicoPagamento().toString(), compra.getUsuario().getUsername());
		}
	}
}
