package com.foodtrack.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodtrack.entity.VotoFuncionario;
import com.foodtrack.service.VotacaoService;
import com.foodtrack.service.VotoFuncionarioService;

@RestController
@RequestMapping("/foodtrack/votofuncionarios")
public class VotoFuncionarioController {

	@Autowired
	private VotoFuncionarioService votoFuncionarioService;
	
	@Autowired
	private VotacaoService votacaoService;

	/**
	 * Serviço que retorna uma lista com todos os votos realizados na data informada na requisição.
	 * O serviço retorna JSON de acordo com a estrutura do objeto VotoFuncionario.
	 * 
	 * @param data String conténdo a data desejada do resultado
	 * 
	 * Exemplo de requisição:
	 * http://localhost:8080/foodtrack/votofuncionarios/2017-06-01
	 * 
	 * Retorno: 
	 * 
	 * [
	 *   {
	 *     "idFuncionario": 1,
	 *     "idRestaurante": 1,
	 *     "dataVotacao": "2017-06-01",
	 *     "funcionario": {
	 *       "id": 1,
	 *       "nome": "Pedro",
	 *       "ativo": "ativo"
	 *     },
	 *     "restaurante": {
	 *       "id": 1,
	 *       "nome": "San Ro",
	 *       "endereco": "Rua Professor Moraes"
	 *     },
	 *     ...
	 * ]
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/{data}", method = RequestMethod.GET)
	public Collection<VotoFuncionario> getAllByDate(@PathVariable("data") String data) {
		return votoFuncionarioService.getAllByDate(data);
	}

	/**
	 * Serviço de inserção de votos de funcionário.
	 * O serviço consome um json como entrada conténdo as informações do voto.
	 * 
	 * @param votoFuncionario VotoFuncionario
	 * 
	 * Exemplo de requisição:
	 * http://localhost:8080/foodtrack/votofuncionarios
	 * 
	 * POST(application/json)
	 * 
	 * {
	 * 		idFuncionario : "",
	 * 		idRestaurante : "",
	 * 		dataVotacao : "", 
	 * }
	 * 
	 * Onde idFuncionario e idRestaurante são do int e dataVotacao é uma String no formato de data yyyy-MM-dd
	 * Caso a dataVotacao seja nula ou vazia, será selecionada a data atual na votação.
	 * 
	 * Exemplo de uso:
	 * 
	 * {
	 * 		idFuncionario : 1,
	 * 		idRestaurante : 3
	 * }
	 * 
	 * @param votoFuncionario
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<VotoFuncionario> insertVotoFuncionario(@RequestBody VotoFuncionario votoFuncionario) throws Exception {

		votoFuncionarioService.insertVotoFuncionario(votoFuncionario);
		
		votacaoService.insertVotacao(votoFuncionario);

		return getAllByDate(votoFuncionario.getDataVotacao());
	}
}