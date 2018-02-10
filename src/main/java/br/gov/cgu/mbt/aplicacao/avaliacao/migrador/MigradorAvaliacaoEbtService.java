package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.aplicacao.avaliacao.PublicadorDeAvaliacao;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.QuestionarioEbtHeader;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.QuestionarioRepository;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.RespostaQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMultiplaEscolha;

@Service
public class MigradorAvaliacaoEbtService {

	private AvaliacaoRepository avaliacaoRepository;

	private QuestionarioRepository questionarioRepository;
	
	private PublicadorDeAvaliacao publicadorDeAvaliacao;

	@Autowired
	public MigradorAvaliacaoEbtService(AvaliacaoRepository avaliacaoRepository,
			QuestionarioRepository questionarioRepository,
			PublicadorDeAvaliacao publicadorDeAvaliacao) {
		this.avaliacaoRepository = avaliacaoRepository;
		this.questionarioRepository = questionarioRepository;
		this.publicadorDeAvaliacao = publicadorDeAvaliacao;
	}

	@Transactional
	public void criarAvaliacoesIndependentes() throws Exception {
		
		// TODO: lançar erro caso as EBT's já estejam migradas
		// Inicialmente só teremos as EBT's, então podemos obter todas
		List<Avaliacao> avaliacoes = new AvaliacaoEbtBuilder().build();
		String jsonEstrutura = ConversorQuestionario.toJson(new BlocoEbtBuilder().build());
		Questionario questionario = Questionario.builder().estrutura(jsonEstrutura).build();

		questionarioRepository.put(questionario);

		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setQuestionario(questionario);
			avaliacaoRepository.put(avaliacao);
		}

		migrarRespostasAvaliacoesIndependentes();
		
		for (Avaliacao avaliacao : avaliacoes) {
			publicadorDeAvaliacao.publicar(avaliacao);
		}
	}

	@Transactional
	private void migrarRespostasAvaliacoesIndependentes() throws Exception {
		RespostaEbtParser respostasParser = new RespostaEbtParser("/ebt/ebt_respostas.csv");

		List<Avaliacao> avaliacoes = avaliacaoRepository.getAll();

		for (Avaliacao avaliacao : avaliacoes) {

			Questionario questionario = avaliacao.getQuestionario();
			String jsonQuestionario = questionario.getEstrutura();
			System.out.println(jsonQuestionario);

			List<CSVRecord> csvRecords = respostasParser.parse(avaliacao.getEdicao());

			// TODO: get todas as respostas do CSV por avaliação
			// TODO: para cada linha das respostas:

			for (CSVRecord record : csvRecords) {
				List<Bloco> blocos = ConversorQuestionario.toBlocos(jsonQuestionario);

				for (Bloco bloco : blocos) {
					List<Questao> questoes = bloco.getQuestoes();
					
					for (Questao questao : questoes) {
						QuestionarioEbtHeader questionarioEbtHeader = EbtUtil.MAPEAMENTO_PERGUNTA_RESPOSTA
								.get(questao.getPergunta());
						String resposta = record.get(questionarioEbtHeader);
						// Na migração da EBT só temos dois tipos de resposta
						// que interessam
						resposta = resposta.equalsIgnoreCase(EbtUtil.OPCAO_SIM) ? EbtUtil.OPCAO_SIM : EbtUtil.OPCAO_NAO;

						if (questao.getTipo() == TipoQuestao.MULTIPLA_ESCOLHA) {
							QuestaoMultiplaEscolha qme = (QuestaoMultiplaEscolha) questao;
							List<OpcaoResposta> opcoesResposta = qme.getOpcoesResposta();

							for (OpcaoResposta opcaoResposta : opcoesResposta) {
								if (opcaoResposta.getOpcao().equalsIgnoreCase(resposta)) {
									opcaoResposta.setResposta(true);
								}
							}
						}
					}
				}
				
				RespostaQuestionario respostaQuestionario = RespostaQuestionario.builder()
					.questionario(avaliacao.getQuestionario())
					.estrutura(ConversorQuestionario.toJson(blocos))
					.municipio(record.get(QuestionarioEbtHeader.municipio))
					.build();
				
				Questionario questionarioDoBanco = questionarioRepository.get(questionario.getId());
				questionarioDoBanco.addResposta(respostaQuestionario);
			}

			// TODO: gera novo Json daquele bloco para aquela linha de resposta
			// TODO: grava resposta em RespostaQuestionario

		}
	}
}

/*
 * for (Questao questao : questoes) { if (questao.getTipo() ==
 * TipoQuestao.MULTIPLA_ESCOLHA) { QuestaoMultiplaEscolha qme =
 * (QuestaoMultiplaEscolha) questao; List<OpcaoResposta> opcoesResposta =
 * qme.getOpcoesResposta(); for (OpcaoResposta opcaoResposta : opcoesResposta) {
 * if (opcaoResposta.isResposta()) {
 * System.out.println(opcaoResposta.getOpcao()); } } } }
 */
