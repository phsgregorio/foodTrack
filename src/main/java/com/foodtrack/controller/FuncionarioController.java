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
	public Collection<Funcionario> uptadeFuncionario(@RequestBody Funcionario funcionario) {
		
		funcionarioService.updateFuncionario(funcionario);
		return getAllFuncionarios();
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Funcionario> insertFuncionario(@RequestBody Funcionario funcionario) {
		
		funcionarioService.insertFuncionario(funcionario);
		return getAllFuncionarios();
	}
}