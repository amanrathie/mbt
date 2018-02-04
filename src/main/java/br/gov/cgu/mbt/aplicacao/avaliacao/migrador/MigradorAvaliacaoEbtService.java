package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.QuestaoEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.bloco.BlocoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.questao.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questao.Resposta;
import br.gov.cgu.mbt.negocio.avaliacao.questao.RespostaRepository;

@Service
public class MigradorAvaliacaoEbtService {
	
	private AvaliacaoRepository avaliacaoRepository;
	
	private BlocoRepository blocoRepository;
	
	private RespostaRepository respostaRepository;
	
	@Autowired
	public MigradorAvaliacaoEbtService(AvaliacaoRepository avaliacaoRepository,
			BlocoRepository blocoRepository,
			RespostaRepository respostaRepository) {
		this.avaliacaoRepository = avaliacaoRepository;
		this.blocoRepository = blocoRepository;
		this.respostaRepository = respostaRepository;
	}
	
	@Transactional
	public void criarAvaliacoesIndependentes() throws Exception {
		// Inicialmente só teremos as EBT's, então podemos obter todas
		List<Avaliacao> avaliacoes = new AvaliacaoEbtBuilder().build();
		List<Bloco> blocos = new BlocoEbtBuilder().build();
		
		for (Bloco bloco : blocos) {
			List<Questao> questoes = new QuestaoEbtBuilder().build(bloco);
			
			for (Questao questao : questoes) {
				bloco.addQuestao(questao);
			}
			
			blocoRepository.put(bloco);
		}
		
		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setBlocos(blocos);

			avaliacaoRepository.put(avaliacao);
		}
	}
	
	@Transactional
	public void migrarAvaliacoesIndependentes() throws Exception {
		RespostaEbtParser respostasParser = new RespostaEbtParser("/ebt/ebt_respostas.csv");
		
		List<Avaliacao> avaliacoes = avaliacaoRepository.getAll(); // TODO: pegar apenas as corretas para não ter riscos
		
		for (Avaliacao avaliacao : avaliacoes) {
			List<Bloco> blocos = avaliacao.getBlocos();
		
			for (Bloco bloco : blocos) {
				List<Questao> questoes = bloco.getQuestoes();
				
				for (Questao questao : questoes) {
				
					// Para cada questão, monta as respostas que foram dadas no arquivo
					List<Resposta> respostas = respostasParser.parse(1, questao);
					
					for (Resposta resposta : respostas) {
						respostaRepository.put(resposta);
					}
				}
			}
		}
	}
}
