package br.com.zupproject.Mercado.Livre.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private BigDecimal valor;
	@Column(nullable = false)
	private Integer quantidade;
	@ElementCollection
	private Map<String, String> caracteristicas = new HashMap<>();

	@Column(nullable = false, length = 1000)
	private String descricao;

	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private Usuario donoProduto;
	private LocalDateTime horaCadastro = LocalDateTime.now();

	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, Integer quantidade, Map<String, String> caracteristicas,
			String descricao, Categoria categoria, Usuario donoProduto) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoria = categoria;
		this.donoProduto = donoProduto;
	}

}
