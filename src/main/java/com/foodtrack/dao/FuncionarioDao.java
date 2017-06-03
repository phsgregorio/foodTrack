package com.foodtrack.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.Funcionario;

/**
 * 
 * @author pedro.gregorio
 *
 */
@Repository
@Qualifier("fake")
public class FuncionarioDao implements GenericDao<Funcionario> {

	private static Map<Integer, Funcionario> funcionarios;
	
	static {
		
		funcionarios = new HashMap<Integer, Funcionario>() {

			private static final long serialVersionUID = 1L;

			{
				// Fake test data
//				 put(1, new Funcionario(1, "Pedro", Funcionario.ATIVO));
//				 put(2, new Funcionario(2, "Henrique Diniz", Funcionario.ATIVO));
//				 put(3, new Funcionario(3, "Luiz Henrique", Funcionario.ATIVO));
//				 put(4, new Funcionario(4, "Wilson Alencar", Funcionario.ATIVO));
//				 put(5, new Funcionario(5, "Rafael Barreto", Funcionario.ATIVO));
//				 put(6, new Funcionario(6, "Andre Alencar", Funcionario.ATIVO));
//				 put(7, new Funcionario(7, "Priscila Alencar", Funcionario.ATIVO));
//				 put(8, new Funcionario(8, "Adriane", Funcionario.ATIVO));
//				 put(9, new Funcionario(9, "Socorro", Funcionario.ATIVO));
//				 put(10, new Funcionario(10, "Rafael", Funcionario.ATIVO));
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