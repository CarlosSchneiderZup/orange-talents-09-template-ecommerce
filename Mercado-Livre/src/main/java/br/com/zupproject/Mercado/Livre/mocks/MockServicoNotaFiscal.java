package br.com.zupproject.Mercado.Livre.mocks;

import org.springframework.stereotype.Component;

@Component
public class MockServicoNotaFiscal {

	public String emiteNotaFiscal(Long idCompra, Long idUsuario) {
		return "Nota fiscal para o produto de id " + idCompra + " vendido para o usuario " + idUsuario;
	}
}
