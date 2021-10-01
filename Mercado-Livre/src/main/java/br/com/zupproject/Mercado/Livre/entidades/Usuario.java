package br.com.zupproject.Mercado.Livre.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String senha;
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	public Usuario() {
		
	}
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
}
