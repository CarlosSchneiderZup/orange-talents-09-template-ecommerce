package br.com.zupproject.Mercado.Livre.mocks;

import org.springframework.stereotype.Component;

import br.com.zupproject.Mercado.Livre.entidades.Produto;

@Component
public class MockServicoEmail {
	
	public void enviaEmail(Produto produto) {
		String email = produto.getDonoProduto().getUsername();
		System.out.println("Email enviado para o destino: " + email);
		System.out.println("Referente ao produto de id " + produto.getId());
	}

}
