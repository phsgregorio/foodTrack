package com.foodtrack.ws.service;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.foodtrack.entity.Funcionario;
import com.foodtrack.service.FuncionarioService;
import com.foodtrack.ws.AbstractTest;

@Rollback
public class VotacaoServiceTest extends AbstractTest{

	@Autowired
	private FuncionarioService funcionarioService;
	
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
		Assert.assertEquals("Falha - Número de registros não esperado", 3, list.size());
	}
}
