package br.com.zupproject.Mercado.Livre.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private List<ImagemProduto> imagensDoProduto = new ArrayList<>();

	@OneToMany(mappedBy = "produtoAlvo")
	private List<Opiniao> opinioes = new ArrayList<>();
	@OneToMany(mappedBy = "produto")
	private List<Pergunta> perguntas = new ArrayList<>();

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

	public void associaLinks(List<String> linksImagem) {
		List<ImagemProduto> imagens = linksImagem.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toList());

		this.imagensDoProduto.addAll(imagens);
	}

	public boolean ehDono(Usuario usuario) {
		return this.donoProduto.equals(usuario);
	}

	public Map<String, String> geraDetalhesAvaliacao() {
		Map<String, String> detalhes = new HashMap<>();

		Integer numeroAvaliacoes = opinioes.size();
		Double media = opinioes.stream().flatMapToInt(opiniao -> IntStream.of(opiniao.getAvaliacao())).average()
				.orElse(0.0);
		detalhes.put("totalAvaliacoes", numeroAvaliacoes.toString());
		detalhes.put("mediaAvaliacoes", media.toString());
		return detalhes;
	}

	public boolean existeEstoqueParaCompra(Integer quantidadeDesejada) {

		if (quantidade <= 0) {
			return false;
		}

		return quantidadeDesejada <= quantidade;
	}

	public void abateEstoque(Integer quantidadeComprada) {
		quantidade = quantidade - quantidadeComprada;
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

	public List<ImagemProduto> getImagensDoProduto() {
		return imagensDoProduto;
	}

	public List<Opiniao> getOpinioes() {
		return opinioes;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getDonoProduto() {
		return donoProduto;
	}

	public LocalDateTime getHoraCadastro() {
		return horaCadastro;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}
}
