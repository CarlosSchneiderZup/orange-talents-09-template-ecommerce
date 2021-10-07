package br.com.zupproject.Mercado.Livre.controllers.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.zupproject.Mercado.Livre.entidades.ImagemProduto;
import br.com.zupproject.Mercado.Livre.entidades.Opiniao;
import br.com.zupproject.Mercado.Livre.entidades.Pergunta;
import br.com.zupproject.Mercado.Livre.entidades.Produto;

public class ProdutoDto {

	private Long id;
	private String nome;
	private BigDecimal valor;
	private Integer quantidade;
	private Map<String, String> caracteristicas = new HashMap<>();
	private Map<String, String> dadosAvaliacao = new HashMap<>();
	private List<ImagemProduto> imagensDoProduto = new ArrayList<>();
	private List<Opiniao> opinioes = new ArrayList<>();
	private List<Pergunta> perguntas = new ArrayList<>();
	private String descricao;
	private String nomeCategoria;
	private LocalDateTime horaCadastro;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.descricao = produto.getDescricao();
		this.caracteristicas = produto.getCaracteristicas();
		this.dadosAvaliacao = produto.geraDetalhesAvaliacao();
		this.imagensDoProduto = produto.getImagensDoProduto();
		this.opinioes = produto.getOpinioes();
		this.perguntas = produto.getPerguntas();
		this.nomeCategoria = produto.getCategoria().getNome();
		this.horaCadastro = produto.getHoraCadastro();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Map<String, String> getCaracteristicas() {
		return caracteristicas;
	}

	public Map<String, String> getDadosAvaliacao() {
		return dadosAvaliacao;
	}

	public List<ImagemProduto> getImagensDoProduto() {
		return imagensDoProduto;
	}

	public List<Opiniao> getOpinioes() {
		return opinioes;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public LocalDateTime getHoraCadastro() {
		return horaCadastro;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}
}
