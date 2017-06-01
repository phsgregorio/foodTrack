package com.foodtrack.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.Funcionario;

@Repository
@Qualifier("fake")
public class FuncionarioFakeDao implements FuncionarioDAO {

//	private Integer id;
//	private String nome;
//	private String ativo;

	private static Map<Integer, Funcionario> funcionarios;
	
	static {
		
		funcionarios = new HashMap<Integer, Funcionario>() {
			{
				put(1, new Funcionario(1, "Pedro", "ativo"));
				put(2, new Funcionario(2, "Henrique", "inativo"));
				put(3, new Funcionario(3, "Luiz", "ativo"));
			}
		};
	}

	@Override
	public Collection<Funcionario> getAllFuncionarios() {
		return this.funcionarios.values();
	}

	@Override
	public Funcionario getFuncionarioById(int id) {
		return this.funcionarios.get(id);
	}

	@Override
	public void removeFuncionarioById(int id) {
		this.funcionarios.remove(id);
	}
	@Override
	public void updateFuncionario(Funcionario funcionario) {
		
		Funcionario f = this.funcionarios.get(funcionario.getId());
		f.setNome(funcionario.getNome());
		f.setAtivo(funcionario.getAtivo());
		
		this.funcionarios.put(funcionario.getId(), f);
	}
	@Override
	public void insertFuncionario(Funcionario funcionario) {
		this.funcionarios.put(funcionario.getId(), funcionario);
	}
}