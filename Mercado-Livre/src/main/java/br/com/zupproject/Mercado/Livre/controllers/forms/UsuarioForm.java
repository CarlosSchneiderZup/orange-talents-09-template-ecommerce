package br.com.zupproject.Mercado.Livre.controllers.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupproject.Mercado.Livre.commons.validators.UniqueValue;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;

public class UsuarioForm {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	@NotBlank
	@Size(min = 6)
	private String senha;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario converter() {
		return new Usuario(email, senha);
	}
}
