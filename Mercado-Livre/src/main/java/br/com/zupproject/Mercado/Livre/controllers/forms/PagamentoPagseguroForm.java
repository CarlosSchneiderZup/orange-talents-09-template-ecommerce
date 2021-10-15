package br.com.zupproject.Mercado.Livre.controllers.forms;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupproject.Mercado.Livre.commons.validators.IdFinder;
import br.com.zupproject.Mercado.Livre.entidades.Compra;
import br.com.zupproject.Mercado.Livre.entidades.Pagamento;
import br.com.zupproject.Mercado.Livre.entidades.enums.StatusPagamento;
import br.com.zupproject.Mercado.Livre.repositorios.CompraRepository;

public class PagamentoPagseguroForm {

	@NotBlank
	private String idServico;
	@NotNull
	@IdFinder(domainClass = Compra.class, fieldName = "id")
	private Long idCompra;
	@NotBlank
	private String statusPagamento;

	public Pagamento converter(CompraRepository compraRepository) {
		StatusPagamento status = StatusPagamento.conversorStatusPagseguro(statusPagamento);
		Optional<Compra> compra = compraRepository.findById(idCompra);
		if (compra.isPresent()) {
			return new Pagamento(idServico, status, compra.get());
		}
		return null;
	}

	public String getIdServico() {
		return idServico;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}
}
