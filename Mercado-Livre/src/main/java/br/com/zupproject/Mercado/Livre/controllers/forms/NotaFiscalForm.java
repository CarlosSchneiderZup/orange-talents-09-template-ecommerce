package br.com.zupproject.Mercado.Livre.controllers.forms;

import javax.validation.constraints.NotNull;

public class NotaFiscalForm {

	@NotNull
	private Long idCompra;
	@NotNull
	private Long idUsuario;

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	@Override
	public String toString() {
		return "[idCompra=" + idCompra + ", idUsuario=" + idUsuario + "]";
	}

}
