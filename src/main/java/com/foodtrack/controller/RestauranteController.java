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
	
	/**
	 * Serviço responsável pelo retorno da lista de restaurantes da aplicação.
	 * O serviço retorna um JSON com a estrutura da lista de Restaurantes.
	 * 
	 * Exemplo de requisição:
	 * http://localhost:8080/foodtrack/restaurantes
	 * 
	 * Retorno:
	 * 
	 * [
	 *   {
	 *     "id": 1,
	 *     "nome": "San Ro",
	 *     "endereco": "Rua Professor Moraes"
	 *   },
	 *   {
	 *     "id": 2,
	 *     "nome": "Yan Shan Zai",
	 *     "endereco": "Avenida Getulio Vargas"
	 *   },
	 *   ...
	 * ]
	 * 
	 * @param restaurante
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Restaurante> uptadeRestaurante(@RequestBody Restaurante restaurante) throws Exception {
		
		restauranteService.updateRestaurante(restaurante);
		return getAllRestaurantes();
	}
	
	/**
	 * Serviço responsável pela inserção de um novo restaurante.
	 * O serviço consome um json como entrada conténdo as informações do restaurante.
	 * 
	 * @param Restaurante restaurante
	 * 
	 * Exemplo de requisição:
	 * http://localhost:8080/foodtrack/restaurantes
	 * 
	 * POST(application/json)
	 * 
	 * {
	 *     "id": 4,
	 *     "nome": "Piu Pane",
	 *     "endereco": "Rua Professor Moraes"
	 * }
	 * 
	 * @param restaurante
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Restaurante> insertRestaurante(@RequestBody Restaurante restaurante) throws Exception {
		
		restauranteService.insertRestaurante(restaurante);
		return getAllRestaurantes();
	}
}