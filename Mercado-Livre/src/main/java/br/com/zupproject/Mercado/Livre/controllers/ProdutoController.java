package br.com.zupproject.Mercado.Livre.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupproject.Mercado.Livre.controllers.forms.ImagemProdutoForm;
import br.com.zupproject.Mercado.Livre.controllers.forms.ProdutoForm;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.mocks.MockServicoUpload;
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
	
	@Autowired
	private MockServicoUpload mockServicoUpload;
	
	@PostMapping
	public void cadastraProduto(@RequestBody @Valid ProdutoForm form) {
		Optional<Usuario> usuarioLogado = usuarioRepository.findById(1L);
		
		Produto produto = form.converter(categoriaRepository, usuarioLogado.get());
		produtoRepository.save(produto);
	}
	
	@PostMapping(value = "/img/{id}")
	public void cadastraImagem(@PathVariable Long id, @Valid ImagemProdutoForm form) {
		Optional<Usuario> usuarioLogado = usuarioRepository.findById(2L);
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			Produto oProduto = produto.get();
			
			if(usuarioLogado.isPresent() && oProduto.ehDono(usuarioLogado.get())) {
				List<String> linksImagem = mockServicoUpload.enviaImagens(form.getImagens());
				oProduto.associaLinks(linksImagem);
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
