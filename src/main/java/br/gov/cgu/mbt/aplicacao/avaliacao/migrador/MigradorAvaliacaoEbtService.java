package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.QuestionarioEbtBuilder;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.QuestionarioRepository;

@Service
public class MigradorAvaliacaoEbtService {
	
	private AvaliacaoRepository avaliacaoRepository;
	
	private QuestionarioRepository questionarioRepository;
	
	@Autowired
	public MigradorAvaliacaoEbtService(AvaliacaoRepository avaliacaoRepository,
			QuestionarioRepository questionarioRepository) {
		this.avaliacaoRepository = avaliacaoRepository;
		this.questionarioRepository = questionarioRepository;
	}
	
	@Transactional
	public void criarAvaliacoesIndependentes() throws Exception {
		// Inicialmente só teremos as EBT's, então podemos obter todas
		List<Avaliacao> avaliacoes = new AvaliacaoEbtBuilder().build();

		Questionario questionario = Questionario.builder()
			.estrutura(new QuestionarioEbtBuilder().build())
			.build();
		
		questionarioRepository.put(questionario);
		
		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setQuestionario(questionario);
			avaliacaoRepository.put(avaliacao);
		}
		
	}
	
	@Transactional
	public void migrarAvaliacoesIndependentes() throws Exception {
		/*RespostaEbtParser respostasParser = new RespostaEbtParser("/ebt/ebt_respostas.csv");
		
		List<Avaliacao> avaliacoes = avaliacaoRepository.getAll(); // TODO: pegar apenas as corretas para não ter riscos
		
		for (Avaliacao avaliacao : avaliacoes) {
			List<BlocoASerExcluido> blocos = avaliacao.getBlocos();
		
			for (BlocoASerExcluido bloco : blocos) {
				List<QuestaoASerExcluida> questoes = bloco.getQuestoes();
				
				for (QuestaoASerExcluida questao : questoes) {
				
					// Para cada questão, monta as respostas que foram dadas no arquivo
					List<Resposta> respostas = respostasParser.parse(1, questao);
					
					for (Resposta resposta : respostas) {
						respostaRepository.put(resposta);
					}
				}
			}
		}*/
	}
}
