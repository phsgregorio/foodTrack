package com.foodtrack.entity;

public class Restaurante {

	int id;
	String nome;
	String endereco;

	public Restaurante() { 	}

	public Restaurante(int id, String nome, String endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Restaurante restaurante = (Restaurante) obj;

		return restaurante!=null &&
				this.getId() == restaurante.getId() &&
				this.getNome().equals(restaurante.getNome()) &&
				this.getEndereco().equals(restaurante.getEndereco());
	}
}
