package br.com.zupproject.Mercado.Livre.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupproject.Mercado.Livre.controllers.forms.ProdutoForm;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.repositorios.CategoriaRepository;
import br.com.zupproject.Mercado.Livre.repositorios.ProdutoRepository;
import br.com.zupproject.Mercado.Livre.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid ProdutoForm form) {
		
		Optional<Usuario> usuarioLogado = usuarioRepository.findById(1L);
		
		Produto produto = form.converter(categoriaRepository, usuarioLogado.get());
		produtoRepository.save(produto);
	}
}
