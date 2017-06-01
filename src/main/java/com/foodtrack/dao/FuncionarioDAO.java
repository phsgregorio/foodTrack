package com.foodtrack.dao;

import java.util.Collection;

import com.foodtrack.entity.Funcionario;

public interface FuncionarioDAO {

	Collection<Funcionario> getAllFuncionarios();

	Funcionario getFuncionarioById(int id);

	void removeFuncionarioById(int id);

	void updateFuncionario(Funcionario funcionario);

	void insertFuncionario(Funcionario funcionario);
}