package br.com.zupproject.Mercado.Livre.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupproject.Mercado.Livre.controllers.forms.PerguntaForm;
import br.com.zupproject.Mercado.Livre.entidades.Pergunta;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.mocks.MockServicoEmail;
import br.com.zupproject.Mercado.Livre.repositorios.PerguntaRepository;
import br.com.zupproject.Mercado.Livre.repositorios.ProdutoRepository;
import br.com.zupproject.Mercado.Livre.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

	@Autowired
	private PerguntaRepository perguntaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private MockServicoEmail mailer;

	@PostMapping(value = "/{id}")
	public void cadastraPergunta(@RequestBody @Valid PerguntaForm form, @PathVariable Long id, @AuthenticationPrincipal UserDetails usuario) {
		
		Optional<Usuario> usuarioLogado = usuarioRepository.findByEmail(usuario.getUsername());
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isPresent()) {
			Pergunta pergunta = form.converter(usuarioLogado.get(), produto.get());
			perguntaRepository.save(pergunta);
			
			String email = produto.get().getDonoProduto().getUsername();
			Long idProduto = produto.get().getId();
			mailer.enviaEmailPergunta(email, idProduto);
			
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}
}
