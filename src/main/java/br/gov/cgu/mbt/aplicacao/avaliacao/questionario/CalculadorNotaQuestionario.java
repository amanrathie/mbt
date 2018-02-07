package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;

public class CalculadorNotaQuestionario {
	
	public static void calculaNota(Questionario questionario) { // TODO: na verdade aqui ja tem que vir a estrutura com a resposta
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<Bloco> blocos = mapper.readValue(questionario.getEstrutura(), 
					mapper.getTypeFactory().constructCollectionType(List.class, Bloco.class));
			
			for (Bloco bloco : blocos) {
				BigDecimal pesoBloco = bloco.getPeso();
				
				List<Questao> questoes = bloco.getQuestoes();
				for (Questao questao : questoes) {
					BigDecimal pesoQuestao = questao.getPeso();
				}
			}
			
		} catch (IOException e) { // TODO: jogar para um método comum que faz o parser do questionario e lanca runtimeexception
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void calculaNotaQuestao(Questao questao) {
		
	}

}
