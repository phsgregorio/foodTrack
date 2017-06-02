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
import com.foodtrack.service.FuncionarioService;
import com.foodtrack.ws.AbstractTest;

@Rollback
public class FuncionarioServiceTest extends AbstractTest{

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();	

	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testGetAllFuncionarios() {
		
		Collection<Funcionario> list = funcionarioService.getAllFuncionarios();		
		Assert.assertNotNull("Falha - O valor null não era esperado", list);
	}
	
	@Test
	public void testInsertFuncionario() throws Exception {
		
		Funcionario funcionario =  new Funcionario(10, "Teste", Funcionario.ATIVO);
		funcionarioService.insertFuncionario(funcionario);
	}
	
	@Test
	public void testParametrosInsertFuncionario() throws Exception {
		
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("devem ser devidamente informados");
	    
		Funcionario funcionario =  new Funcionario(11, null, null);
		funcionarioService.insertFuncionario(funcionario);
	}
	
	@Test
	public void testParametroAtivoInsertFuncinario() throws Exception {
		
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("O valor informado para o parametro");

		Funcionario funcionario =  new Funcionario(12, "Teste", "Teste");
		funcionarioService.insertFuncionario(funcionario);
	}
}
