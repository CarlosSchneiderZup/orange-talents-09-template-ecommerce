package br.com.zupproject.Mercado.Livre.mocks;

import org.springframework.stereotype.Component;

import br.com.zupproject.Mercado.Livre.entidades.enums.StatusCompra;

@Component
public class MockServicoRanking {

	public void geraPontuacao(Long idCompra, Long idVendedor) {
		System.out.println("Gerando uma pontuação para o vendedor " + idVendedor);
		System.out.println("pela venda do produto com o id de compra " + idCompra);
	}
}
