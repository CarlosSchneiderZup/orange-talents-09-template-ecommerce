package br.com.zupproject.Mercado.Livre.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@ManyToOne(optional = true)
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {
	}

	public Categoria(String nome, Categoria categoriaMae) {
		super();
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
