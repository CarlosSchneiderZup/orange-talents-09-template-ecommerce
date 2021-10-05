package br.com.zupproject.Mercado.Livre.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupproject.Mercado.Livre.entidades.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
