package br.com.zupproject.Mercado.Livre.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupproject.Mercado.Livre.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
