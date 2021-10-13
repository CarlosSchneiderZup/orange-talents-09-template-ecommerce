package br.com.zupproject.Mercado.Livre.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupproject.Mercado.Livre.controllers.forms.OpiniaoForm;
import br.com.zupproject.Mercado.Livre.entidades.Opiniao;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.repositorios.OpiniaoRepository;
import br.com.zupproject.Mercado.Livre.repositorios.ProdutoRepository;
import br.com.zupproject.Mercado.Livre.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

	@Autowired
	private OpiniaoRepository opiniaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping
	public void cadastraOpiniao(@RequestBody @Valid OpiniaoForm form, @AuthenticationPrincipal UserDetails usuario) {
		Optional<Usuario> usuarioLogado = usuarioRepository.findByEmail(usuario.getUsername());

		Opiniao opiniao = form.converter(produtoRepository, usuarioLogado.get());
		opiniaoRepository.save(opiniao);
	}
}
