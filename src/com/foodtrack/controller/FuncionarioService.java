package com.foodtrack.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.foodtrack.entity.Funcionario;

/**
 * 
 * @author pedro.gregorio
 *
 */
@Path("/funcionarioservice")
public class FuncionarioService {
	
	@GET
	@Produces("application/xml")
	public String inserirFuncionario() {

		String result = "Os parâmetros nome e ativo devem ser devidamente informados";
		return "<error><status>-1</status><message>" + result + "</message></error>";
	}
 
	@Path("{nome}/{ativo}")
	@GET
	@Produces("application/xml")
	public String inserirFuncionario(@PathParam("nome") String nome, @PathParam("ativo") String ativo) {
		
		Funcionario funcionarioBO = new Funcionario(ativo, ativo);
		funcionarioBO.setNome(nome);
		funcionarioBO.setAtivo(ativo);
 
		return "<funcionario>" + "<nome>" + nome + "</nome>" + "<ativo>" + ativo + "</ativo>" + "</funcionario>";
	}
}