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
				// TODO pedro.gregorio inject fake data here
			}
		};
	}

	public Collection<Votacao> getAll() {
		return votacoes.values();
	}
	
	/**
	 * Verify if the given Restaurante was chosen in the past week
	 * @param restaurante
	 * @return
	 */
	public boolean isRestauranteJaEscolhido(Restaurante restaurante) {
		// TODO pedro.gregorio Implementar
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
						!isRestauranteJaEscolhido(votosDia.get(i).getRestaurante())) {
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