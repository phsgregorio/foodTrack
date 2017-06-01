package com.foodtrack.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.Funcionario;

@Repository
@Qualifier("h2")
public class FuncionarioH2Dao implements FuncionarioDAO {

	@Override
	public Collection<Funcionario> getAllFuncionarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario getFuncionarioById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFuncionarioById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertFuncionario(Funcionario funcionario) {
		// TODO Auto-generated method stub

	}

}
