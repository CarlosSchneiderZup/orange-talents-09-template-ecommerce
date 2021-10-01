package br.com.zupproject.Mercado.Livre.controllers.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zupproject.Mercado.Livre.customizations.UniqueValue;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;

public class UsuarioForm {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	@NotBlank
	@Size(min = 6)
	private String senha;

	public BCryptPasswordEncoder getEncoder() {
		return encoder;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario converter() {
		String senhaSegura = encoder.encode(senha);
		return new Usuario(email, senhaSegura);
	}
}
