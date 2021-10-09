package br.com.zupproject.Mercado.Livre.entidades.enums;

public enum StatusPagamento {
	SUCESSO, ERRO;
	
	public static StatusPagamento conversorStatusPaypal(int status) {
		return status == 0 ? ERRO : SUCESSO;
	}
	
	public static StatusPagamento conversorStatusPagseguro(String status) {
		return status.equals("Falha") ? ERRO : SUCESSO;
	}

}
