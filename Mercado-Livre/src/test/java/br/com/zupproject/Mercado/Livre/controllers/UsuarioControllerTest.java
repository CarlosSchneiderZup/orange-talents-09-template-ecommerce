package br.com.zupproject.Mercado.Livre.controllers;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.repositorios.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UsuarioControllerTest {

	private String uri = "/usuarios";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void deveriaFazerOCadastroDeUmNovoUsuarioERetornarStatus200() throws Exception {

		String json = "{\"email\":\"emailfake@email.com\", \"senha\": \"123456\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void naoDeveFazerOCadastroDeUmNovoUsuarioComEmailDeFormatoInvalidoERetornarStatus400() throws Exception {

		String json = "{\"email\":\"emailfake.email.com\", \"senha\": \"123456\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	void naoDeveFazerOCadastroDeUmNovoUsuarioComSenhaMenorQueSeisDigitosERetornarStatus400() throws Exception {

		String json = "{\"email\":\"emailfake@email.com\", \"senha\": \"A\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	void naoDeveFazerOCadastroDeUmNovoUsuarioComEmailRepetidoERetornarStatus400() throws Exception {

		Usuario usuario = new Usuario("emailfake@email.com", "umaSenha");
		usuarioRepository.save(usuario);
		
		String json = "{\"email\":\"emailfake@email.com\", \"senha\": \"123456\"}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
}
