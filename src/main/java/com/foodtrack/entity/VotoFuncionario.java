package com.foodtrack.entity;


import com.foodtrack.dao.FuncionarioDao;
import com.foodtrack.dao.RestauranteDao;

public class VotoFuncionario {

	private int idFuncionario;
	private int idRestaurante;
	private String dataVotacao;

	private Funcionario funcionario;
	private Restaurante restaurante;
	
	public VotoFuncionario() {	}

	public VotoFuncionario(int idFuncionario, int idRestaurante, String dataVotacao) {
		this.idFuncionario = idFuncionario;
		this.idRestaurante = idRestaurante;
		this.dataVotacao = dataVotacao;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getDataVotacao() {
		return dataVotacao;
	}

	public void setDataVotacao(String dataVotacao) {
		this.dataVotacao = dataVotacao;
	}

	public Funcionario getFuncionario() {

		if (funcionario==null) {
			FuncionarioDao restauranteDao = new FuncionarioDao();
			setFuncionario(restauranteDao.getById(idFuncionario));
		}

		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Restaurante getRestaurante() {

		if (restaurante==null) {
			RestauranteDao restauranteDao = new RestauranteDao();
			setRestaurante(restauranteDao.getById(idRestaurante));
		}

		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
}