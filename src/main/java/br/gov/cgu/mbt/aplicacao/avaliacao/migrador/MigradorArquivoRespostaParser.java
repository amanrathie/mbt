package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.QuestionarioEbtHeader;


public class MigradorArquivoRespostaParser {
	private static Iterable<CSVRecord> records;
	
	public MigradorArquivoRespostaParser(@Value("${arquivo.ebt.importar}") String arquivoParaImportar) throws RuntimeException {		
		InputStreamReader in;
		try {
			in = new InputStreamReader(new ClassPathResource(arquivoParaImportar).getInputStream(), "UTF-8");
			records = CSVFormat.DEFAULT
					  .withDelimiter(';')
				      .withHeader(QuestionarioEbtHeader.class)
				      .withFirstRecordAsHeader()
				      .parse(in);
		} catch (IOException e) {
			throw new RuntimeException("Não foi possível ler o arquivo");
		}	
	}
	
	/**
	 * Retorna uma lista de respostas de uma EBT especifica
	 */
	public List<CSVRecord> parse(int rodadaAvaliacao) throws RuntimeException {
		List<CSVRecord> registrosDaRodada = new ArrayList<CSVRecord>();
		
		for (CSVRecord record : records) {
			if (Integer.valueOf(record.get(QuestionarioEbtHeader.rodada)) == rodadaAvaliacao) {
				registrosDaRodada.add(record);
			}
		}
 		
		return registrosDaRodada;
	}

}
