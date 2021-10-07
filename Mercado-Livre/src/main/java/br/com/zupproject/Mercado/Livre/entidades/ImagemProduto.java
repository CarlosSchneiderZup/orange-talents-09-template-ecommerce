package br.com.zupproject.Mercado.Livre.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NotNull
	private Produto produto;
	@NotBlank
	@URL
	private String link;

	public ImagemProduto() {}
	
	public ImagemProduto(Produto produto, String link) {
		this.produto = produto;
		this.link = link;
	}

	public String getLink() {
		return link;
	}
}
