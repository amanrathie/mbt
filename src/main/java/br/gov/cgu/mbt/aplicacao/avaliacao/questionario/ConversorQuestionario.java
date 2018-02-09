package br.gov.cgu.mbt.aplicacao.avaliacao.questionario;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;

public class ConversorQuestionario {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static List<Bloco> toBlocos(String jsonQuestionario) {
		try {
			List<Bloco> blocos = mapper.readValue(jsonQuestionario, mapper.getTypeFactory().constructCollectionType(List.class, Bloco.class));
			return blocos;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static String toJson(List<Bloco> blocos) {
		try {
			return mapper.writeValueAsString(blocos);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}

}
