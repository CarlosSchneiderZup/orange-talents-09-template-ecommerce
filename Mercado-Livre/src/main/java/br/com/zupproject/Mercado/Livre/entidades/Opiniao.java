package br.com.zupproject.Mercado.Livre.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private Integer avaliacao;
	@Column(nullable = false)
	private String descricao;

	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "produto_id")
	private Produto produtoAlvo;
	@ManyToOne(optional = false)
	private Usuario usuario;

	public Opiniao() {
	}

	public Opiniao(String titulo, Integer avaliacao, String descricao, Produto produtoAlvo, Usuario usuario) {
		super();
		this.titulo = titulo;
		this.avaliacao = avaliacao;
		this.descricao = descricao;
		this.produtoAlvo = produtoAlvo;
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

}
