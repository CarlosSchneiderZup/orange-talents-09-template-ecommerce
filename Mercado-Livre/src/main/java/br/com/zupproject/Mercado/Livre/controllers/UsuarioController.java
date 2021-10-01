package br.com.zupproject.Mercado.Livre.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupproject.Mercado.Livre.controllers.forms.UsuarioForm;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.repositorios.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public void cadastraUsuario(@RequestBody @Valid UsuarioForm form) {
		
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);
		
	}
	
}
