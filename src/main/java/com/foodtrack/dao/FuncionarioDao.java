package com.foodtrack.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.Funcionario;

@Repository
@Qualifier("fake")
public class FuncionarioDao implements GenericDao<Funcionario> {

	private static Map<Integer, Funcionario> funcionarios;
	
	static {
		
		funcionarios = new HashMap<Integer, Funcionario>() {

			private static final long serialVersionUID = 1L;

			{
				// Fake Data
				// put(1, new Funcionario(1, "Pedro", "ativo"));
				// put(2, new Funcionario(2, "Henrique", "inativo"));
				// put(3, new Funcionario(3, "Luiz", "ativo"));
			}
		};
	}

	@Override
	public Collection<Funcionario> getAll() {
		return funcionarios.values();
	}

	@Override
	public Funcionario getById(int id) {
		return funcionarios.get(id);
	}

	@Override
	public void removeById(int id) {
		funcionarios.remove(id);
	}

	@Override
	public void update(Funcionario funcionario) {
		
		Funcionario f = funcionarios.get(funcionario.getId());
		f.setNome(funcionario.getNome());
		f.setAtivo(funcionario.getAtivo());
		
		funcionarios.put(funcionario.getId(), f);
	}

	@Override
	public void insert(Funcionario funcionario) {
		funcionarios.put(funcionario.getId(), funcionario);
	}
}