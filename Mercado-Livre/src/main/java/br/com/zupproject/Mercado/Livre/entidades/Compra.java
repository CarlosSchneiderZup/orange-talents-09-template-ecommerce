package br.com.zupproject.Mercado.Livre.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zupproject.Mercado.Livre.entidades.enums.ServicoPagamento;
import br.com.zupproject.Mercado.Livre.entidades.enums.StatusCompra;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Integer quantidade;
	@Column(nullable = false)
	private BigDecimal valor;
	private LocalDateTime horaCompra = LocalDateTime.now();

	@Enumerated
	private StatusCompra statusCompra = StatusCompra.INICIADA;
	@Column(nullable = false)
	@Enumerated
	private ServicoPagamento servicoPagamento;

	@ManyToOne(optional = false)
	private Usuario usuario;
	@ManyToOne(optional = false)
	private Produto produto;

	public Compra() {
	}

	public Compra(Integer quantidade, BigDecimal valor, ServicoPagamento servicoPagamento, Usuario usuario,
			Produto produto) {
		super();
		this.quantidade = quantidade;
		this.valor = valor;
		this.servicoPagamento = servicoPagamento;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public ServicoPagamento getServicoPagamento() {
		return servicoPagamento;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
}