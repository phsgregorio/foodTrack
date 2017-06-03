package com.foodtrack.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodtrack.entity.Funcionario;
import com.foodtrack.service.FuncionarioService;

@RestController
@RequestMapping("/foodtrack/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	/**
	 * Serviço responsável pelo retorno da lista de funcionários da aplicação.
	 * O serviço retorna um JSON com a estrutura da lista de Funcionarios.
	 * 
	 * Exemplo de requisição:
	 * http://localhost:8080/foodtrack/funcionarios
	 * 
	 * Retorno:
	 * 
	 * [
	 *   {
	 *     "id": 1,
	 *     "nome": "Pedro",
	 *     "ativo": "ativo"
	 *   },
	 *   {
	 *     "id": 2,
	 *     "nome": "Henrique Diniz",
	 *     "ativo": "ativo"
	 *   },
	 *   ...
	 * ]
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Funcionario> getAllFuncionarios() {
		
		return funcionarioService.getAllFuncionarios();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Funcionario getFuncionarioById(@PathVariable("id") int id) {
		
		return funcionarioService.getFuncionarioById(id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Collection<Funcionario> deleteFuncionarioById(@PathVariable("id") int id) {
		
		funcionarioService.removeFuncionarioById(id);
		return getAllFuncionarios();
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Funcionario> uptadeFuncionario(@RequestBody Funcionario funcionario) throws Exception {
		
		funcionarioService.updateFuncionario(funcionario);
		return getAllFuncionarios();
	}
	
	/**
	 * Serviço responsável pela inserção de um novo funcionário.
	 * O serviço consome um json como entrada conténdo as informações do funcionário.
	 * 
	 * @param Funcionario funcionario
	 * 
	 * Exemplo de requisição:
	 * http://localhost:8080/foodtrack/funcionarios
	 * 
	 * POST(application/json)
	 * 
	 * {
	 *     "id": 15,
	 *     "nome": "Roberto Carlos",
	 *     "ativo": "inativo"
	 * }
	 * 
	 * @param funcionario
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Funcionario> insertFuncionario(@RequestBody Funcionario funcionario) throws Exception {
		
		funcionarioService.insertFuncionario(funcionario);
		return getAllFuncionarios();
	}
}