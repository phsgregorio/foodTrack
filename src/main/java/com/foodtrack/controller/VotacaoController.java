package com.foodtrack.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodtrack.entity.Restaurante;
import com.foodtrack.entity.Votacao;
import com.foodtrack.service.VotacaoService;

@RestController
@RequestMapping("/foodtrack/votacao")
public class VotacaoController {

	@Autowired
	private VotacaoService votacaoService;
	
	/**
	 * Serviço que retorna uma lista com todos os votos realizados na data informada na requisição.
	 * O serviço retorna JSON de acordo com a estrutura do objeto Votacao.
	 * 
	 * @param data String conténdo a data desejada do resultado
	 * @param all boolean apenas diferenciando a assinatura do serviço, informar true
	 * 
	 * Exemplo de requisição:
	 * http://localhost:8080/foodtrack/votacao/2017-06-01/true
	 * 
	 * Retorno:
	 * 
	 * [
	 *   {
	 *     "id": 1,
	 *     "idRestaurante": 1,
	 *     "numeroVotos": 3,
	 *     "dataVotacao": "2017-06-01",
	 *     "restaurante": {
	 *       "id": 1,
	 *       "nome": "San Ro",
	 *       "endereco": "Rua Professor Moraes"
	 *     },
	 *     ...
	 * ]
	 * 
	 * @param data
	 * @param all
	 * @return
	 */
	@RequestMapping(value="/{data}/{all}", method = RequestMethod.GET)
	public Collection<Votacao> getAllByDate(@PathVariable("data") String data, @PathVariable("all") boolean all) {
		return votacaoService.getAllByDate(data);
	}

	/**
	 * Serviço responsável por retornar o restaurante escolhido através da votação.
	 * O serviço retorna JSON de acordo com a estrutura do objeto Restaurante.
	 * 
	 * Exemplo de requisição:
	 * http://localhost:8080/foodtrack/votacao/2017-06-01
	 * 
	 * @param data String conténdo a data desejada do resultado
	 * 
	 * Retorno:
	 * {
	 *   "id": 3,
	 *   "nome": "Autentica",
	 *   "endereco": "Rua Alagoas"
	 * }
	 * 
	 * Caso nenhum restaurante tenha sido escolhido(não ocorreu votação ou restaurante já escolhido durante a semana) o serviço
	 * irá retornar um JSON no seguinte formato:
	 * 
	 * {
	 *   "timestamp": 1496489429858,
	 *   "status": 500,
	 *   "error": "Internal Server Error",
	 *   "exception": "java.lang.Exception",
	 *   "message": "Nenhuma votação ocorreu para o dia 2017-06-03 ou todos os restaurantes votados já foram escolhidos durante a semana.",
	 *   "path": "/foodtrack/votacao/2017-06-03"
	 * }
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{data}", method = RequestMethod.GET)
	public Restaurante getRestauranteDia(@PathVariable("data") String data) throws Exception {
		return votacaoService.getRestauranteDia(data);
	}
}