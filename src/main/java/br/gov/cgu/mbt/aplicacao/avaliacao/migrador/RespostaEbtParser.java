package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.io.FileReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.header.RespostaQuestionarioEbtHeader;

/**
 * Classe responsável somente por construir os objetos que serão migrados
 * TODO: Renomear classe
 */

public class RespostaEbtParser {
	
	private String arquivoParaImportar;
	
	public RespostaEbtParser(@Value("${arquivo.ebt.importar}") String arquivoParaImportar) {
		this.arquivoParaImportar = arquivoParaImportar;
	}
	
	/**
	 * Retorna uma avaliacao com 
	 */
	/*public Questionario parse(int rodada) throws Exception {
		Iterable<CSVRecord> records = carregarArquivo();
		
		// TODO: criar avaliacao (via SQL)
		// TODO: criar 
		
		Questionario questionario = Questionario.builder().build();
		
		return questionario;
	}*/
	
	private Iterable<CSVRecord> carregarArquivo() throws Exception {
		FileReader in = new FileReader(new ClassPathResource(arquivoParaImportar).getFile());
	    
		Iterable<CSVRecord> records = CSVFormat.DEFAULT
		  .withDelimiter(';')
	      .withHeader(RespostaQuestionarioEbtHeader.class)
	      .withFirstRecordAsHeader()
	      .parse(in);
		
		 return records;
	}

}
