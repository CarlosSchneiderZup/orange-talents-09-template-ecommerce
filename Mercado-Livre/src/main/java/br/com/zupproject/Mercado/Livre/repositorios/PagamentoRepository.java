package br.com.zupproject.Mercado.Livre.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupproject.Mercado.Livre.entidades.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
