package br.com.zupproject.Mercado.Livre.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.zupproject.Mercado.Livre.entidades.enums.ServicoPagamento;
import br.com.zupproject.Mercado.Livre.entidades.enums.StatusCompra;
import br.com.zupproject.Mercado.Livre.entidades.enums.StatusPagamento;

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
	@OneToMany(mappedBy = "compra")
	private List<Pagamento> pagamentos = new ArrayList<>();

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

	public Usuario getUsuario() {
		return usuario;
	}

	public boolean jaPossuiPagamentoDeSucesso() {

		if (pagamentos.isEmpty()) {
			return false;
		}
		for (Pagamento pagamento : pagamentos) {
			if (pagamento.getStatusPagamento().equals(StatusPagamento.SUCESSO)) {
				return true;
			}
		}
		return false;
	}

	public void alteraStatusPagamentoParaPago() {
		statusCompra = StatusCompra.PAGA;
	}
}