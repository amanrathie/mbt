package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.io.FileReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.QuestionarioEbtHeader;


public class RespostaEbtParser {
	private static Iterable<CSVRecord> records;
	
	public RespostaEbtParser(@Value("${arquivo.ebt.importar}") String arquivoParaImportar) throws Exception {		
		FileReader in = new FileReader(new ClassPathResource(arquivoParaImportar).getFile());
	    
		records = CSVFormat.DEFAULT
		  .withDelimiter(';')
	      .withHeader(QuestionarioEbtHeader.class)
	      .withFirstRecordAsHeader()
	      .parse(in);
	}
	
	/**
	 * Retorna uma lista de respostas de uma EBT especifica
	 */
	/*public List<Resposta> parse(int rodadaAvaliacao, QuestaoASerExcluida questao) throws Exception {
		List<Resposta> respostas = new ArrayList<Resposta>();
		ObjectMapper mapper = new ObjectMapper();
		
		for (CSVRecord record : records) {
			if (Integer.valueOf(record.get(QuestionarioEbtHeader.rodada)) == rodadaAvaliacao) {
				QuestionarioEbtHeader headerDaQuestao = QuestaoQuestionarioEbtHeaderMapper.mapper.get(questao);
				
				// TODO: modificar para conter a resposta
				QuestaoMultiplaEscolha questaoMultiplaEscolha = mapper.readValue(questao.getEstrutura(), QuestaoMultiplaEscolha.class);
				
				Resposta resposta = Resposta.builder()
				.questao(questao)
				.municipio(record.get(QuestionarioEbtHeader.municipio))
				.estrutura(mapper.writeValueAsString(questaoMultiplaEscolha)) 
				.build();
				
				respostas.add(resposta);
			}
		}
 		
		return respostas;
	}*/

}
