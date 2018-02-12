package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.aplicacao.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.aplicacao.avaliacao.PublicadorDeAvaliacao;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.QuestionarioEbtHeader;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.QuestionarioRepository;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.questao.TipoQuestao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.Questionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.RespostaQuestionario;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Bloco;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoMultiplaEscolha;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.OpcaoResposta;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.Questao;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoDescritiva;
import br.gov.cgu.mbt.negocio.avaliacao.questionario.json.QuestaoMatriz;
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

			// Para cada respostsa
			for (CSVRecord record : csvRecords) {
				List<Bloco> blocos = ConversorQuestionario.toBlocos(jsonQuestionario);

				for (Bloco bloco : blocos) {
					List<Questao> questoes = bloco.getQuestoes();
					
					for (Questao questao : questoes) {
						if (questao.getTipo() == TipoQuestao.MULTIPLA_ESCOLHA) {
							
							// Essa pergunta necessitou um tratamento especial, pois a pontuação não era por item
							if (questao.getPergunta().equals(EbtUtil.QUESTAO_alternativa_sic_eletronico)) {
								String valor = record.get(QuestionarioEbtHeader.nao_exige_identificacaop);
								QuestaoMultiplaEscolha qme = (QuestaoMultiplaEscolha) questao;
								List<OpcaoResposta> opcoesResposta = qme.getOpcoesResposta();

								for (OpcaoResposta opcaoResposta : opcoesResposta) {
									if (opcaoResposta.getOpcao().equalsIgnoreCase(EbtUtil.OPCAO_SIM) && valor.equals("300")) {
										opcaoResposta.setResposta(true);
									}
									
									if (opcaoResposta.getOpcao().equalsIgnoreCase(EbtUtil.OPCAO_NAO) && !valor.equals("300")) {
										opcaoResposta.setResposta(true);
									}
								}
							} else {
							
								QuestionarioEbtHeader questionarioEbtHeader = EbtUtil.MAPEAMENTO_PERGUNTA_RESPOSTA // TODO: extrair em método
										.get(questao.getPergunta());
								String respostaQuestao = record.get(questionarioEbtHeader);
								respostaQuestao = respostaQuestao.equalsIgnoreCase(EbtUtil.OPCAO_SIM) ? EbtUtil.OPCAO_SIM : EbtUtil.OPCAO_NAO;
								
								QuestaoMultiplaEscolha qme = (QuestaoMultiplaEscolha) questao;
								List<OpcaoResposta> opcoesResposta = qme.getOpcoesResposta();
	
								for (OpcaoResposta opcaoResposta : opcoesResposta) {
									if (opcaoResposta.getOpcao().equalsIgnoreCase(respostaQuestao)) {
										opcaoResposta.setResposta(true);
									}
								}
							}
						} else if (questao.getTipo() == TipoQuestao.DESCRITIVA) {
							QuestionarioEbtHeader questionarioEbtHeader = EbtUtil.MAPEAMENTO_PERGUNTA_RESPOSTA
									.get(questao.getPergunta());
							String respostaQuestao = record.get(questionarioEbtHeader);
							QuestaoDescritiva qd = (QuestaoDescritiva) questao;
							qd.setResposta(respostaQuestao);
						} else if (questao.getTipo() == TipoQuestao.MATRIZ) {
							QuestaoMatriz qm = (QuestaoMatriz) questao;
							List<OpcaoMultiplaEscolha> opcoesMultiplaEscolha = qm.getOpcoesMultiplaEscolha();

							for (OpcaoMultiplaEscolha opcaoMultiplaEscolha : opcoesMultiplaEscolha) {
								// No caso de questao matriz, devemos escolher a pergunta da questão múltipla escolha, e não da pergunta mãe (que é apenas um agrupador)
								QuestionarioEbtHeader questionarioEbtHeader = EbtUtil.MAPEAMENTO_PERGUNTA_RESPOSTA.get(opcaoMultiplaEscolha.getPergunta());
								String respostaQuestao = record.get(questionarioEbtHeader);
								respostaQuestao = respostaQuestao.equalsIgnoreCase(EbtUtil.OPCAO_SIM) ? EbtUtil.OPCAO_SIM : EbtUtil.OPCAO_NAO;
								
								List<OpcaoResposta> opcoesResposta = opcaoMultiplaEscolha.getOpcoesResposta();
								
								for (OpcaoResposta opcaoResposta : opcoesResposta) {
									if (opcaoResposta.getOpcao().equalsIgnoreCase(respostaQuestao)) {
										opcaoResposta.setResposta(true);
									}
								}
							}
						}
					}
				}
				
				// Monta a resposta do questionario e grava
				RespostaQuestionario respostaQuestionario = RespostaQuestionario.builder()
					.questionario(avaliacao.getQuestionario())
					.estrutura(ConversorQuestionario.toJson(blocos))
					.municipio(record.get(QuestionarioEbtHeader.municipio))
					.build();
				
				Questionario questionarioDoBanco = questionarioRepository.get(questionario.getId());
				questionarioDoBanco.addResposta(respostaQuestionario);
			}
		}
	}
}