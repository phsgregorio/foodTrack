package com.foodtrack.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodtrack.entity.Votacao;
import com.foodtrack.service.VotacaoService;

@RestController
@RequestMapping("/foodtrack/votacao")
public class VotacaoController {

	@Autowired
	private VotacaoService votacaoService;
	
	@RequestMapping(value="/{data}/{all}", method = RequestMethod.GET)
	public Collection<Votacao> getAllByDate(@PathVariable("data") String data, @PathVariable("all") boolean all) {
		return votacaoService.getAllByDate(data);
	}

	@RequestMapping(value="/{data}", method = RequestMethod.GET)
	public Votacao getRestauranteDia(@PathVariable("data") String data) {
		return votacaoService.getRestauranteDia(data);
	}
}