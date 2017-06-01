package com.foodtrack.entity;

/**
 * TODO pedro.gregorio
 * @author First Place
 *
 */
public class Funcionario {

	private int id;
	private String nome;
	private String ativo;

	public Funcionario() { }

	public Funcionario(int id, String nome, String ativo) {
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
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

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
		return getId() + ", " + getNome() + ", " + getAtivo();
	}
}