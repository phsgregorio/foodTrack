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

import com.foodtrack.entity.Restaurante;
import com.foodtrack.service.RestauranteService;
import com.foodtrack.ws.AbstractTest;

@Rollback
public class RestauranteServiceTest extends AbstractTest{

	@Autowired
	private RestauranteService restauranteService;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();	

	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testGetAllRestaurantes() {
		Collection<Restaurante> list = restauranteService.getAllRestaurantes();
		Assert.assertNotNull("Falha - O valor null não era esperado", list);
	}
	
	@Test
	public void testInsertRestaurante() throws Exception {
		
		Restaurante restaurante =  new Restaurante(10, "Teste", "Teste");
		restauranteService.insertRestaurante(restaurante);
	}
	
	@Test
	public void testParametrosInsertRestaurante() throws Exception {
		
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("devem ser devidamente informados");
	    
		Restaurante restaurante =  new Restaurante(11, null, null);
		restauranteService.insertRestaurante(restaurante);
	}
}
