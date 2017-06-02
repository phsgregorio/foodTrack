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

	@RequestMapping(value="/{data}", method = RequestMethod.GET)
	public Collection<VotoFuncionario> getAllByDate(@PathVariable("data") String data) {
		return votoFuncionarioService.getAllByDate(data);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<VotoFuncionario> insertVotoFuncionario(@RequestBody VotoFuncionario votoFuncionario) throws Exception {

		votoFuncionarioService.insertVotoFuncionario(votoFuncionario);
		
		// Updating Votacao Entity
		votacaoService.insertVotacao(votoFuncionario);

		return getAllByDate(votoFuncionario.getDataVotacao());
	}
}