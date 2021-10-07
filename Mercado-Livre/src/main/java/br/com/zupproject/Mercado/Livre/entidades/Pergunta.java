package br.com.zupproject.Mercado.Livre.entidades;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;

	@ManyToOne(optional = false)
	private Usuario usuario;
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "produto_id")
	private Produto produto;
	private LocalDateTime horaCriacao = LocalDateTime.now();

	public Pergunta() {
	}

	public Pergunta(String titulo, Usuario usuario, Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public LocalDateTime getHoraCriacao() {
		return horaCriacao;
	}

	public String getTitulo() {
		return titulo;
	}
}
