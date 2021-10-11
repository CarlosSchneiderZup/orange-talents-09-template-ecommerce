package br.com.zupproject.Mercado.Livre.commons.exceptions;

public class EstoqueInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EstoqueInvalidoException(String message) {
		super(message);
	}

}
