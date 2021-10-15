package br.com.zupproject.Mercado.Livre.controllers.forms;

import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupproject.Mercado.Livre.commons.validators.IdFinder;
import br.com.zupproject.Mercado.Livre.entidades.Compra;
import br.com.zupproject.Mercado.Livre.entidades.Pagamento;
import br.com.zupproject.Mercado.Livre.entidades.enums.StatusPagamento;
import br.com.zupproject.Mercado.Livre.repositorios.CompraRepository;

public class pagamentoPaypalForm {

	@NotBlank
	private String idServico;
	@NotNull
	@IdFinder(domainClass = Compra.class, fieldName = "id")
	private Long idCompra;
	@NotNull
	@Min(0)
	@Max(1)
	private Integer statusPagamento;

	public Pagamento converter(CompraRepository compraRepository) {
		StatusPagamento status = StatusPagamento.conversorStatusPaypal(statusPagamento);
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

	public Integer getStatusPagamento() {
		return statusPagamento;
	}
}
