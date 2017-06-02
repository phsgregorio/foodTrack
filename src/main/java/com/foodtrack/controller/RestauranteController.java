package com.foodtrack.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodtrack.entity.Restaurante;
import com.foodtrack.service.RestauranteService;

@RestController
@RequestMapping("/foodtrack/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Restaurante> getAllRestaurantes() {
		return restauranteService.getAllRestaurantes();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Restaurante getRestauranteById(@PathVariable("id") int id) {
		return restauranteService.getRestauranteById(id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Collection<Restaurante> deleteRestauranteById(@PathVariable("id") int id) {
		
		restauranteService.removeRestauranteById(id);
		return getAllRestaurantes();
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Restaurante> uptadeRestaurante(@RequestBody Restaurante restaurante) {
		
		restauranteService.updateRestaurante(restaurante);
		return getAllRestaurantes();
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Restaurante> insertRestaurante(@RequestBody Restaurante restaurante) throws Exception {
		
		restauranteService.insertRestaurante(restaurante);
		return getAllRestaurantes();
	}
}