package br.com.zupproject.Mercado.Livre.entidades;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.zupproject.Mercado.Livre.entidades.enums.StatusPagamento;

@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String idServico;
	@Enumerated
	private StatusPagamento statusPagamento;
	private LocalDateTime horaDoPagamento = LocalDateTime.now();
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "produto_id")
	private Compra compra;

	@Deprecated
	public Pagamento() {

	}

	public Pagamento(String idServico, StatusPagamento statusPagamento, Compra compra) {
		super();
		this.idServico = idServico;
		this.statusPagamento = statusPagamento;
		this.compra = compra;
	}

	public Compra getCompra() {
		return compra;
	}

	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}
}
