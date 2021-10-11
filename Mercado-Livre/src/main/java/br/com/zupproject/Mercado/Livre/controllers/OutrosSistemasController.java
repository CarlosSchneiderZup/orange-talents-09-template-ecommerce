package br.com.zupproject.Mercado.Livre.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupproject.Mercado.Livre.controllers.forms.NotaFiscalForm;
import br.com.zupproject.Mercado.Livre.controllers.forms.RankingForm;

@RestController
public class OutrosSistemasController {

	
	@PostMapping(value = "/notas-fiscais")
	public String cadastraNotaFiscal(@RequestBody @Valid NotaFiscalForm form) {
		return "Criando nota fiscal... " + form.toString();
	}
	
	@PostMapping(value = "/rankings")
	public String cadastraRanking(@RequestBody @Valid RankingForm form) {
		return "Gerando pontuação do ranking para -> " + form.toString();
	}
}
