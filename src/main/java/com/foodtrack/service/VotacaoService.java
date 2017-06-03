package com.foodtrack.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.foodtrack.dao.VotacaoDao;
import com.foodtrack.entity.Restaurante;
import com.foodtrack.entity.Votacao;
import com.foodtrack.entity.VotoFuncionario;

/**
 * Votacao Business Logic 
 * @author pedro.gregorio
 *
 */
@Service
public class VotacaoService {

	@Autowired
	@Qualifier("fake")
	private VotacaoDao votacaoDao;
	
	public Collection<Votacao> getAll() {
		return this.votacaoDao.getAll();
	}
	
	public Collection<Votacao> getAllByDate(String data) {
		return votacaoDao.getAllByDate(data);
	}
	
	/**
	 * Confere se o restaurante informado foi escolhido durante a semana
	 * @param restaurante
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean isRestauranteJaEscolhido(Restaurante restaurante, String data) throws Exception {
		
		boolean jaEscolhido = false;

		try {
			
			HashMap<String, Restaurante> restaurantes = votacaoDao.getRestaurantesSemana(data);
			
			for (Restaurante restauranteEscolhido : restaurantes.values()) {
				
				if (restauranteEscolhido!=null && restauranteEscolhido.equals(restaurante)) {
					jaEscolhido = true;
				}
			}
		} catch (ParseException e) {
			throw new Exception("Falha ao recuperar restaurantes, favor conferir o parâmetro {data} informado.");
		}
		
		return jaEscolhido;
	}
	
	/**
	 * Recupera o restaurante escolhido de acordo com a votação e as escolhas anteriores da semana.
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Restaurante getRestauranteDia(String data) throws Exception{
		
		Restaurante restaurante = null;
		
		try {
			restaurante = votacaoDao.getRestauranteDia(data);
		} catch (ParseException e) {
			throw new Exception("Falha ao recuperar restaurantes, favor conferir o parâmetro {data} informado.");
		}
		
		if (restaurante==null) {
			throw new Exception("Nenhum restaurante foi escolhido para o dia " + data + " ou ele já foi escolhido durante a semana.");
		}

		return restaurante;
	}
	
	/**
	 * Retorna o resultado da votação de um restaurante para a data informada
	 * @param idRestaurante
	 * @param data
	 * @return
	 */
	public Votacao getVotacaoByRestauranteData(int idRestaurante, String data) {
		return votacaoDao.getVotacaoByRestauranteData(idRestaurante, data);
	}

	/**
	 * Inseri ou atualiza a pontuação de um restaurante após o voto de um funcionário
	 * @param votoFuncionario
	 * @throws Exception
	 */
	public void insertVotacao(VotoFuncionario votoFuncionario) throws Exception {

		if (StringUtils.isEmpty(votoFuncionario.getDataVotacao())) {
			String dataHoje = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			votoFuncionario.setDataVotacao(dataHoje);
		}
		
		if (votoFuncionario.getRestaurante()!=null && votoFuncionario.getDataVotacao()!=null) {
			
			Votacao votacao = getVotacaoByRestauranteData(votoFuncionario.getIdRestaurante(), votoFuncionario.getDataVotacao());
			
			if (votacao==null) {
				
				votacao = new Votacao(this.votacaoDao.getLastId(), votoFuncionario.getIdRestaurante(), 1, votoFuncionario.getDataVotacao());
				this.votacaoDao.insert(votacao);
			} else {
				
				votacao.setNumeroVotos(votacao.getNumeroVotos()+1);
				this.votacaoDao.update(votacao);
			}
		} else {
			throw new Exception("As propriedades do objeto VotoFuncionario {idRestaurante} e {dataVotacao} devem ser devidamente informadas.");
		}
	}
}
