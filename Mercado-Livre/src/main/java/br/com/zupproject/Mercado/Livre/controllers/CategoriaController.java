package br.com.zupproject.Mercado.Livre.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupproject.Mercado.Livre.controllers.forms.CategoriaForm;
import br.com.zupproject.Mercado.Livre.entidades.Categoria;
import br.com.zupproject.Mercado.Livre.repositorios.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public void cadastraCategoria(@RequestBody @Valid CategoriaForm form) {
		
		Categoria categoria = form.converter(categoriaRepository);
		categoriaRepository.save(categoria);
		
	}
}
