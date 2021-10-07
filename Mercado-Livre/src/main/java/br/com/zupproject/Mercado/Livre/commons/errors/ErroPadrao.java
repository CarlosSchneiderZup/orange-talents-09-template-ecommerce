package br.com.zupproject.Mercado.Livre.commons.errors;

public class ErroPadrao {

	String campo;
	String erro;

	public ErroPadrao(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
