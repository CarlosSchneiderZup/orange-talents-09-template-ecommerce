package br.com.zupproject.Mercado.Livre.commons.exceptions;

public class PagamentoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PagamentoInvalidoException(String message) {
		super(message);
	}

}
