package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.aplicacao.avaliacao.AvaliacaoRepository;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.QuestionarioEbtHeader;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.QuestionarioRepository;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.RespostaQuestionarioRepository;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador.CalculadorQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.resultado.ResultadoAvaliacaoRepository;
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
import br.gov.cgu.mbt.negocio.avaliacao.resultado.ResultadoAvaliacao;

@Service
public class MigradorAvaliacaoService {

	private CalculadorQuestionario calculadorQuestionario;
	
	private AvaliacaoRepository avaliacaoRepository;

	private QuestionarioRepository questionarioRepository;
	
	private RespostaQuestionarioRepository respostaQuestionarioRepository;
	
	private ResultadoAvaliacaoRepository resultadoAvaliacaoRepository;

	@Autowired
	public MigradorAvaliacaoService(@Qualifier("calculadorQuestionarioMigrador")CalculadorQuestionario calculadorQuestionario,
			AvaliacaoRepository avaliacaoRepository,
			QuestionarioRepository questionarioRepository,
			RespostaQuestionarioRepository respostaQuestionarioRepository,
			ResultadoAvaliacaoRepository resultadoAvaliacaoRepository) {
		this.calculadorQuestionario = calculadorQuestionario;
		this.avaliacaoRepository = avaliacaoRepository;
		this.questionarioRepository = questionarioRepository;
		this.respostaQuestionarioRepository = respostaQuestionarioRepository;
		this.resultadoAvaliacaoRepository = resultadoAvaliacaoRepository;
	}

	@Transactional
	public List<Avaliacao> criarAvaliacoesIndependentes() throws Exception {
		// TODO: lançar erro caso as EBT's já estejam migradas
		List<Avaliacao> avaliacoes = new AvaliacaoEbtBuilder().build();
		String jsonEstrutura = ConversorQuestionario.toJson(new BlocoEbtBuilder().build());
		Questionario questionario = Questionario.builder().estrutura(jsonEstrutura).build();

		questionarioRepository.put(questionario);

		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setQuestionario(questionario);
			avaliacaoRepository.put(avaliacao);
		}

		migrarRespostasAvaliacoesIndependentes(avaliacoes);
		
		criarResultadosAvaliacao(avaliacoes);
		
		return avaliacoes;
	}

	@Transactional
	private void migrarRespostasAvaliacoesIndependentes(List<Avaliacao> avaliacoes) throws Exception {
		for (Avaliacao avaliacao : avaliacoes) {

			Questionario questionario = avaliacao.getQuestionario();
			String jsonQuestionario = questionario.getEstrutura();
			
			MigradorArquivoRespostaParser respostasParser = new MigradorArquivoRespostaParser("/ebt/ebt_respostas.csv");
			List<CSVRecord> csvRecords = respostasParser.parse(avaliacao.getEdicao());
			
			System.out.println(csvRecords.size());

			// Para cada respostas
			for (CSVRecord record : csvRecords) {
				List<Bloco> blocos = ConversorQuestionario.toBlocos(jsonQuestionario);

				for (Bloco bloco : blocos) {
					List<Questao> questoes = bloco.getQuestoes();
					
					for (Questao questao : questoes) {
						if (questao.getTipo() == TipoQuestao.MULTIPLA_ESCOLHA) {
							
							// Essa pergunta necessitou um tratamento especial, pois a pontuação não era por item
							if (questao.getPergunta().equals(EbtUtil.QUESTAO_nao_exige_identificacao)) {
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
					.avaliacao(avaliacao)
					.estrutura(ConversorQuestionario.toJson(blocos))
					.municipio(record.get(QuestionarioEbtHeader.municipio)) // TODO: temporario
					.uf(record.get(QuestionarioEbtHeader.uf)) // TODO: temporario
					.build();
				
				//Questionario questionarioDoBanco = questionarioRepository.get(questionario.getId());
				questionario.addResposta(respostaQuestionario);
			}
			System.out.println("Salvando respostas: " + questionario.getRespostas().size()); 
			questionarioRepository.flush();
		}
	}
	
	@Transactional
	private void criarResultadosAvaliacao(List<Avaliacao> avaliacoes) {
		for (Avaliacao avaliacao : avaliacoes) {
			List<RespostaQuestionario> respostas = respostaQuestionarioRepository.getPorIdAvaliacao(avaliacao.getId());
			System.out.println(respostas.size());
			for (RespostaQuestionario resposta : respostas) {
				BigDecimal notaFinal = calculadorQuestionario.calculaNota(ConversorQuestionario.toBlocos(resposta.getEstrutura()));
	
				// TODO: Logica para salvar dados do .CSV caso o desvio seja de 0,01
				
				ResultadoAvaliacao resultado = 
						ResultadoAvaliacao.builder()
						.avaliacao(avaliacao)
						.nomeMunicipio(resposta.getMunicipio())
						.uf(resposta.getUf())
						.nota(notaFinal)
						.build();
				
				resultadoAvaliacaoRepository.put(resultado);
			}
		}
	}
}