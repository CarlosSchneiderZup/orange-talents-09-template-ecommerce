package br.com.zupproject.Mercado.Livre.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.zupproject.Mercado.Livre.config.security.TokenService;
import br.com.zupproject.Mercado.Livre.controllers.dtos.ProdutoDto;
import br.com.zupproject.Mercado.Livre.controllers.forms.LoginForm;
import br.com.zupproject.Mercado.Livre.controllers.forms.ProdutoForm;
import br.com.zupproject.Mercado.Livre.entidades.Categoria;
import br.com.zupproject.Mercado.Livre.entidades.Produto;
import br.com.zupproject.Mercado.Livre.entidades.Usuario;
import br.com.zupproject.Mercado.Livre.repositorios.CategoriaRepository;
import br.com.zupproject.Mercado.Livre.repositorios.ProdutoRepository;
import br.com.zupproject.Mercado.Livre.repositorios.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProdutoControllerTest {

	private String uri = "/produtos";
	private Produto produto;
	
	private Gson gson = new Gson();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private ObjectMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;
	
	@BeforeEach
	void setUp() {

		Usuario usuarioLogado = new Usuario("usuario@email.com", "superSafePassword");
		usuarioRepository.save(usuarioLogado);
		
		Categoria categoria = new Categoria("Pets", null);
		categoriaRepository.save(categoria);

		Usuario usuario = new Usuario("usuarioDono@email.com", "1234567");
		usuarioRepository.save(usuario);
		produto = new Produto("Coleira canina", BigDecimal.valueOf(19.90), 10, getCaracteristicas(), getDescricao(),
				categoria, usuario);
		produtoRepository.save(produto);
	}

	@Test
	void deveRetornarUmProdutoDtoEStatus200ParaUmProdutoValido() throws Exception {

		ProdutoDto produtoDto = new ProdutoDto(produto);
		String response = mapper.writeValueAsString(produtoDto);

		MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.get(uri.concat("/1"));

		mockMvc.perform(chamada).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().json(response));

	}

	@Test
	void deveRetornarStatus404ParaProdutoComIdInvalido() throws Exception {

		MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.get(uri.concat("/1000"));

		mockMvc.perform(chamada).andExpect(MockMvcResultMatchers.status().is(404));

	}

	@Test
	void deveCadastrarUmNovoProdutoERetornarStatus200() throws Exception {
		
		LoginForm login = new LoginForm();
		login.setEmail("usuario@email.com");
		login.setSenha("superSafePassword");
		
		String token = tokenService.gerarToken(authenticationManager.authenticate(login.converter()));

		ProdutoForm novoProduto = new ProdutoForm("Saco de ração Three dogs", new BigDecimal(9500.0), 1,
				getCaracteristicas(), "Acompanha de brinde uma bolinha", 1L);
		
		mockMvc.perform(
				MockMvcRequestBuilders.post(uri)
				.header("Authorization", "Bearer " + token)
				.content(gson.toJson(novoProduto))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

	private Map<String, String> getCaracteristicas() {
		Map<String, String> caracteristicas = new HashMap<>();
		caracteristicas.put("Cor", "Verde");
		caracteristicas.put("Tamanho", "38 cm");
		caracteristicas.put("Garantia", "6 meses");
		caracteristicas.put("Anti pulgas", "Não");
		return caracteristicas;
	}

	private String getDescricao() {
		return "Coleiras caninas com peitoral e duas argolas de metal para guia";
	}

}
