package br.com.zupproject.Mercado.Livre.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupproject.Mercado.Livre.entidades.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
