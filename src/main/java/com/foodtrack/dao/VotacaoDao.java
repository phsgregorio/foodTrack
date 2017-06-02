package com.foodtrack.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.Restaurante;
import com.foodtrack.entity.Votacao;

@Repository
@Qualifier("fake")
public class VotacaoDao {

	private static Map<Integer, Votacao> votacoes;
	
	static {
		
		votacoes = new HashMap<Integer, Votacao>() {
			private static final long serialVersionUID = 1L;
			
			{
				// Fake data
				 put(1, new Votacao(1, 1, 3, "2017-06-01"));
				 put(2, new Votacao(2, 2, 2, "2017-06-01"));
				 put(3, new Votacao(3, 3, 5, "2017-06-01"));
			}
		};
	}

	public Collection<Votacao> getAll() {
		return votacoes.values();
	}
	
	/**
	 * Verify if the given Restaurante was chosen in the past week
	 * @param restaurante
	 * @param data 
	 * @return
	 */
	public boolean isRestauranteJaEscolhido(Restaurante restaurante, String data) {
		
		// Devo conferir se o restaurante já foi o mais votado nos últimos sete dias
		// Para isso devo converter a data para algum formato(fazer um for) e olhar se nos sete dias anteriores ele foi escolhido
		
		return false;
	}
	
	/**
	 * Returns the chosen restaurant for a day
	 * @param data
	 * @return
	 */
	public Votacao getRestauranteDia(String data) {
		
		ArrayList<Votacao> votosDia = (ArrayList<Votacao>) getAllByDate(data);
		Integer escolhido = -1;

		if (votosDia.size()>0) {
			
			escolhido = 0;
			
			for (int i = 1; i < votosDia.size(); i++) {
				
				if (votosDia.get(i).getNumeroVotos() > votosDia.get(escolhido).getNumeroVotos() && 
						!isRestauranteJaEscolhido(votosDia.get(i).getRestaurante(), data)) {
					escolhido = i;
				}
			}
		}

		return escolhido==-1 ? null : votosDia.get(escolhido);
	}
	
	public Votacao getVotacaoByRestauranteData(int idRestaurante, String data) {
		
		Votacao retorno = null;
		
		for (Votacao votacao : votacoes.values()) {
			
			if (votacao.getIdRestaurante()==idRestaurante && votacao.getDataVotacao().equals(data)) {
				retorno = votacao;
			}
		}

		return retorno;
	}
	
	/**
	 * Return all Restaurante Votos by day
	 * @param data
	 * @return
	 */
	public Collection<Votacao> getAllByDate(String data) {
		
		Collection<Votacao> votosDoDia = new ArrayList<Votacao>();
		
		for (Votacao votacao : votacoes.values()) {
			
			if (votacao.getDataVotacao().equals(data)) {
				votosDoDia.add(votacao);
			}
		}

		return votosDoDia;
	}

	public void insert(Votacao votacao) {
		votacoes.put(votacao.getId(), votacao);
	}

	public void update(Votacao votacao) {
		votacoes.put(votacao.getId(), votacao);
		
	}

	public int getLastId() {

		int lastIndex = 0;

		for (Votacao votacao : votacoes.values()) {
			
			if (votacao.getId() > lastIndex) {
				lastIndex = votacao.getId();
			}
		}

		return lastIndex + 1;
	}
}