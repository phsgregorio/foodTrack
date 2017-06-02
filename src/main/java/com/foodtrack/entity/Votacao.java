package com.foodtrack.entity;

import com.foodtrack.dao.RestauranteDao;

public class Votacao {

	private int id;
	private int idRestaurante;
	private int numeroVotos;
	private String dataVotacao;
	
	private Restaurante restaurante;

	public Votacao() {	}

	public Votacao(int id, int idRestaurante, int numeroVotos, String dataVotacao) {
		this.id = id;
		this.idRestaurante = idRestaurante;
		this.numeroVotos = numeroVotos;
		this.dataVotacao = dataVotacao;
	}

	public Votacao(int idRestaurante, int numeroVotos, String dataVotacao) {
		this.idRestaurante = idRestaurante;
		this.numeroVotos = numeroVotos;
		this.dataVotacao = dataVotacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public int getNumeroVotos() {
		return numeroVotos;
	}

	public void setNumeroVotos(int numeroVotos) {
		this.numeroVotos = numeroVotos;
	}

	public String getDataVotacao() {
		return dataVotacao;
	}

	public void setDataVotacao(String dataVotacao) {
		this.dataVotacao = dataVotacao;
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