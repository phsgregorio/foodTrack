package com.foodtrack.ws.service;

import java.util.Collection;

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
import com.foodtrack.entity.VotoFuncionario;
import com.foodtrack.service.FuncionarioService;
import com.foodtrack.service.RestauranteService;
import com.foodtrack.service.VotoFuncionarioService;
import com.foodtrack.ws.AbstractTest;

@Rollback
public class VotoFuncionarioServiceTest extends AbstractTest{

	@Autowired
	private VotoFuncionarioService votoFuncionarioService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();	
	
	private Funcionario funcionarioAtivo;
	private Funcionario funcionarioInativo;
	private Restaurante restaurante;
	private String data;

	@Before
	public void setUp() throws Exception {
		
		data = "2017-06-02";
		
		funcionarioAtivo = new Funcionario(15, "Teste", Funcionario.ATIVO);
		funcionarioService.insertFuncionario(funcionarioAtivo);
		
		funcionarioInativo = new Funcionario(16, "Teste", Funcionario.INATIVO);
		funcionarioService.insertFuncionario(funcionarioInativo);
		
		restaurante = new Restaurante(15, "Teste", "Teste");
		restauranteService.insertRestaurante(restaurante);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testGetAllByDateFuncionarios() {
		
		Collection<VotoFuncionario> list = votoFuncionarioService.getAllByDate(data);
		Assert.assertNotNull("Falha - O valor null não era esperado", list);
		
		for (VotoFuncionario votoFuncionario : list) {
			Assert.assertEquals("Data diferente da data consultada", data, votoFuncionario.getDataVotacao());
		}
	}
	
	@Test
	public void testInsertVotoFuncionario() throws Exception {

		VotoFuncionario votoFuncionario = new VotoFuncionario(funcionarioAtivo.getId(), restaurante.getId(), data);
		votoFuncionarioService.insertVotoFuncionario(votoFuncionario);
	}
	
	@Test
	public void testInsertVotoFuncionarioDesativado() throws Exception {
		
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Funcionario desativado");
	    
		VotoFuncionario votoFuncionario = new VotoFuncionario(funcionarioInativo.getId(), restaurante.getId(), data);
		votoFuncionarioService.insertVotoFuncionario(votoFuncionario);
	}
	
	@Test
	public void testInsertVotouAnteriormente() throws Exception {

		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Funcionario votou anteriormente");

		VotoFuncionario votoFuncionario = new VotoFuncionario(funcionarioAtivo.getId(), restaurante.getId(), data);
		votoFuncionarioService.insertVotoFuncionario(votoFuncionario);
	}
}
