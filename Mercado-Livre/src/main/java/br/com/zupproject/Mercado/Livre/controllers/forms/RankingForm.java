package br.com.zupproject.Mercado.Livre.controllers.forms;

import javax.validation.constraints.NotNull;

public class RankingForm {

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idDonoProduto;

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdDonoProduto() {
		return idDonoProduto;
	}

	@Override
	public String toString() {
		return "[idCompra=" + idCompra + ", idDonoProduto=" + idDonoProduto + "]";
	}

}
