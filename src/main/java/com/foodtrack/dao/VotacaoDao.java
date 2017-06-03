package com.foodtrack.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.Restaurante;
import com.foodtrack.entity.Votacao;
import com.foodtrack.util.DateUtils;

@Repository
@Qualifier("fake")
public class VotacaoDao {

	private static Map<Integer, Votacao> votacoes;
	
	static {
		
		votacoes = new HashMap<Integer, Votacao>() {
			private static final long serialVersionUID = 1L;
			
			{
				// Fake data
//				 put(1, new Votacao(1, 1, 3, "2017-06-01"));
//				 put(2, new Votacao(2, 2, 2, "2017-06-01"));
//				 put(3, new Votacao(3, 3, 5, "2017-06-01"));
			}
		};
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
	
	/**
	 * Retorna todos os registros de votação
	 * @return
	 */
	public Collection<Votacao> getAll() {
		return votacoes.values();
	}
	
	/**
	 * Retorna os dados da votação de um restaurante para a data informada.
	 * @param idRestaurante
	 * @param data
	 * @return
	 */
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
	
	/**
	 * Retorna os restaurantes escolhidos durante a semana da data informada
	 * @param data 
	 * @return
	 * @throws ParseException 
	 */
	public HashMap<String, Restaurante> getRestaurantesSemana(String data) throws ParseException {

		ArrayList<String> diasDaSemana = DateUtils.getDaysOfWeek(data);
		ArrayList<Restaurante> restaurantesEscolhidos = new ArrayList<Restaurante>();
		HashMap<String, Restaurante> restaurantePorDiaDaSemana = new HashMap<String, Restaurante>();

		for (String dataDia : diasDaSemana) {

			// Recupero a votação de cada dia, para verificar qual foi o restaurante escolhido
			ArrayList<Votacao> votosDosRestaurantePorDia = (ArrayList<Votacao>) getAllByDate(dataDia);
			Votacao escolhido = new Votacao();
			
			if (votosDosRestaurantePorDia.size()>0) {

				for (Votacao votosDoRestauranteNoDia : votosDosRestaurantePorDia) {
					
					// Se não estiver na lista de escolhidos e tiver mais votos que o anterior, troco o Restaurante escolhido
					Restaurante restaurante = votosDoRestauranteNoDia.getRestaurante();
					if (restaurantesEscolhidos.indexOf(restaurante)==-1 &&
							votosDoRestauranteNoDia.getNumeroVotos() > escolhido.getNumeroVotos()) {

						escolhido = votosDoRestauranteNoDia;
					}
				}
			}

			// If none be chosed it will return null value
			restaurantesEscolhidos.add(escolhido.getRestaurante());
			restaurantePorDiaDaSemana.put(dataDia, escolhido.getRestaurante());
		}

		return restaurantePorDiaDaSemana;
	}
	
	/**
	 * Retorna o restaurante escolhido para a data informada levando em consideração restaurantes escolhidos durante a semana.
	 * @param data
	 * @return
	 * @throws ParseException 
	 */
	public Restaurante getRestauranteDia(String data) throws ParseException {

		HashMap<String, Restaurante> restaurantesDaSemana = getRestaurantesSemana(data);
		return restaurantesDaSemana.get(data);
	}
}