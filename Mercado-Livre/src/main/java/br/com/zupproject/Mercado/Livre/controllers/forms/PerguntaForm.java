package br.com.zupproject.Mercado.Livre.controllers.forms;

import javax.validation.constraints.NotBlank;

import br.com.zupproject.Mercado.Livre.entidades.Pergunta;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;

public class PerguntaForm {
	
	@NotBlank
	private String titulo;

	public String getTitulo() {
		return titulo;
	}
	
	public Pergunta converter(Usuario usuario, Produto produto) {
		return new Pergunta(titulo, usuario, produto);
	}
}
