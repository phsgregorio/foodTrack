package com.foodtrack.entity;

/**
 * TODO pedro.gregorio
 * @author First Place
 *
 */
public class Funcionario {

	private Integer id;
	private String nome;
	private String ativo;

	public Funcionario(String nome, String ativo) {
		this.nome = nome;
		this.ativo = ativo;
	}
	
	public Funcionario(Integer id, String nome, String ativo) {
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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