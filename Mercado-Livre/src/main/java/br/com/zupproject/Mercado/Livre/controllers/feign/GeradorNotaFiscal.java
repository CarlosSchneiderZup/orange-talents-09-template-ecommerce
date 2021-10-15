package br.com.zupproject.Mercado.Livre.controllers.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notas-fiscais", url = "localhost:8080/notas-fiscais")
public interface GeradorNotaFiscal {

	@PostMapping(value = "/{idCompra}/{idUsuario}")
	void enviar(@PathVariable Long idCompra, @PathVariable Long idUsuario);
}
