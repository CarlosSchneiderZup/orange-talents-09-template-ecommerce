package br.com.zupproject.Mercado.Livre.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemasController {

	
	@PostMapping(value = "/notas-fiscais/{idCompra}/{idUsuario}")
	public String cadastraNotaFiscal(@PathVariable Long idCompra, @PathVariable Long idUsuario) {
		return "Criando nota fiscal da compra de id " + idCompra + ", efetuada pelo usuario de id " + idUsuario;
	}
	
	@PostMapping(value = "/rankings/{idCompra}/{idVendedor}")
	public String cadastraRanking(@PathVariable Long idCompra, @PathVariable Long idVendedor) {
		return "Gerando pontuação do ranking pela compra " + idCompra + " para -> " + idVendedor;
	}
}
