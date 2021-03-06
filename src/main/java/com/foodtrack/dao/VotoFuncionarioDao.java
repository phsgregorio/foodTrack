package com.foodtrack.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.VotoFuncionario;

/**
 * 
 * @author pedro.gregorio
 *
 */
@Repository
@Qualifier("fake")
public class VotoFuncionarioDao {

	private static Map<Integer, VotoFuncionario> votoFuncionarios;
	
	static {
		
		votoFuncionarios = new HashMap<Integer, VotoFuncionario>() {
			private static final long serialVersionUID = 1L;
			
			{
				// Fake test data
//				 put(1, new VotoFuncionario(1, 1, "2017-06-01"));
//				 put(2, new VotoFuncionario(2, 1, "2017-06-01"));
//				 put(3, new VotoFuncionario(3, 3, "2017-06-01"));
//				 put(4, new VotoFuncionario(4, 3, "2017-06-01"));
//				 put(5, new VotoFuncionario(5, 3, "2017-06-01"));
//				 put(6, new VotoFuncionario(6, 2, "2017-06-01"));
//				 put(7, new VotoFuncionario(7, 2, "2017-06-01"));
//				 put(8, new VotoFuncionario(8, 3, "2017-06-01"));
//				 put(9, new VotoFuncionario(9, 3, "2017-06-01"));
//				 put(10, new VotoFuncionario(10, 1, "2017-06-01"));
			}
		};
	}

	/**
	 * Retorna todos os votos de funcionários
	 * @return
	 */
	public Collection<VotoFuncionario> getAll() {
		return votoFuncionarios.values();
	}
	
	/**
	 * Retorna todos os votos de funcionários para a data informada
	 * @param data
	 * @return
	 */
	public Collection<VotoFuncionario> getAllByDate(String data) {
		
		Collection<VotoFuncionario> votosDoDia = new ArrayList<VotoFuncionario>();
		
		for (VotoFuncionario votoFuncionario : votoFuncionarios.values()) {
			
			if (votoFuncionario.getDataVotacao().equals(data)) {
				votosDoDia.add(votoFuncionario);
			}
		}

		return votosDoDia;
	}

	/**
	 * Retorna o voto do funcionário dado a data informada
	 * @param idFuncionario
	 * @param dataVotacao
	 * @return
	 */
	public VotoFuncionario retrieveVotoFuncionarioByDate(int idFuncionario, String dataVotacao) {

		VotoFuncionario retorno = null;
		
		for (VotoFuncionario votoFuncionario : votoFuncionarios.values()) {
			
			if (votoFuncionario.getIdFuncionario()==idFuncionario && votoFuncionario.getDataVotacao().equals(dataVotacao)) {
				retorno = votoFuncionario;
			}
		}
	
		return retorno;
	}
	
	/**
	 * Insere um novo voto de funcionário na memória
	 * @param votoFuncionario
	 */
	public void insert(VotoFuncionario votoFuncionario) {
		votoFuncionarios.put(votoFuncionario.getIdFuncionario(), votoFuncionario);
	}
}