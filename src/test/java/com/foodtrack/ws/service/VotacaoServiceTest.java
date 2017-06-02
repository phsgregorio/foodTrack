package com.foodtrack.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.foodtrack.entity.Funcionario;
import com.foodtrack.entity.Restaurante;
import com.foodtrack.entity.Votacao;
import com.foodtrack.entity.VotoFuncionario;
import com.foodtrack.service.FuncionarioService;
import com.foodtrack.service.RestauranteService;
import com.foodtrack.service.VotacaoService;
import com.foodtrack.service.VotoFuncionarioService;
import com.foodtrack.ws.AbstractTest;

@Rollback
public class VotacaoServiceTest extends AbstractTest{

	@Autowired
	private VotacaoService votacaoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private VotoFuncionarioService votoFuncionarioService;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();	
	
	ArrayList<Votacao> votosPorRestaurante;
	private ArrayList<VotoFuncionario> votoFuncionarios;
	private ArrayList<Funcionario> funcionarios;
	private ArrayList<Restaurante> restaurantes;
	private String data;
	
	@Before
	public void setUp() throws Exception {
		
		data = "2017-06-02";
		
		funcionarios = new ArrayList<Funcionario>();
		restaurantes = new ArrayList<Restaurante>();
		votoFuncionarios = new ArrayList<VotoFuncionario>();

		// Inserindo dez funcionários ativos
		for (int i = 20; i < 30; i++) {
			
			Funcionario funcionario = new Funcionario(i, "TesteFuncionario{"+i+"}", Funcionario.ATIVO);
			funcionarios.add(funcionario);
			funcionarioService.insertFuncionario(funcionario);
		}
		
		// Inserindo três restaurantes
		for (int i = 20; i < 23; i++) {
			Restaurante restaurante = new Restaurante(i, "TesteRestaurante{"+i+"}", "Teste");
			restaurantes.add(restaurante);
			restauranteService.insertRestaurante(restaurante);
		}
		
		// Preparando inserção de dez votos
		votoFuncionarios.add(new VotoFuncionario(20, 20, data));
		votoFuncionarios.add(new VotoFuncionario(21, 20, data));
		votoFuncionarios.add(new VotoFuncionario(22, 20, data));
		votoFuncionarios.add(new VotoFuncionario(23, 21, data));
		votoFuncionarios.add(new VotoFuncionario(24, 21, data));
		votoFuncionarios.add(new VotoFuncionario(25, 22, data));
		votoFuncionarios.add(new VotoFuncionario(26, 22, data));
		votoFuncionarios.add(new VotoFuncionario(27, 20, data));
		votoFuncionarios.add(new VotoFuncionario(28, 21, data));
		votoFuncionarios.add(new VotoFuncionario(29, 20, data));
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testGetAll() {
		
		Collection<Votacao> list = votacaoService.getAll();
		Assert.assertNotNull("Falha - O valor null não era esperado", list);
	}
	
	@Test
	public void testGetAllByDate() {
		
		Collection<Votacao> list = votacaoService.getAllByDate(data);
		Assert.assertNotNull("Falha - O valor null não era esperado", list);
	}

	/**
	 * Método auxiliar para inserção dos votos do funcionário 
	 * @param votoFuncionarios
	 * @throws Exception
	 */
	private void insertVotoFuncionarios(ArrayList<VotoFuncionario> votoFuncionarios) throws Exception {

		for (VotoFuncionario votoFuncionario : votoFuncionarios) {
			
			votoFuncionarioService.insertVotoFuncionario(votoFuncionario);
			votacaoService.insertVotacao(votoFuncionario);
		}
	}
	
	@Test
	public void testInsertUpdateVotacao() throws Exception {

		// Inserção dos votos dos funcionários
		insertVotoFuncionarios(votoFuncionarios);
		
		votosPorRestaurante = (ArrayList<Votacao>) votacaoService.getAllByDate(data);

		// Conferindo o número de registros na tabela votações(registros por restaurante) para a data informada
		Assert.assertEquals("O número de votos por restaurante deveria ser " + restaurantes.size(), restaurantes.size(), votosPorRestaurante.size());
		Assert.assertEquals("O número de votos do restaurante TesteRestaurante{20} deveria ser 5", 5, votosPorRestaurante.get(0).getNumeroVotos());
		Assert.assertEquals("O número de votos do restaurante TesteRestaurante{21} deveria ser 5", 3, votosPorRestaurante.get(1).getNumeroVotos());
		Assert.assertEquals("O número de votos do restaurante TesteRestaurante{22} deveria ser 5", 2, votosPorRestaurante.get(2).getNumeroVotos());
		
		Votacao votacaoRestauranteDia = votacaoService.getRestauranteDia(data);
		System.out.println(votacaoRestauranteDia.getRestaurante().getNome()+ ", " + votacaoRestauranteDia.getRestaurante().getEndereco() + ", " + data);
	}
	
	@Test
	public void testRestauranteJaEscolhido() throws Exception {
		
		ArrayList<VotoFuncionario> votoFuncionariosDiaAnterior = new ArrayList<VotoFuncionario>();
		String dataDeOntem = "2017-06-01";
		
		// Preparando inserção de dez votos
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(20, 20, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(21, 20, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(22, 20, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(23, 21, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(24, 21, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(25, 22, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(26, 22, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(27, 20, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(28, 21, dataDeOntem));
		votoFuncionariosDiaAnterior.add(new VotoFuncionario(29, 20, dataDeOntem));
		
		// Inserção dos votos do dia anterior
		insertVotoFuncionarios(votoFuncionariosDiaAnterior);
		
		// Conferir se o restaurante mais votado de {data} já foi escolhido na última semana
		boolean jaVotado = votacaoService.isRestauranteJaEscolhido(votoFuncionarios.get(0).getRestaurante(), data);
		
		Assert.assertEquals("O restaurante " + votoFuncionarios.get(0).getRestaurante().getNome() + " já foi votado anteriormente.", true, jaVotado);
	}

	@Test
	public void testParametrosInvalidosInsertVotacao() throws Exception {
		
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("As propriedades do objeto VotoFuncionario");
		
		VotoFuncionario votoFuncionario = new VotoFuncionario(29, 0, null);
		votacaoService.insertVotacao(votoFuncionario);
	}
}
