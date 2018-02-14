package br.gov.cgu.mbt.aplicacao.avaliacao.migrador;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cgu.mbt.Constantes;
import br.gov.cgu.mbt.aplicacao.avaliacao.GerenciadorAvaliacao;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.AvaliacaoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.builder.BlocoEbtBuilder;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.EbtUtil;
import br.gov.cgu.mbt.aplicacao.avaliacao.migrador.util.QuestionarioEbtHeader;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.BuscadorRespostaQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.ConversorQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.GerenciadorQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.questionario.calculador.CalculadorQuestionario;
import br.gov.cgu.mbt.aplicacao.avaliacao.resultado.GerenciadorResultadoAvaliacao;
import br.gov.cgu.mbt.negocio.auth.Usuario;
import br.gov.cgu.mbt.negocio.avaliacao.Avaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoEtapaAvaliacao;
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
	
	private GerenciadorAvaliacao gerenciadorAvaliacao;

	private GerenciadorQuestionario gerenciadorQuestionario;
	
	private BuscadorRespostaQuestionario buscadorRespostaQuestionario;
	
	private GerenciadorResultadoAvaliacao gerenciadorResultadoAvaliacao;

	@Autowired
	public MigradorAvaliacaoService(@Qualifier("calculadorQuestionarioMigrador")CalculadorQuestionario calculadorQuestionario,
			GerenciadorAvaliacao gerenciadorAvaliacao,
			GerenciadorQuestionario gerenciadorQuestionario,
			GerenciadorResultadoAvaliacao gerenciadorResultadoAvaliacao,
			BuscadorRespostaQuestionario buscadorRespostaQuestionario) {
		this.calculadorQuestionario = calculadorQuestionario;
		this.gerenciadorAvaliacao = gerenciadorAvaliacao;
		this.gerenciadorQuestionario = gerenciadorQuestionario;
		this.gerenciadorResultadoAvaliacao = gerenciadorResultadoAvaliacao;
		this.buscadorRespostaQuestionario = buscadorRespostaQuestionario;
	}

	@Transactional
	public List<Avaliacao> criarAvaliacoesIndependentes() {
		// TODO: lançar erro caso as EBT's já estejam migradas
		List<Avaliacao> avaliacoes = new AvaliacaoEbtBuilder().build();
		String jsonEstrutura = ConversorQuestionario.toJson(new BlocoEbtBuilder().build());
		Questionario questionario = Questionario.builder().estrutura(jsonEstrutura).build();

		gerenciadorQuestionario.salvar(questionario);

		for (Avaliacao avaliacao : avaliacoes) {
			avaliacao.setQuestionario(questionario);
			gerenciadorAvaliacao.salvar(avaliacao);
		}

		migrarRespostasAvaliacoesIndependentes(avaliacoes);
		
		criarResultadosAvaliacao(avaliacoes);
		
		return avaliacoes;
	}

	@Transactional
	private void migrarRespostasAvaliacoesIndependentes(List<Avaliacao> avaliacoes) {
		for (Avaliacao avaliacao : avaliacoes) {
			Questionario questionario = avaliacao.getQuestionario();
			String jsonQuestionario = questionario.getEstrutura();
			
			MigradorArquivoRespostaParser respostasParser = new MigradorArquivoRespostaParser(Constantes.ARQUIVO_EBT_MIGRACAO);
			List<CSVRecord> csvRecords = respostasParser.parse(avaliacao.getEdicao());

			// Para cada linha do arquivo, construimos um questionário com as respostas preenchidas
			for (CSVRecord record : csvRecords) {
				List<Bloco> blocos = ConversorQuestionario.toBlocos(jsonQuestionario);

				processaBloco(record, blocos);
				
				// Monta a resposta do questionario e grava
				RespostaQuestionario respostaQuestionario = RespostaQuestionario.builder()
					.questionario(avaliacao.getQuestionario())
					.avaliacao(avaliacao)
					.estrutura(ConversorQuestionario.toJson(blocos))
					.etapaAvaliacao(TipoEtapaAvaliacao.AVALIACAO)
					.usuario(Usuario.builder().id(Constantes.ID_ADMIN_CGU_PADRAO).build())
					.municipio(record.get(QuestionarioEbtHeader.municipio)) // TODO: temporario
					.uf(record.get(QuestionarioEbtHeader.uf)) // TODO: temporario
					.finalizada(true)
					.build();

				questionario.addResposta(respostaQuestionario);
			}
		}
	}
	
	@Transactional
	private void criarResultadosAvaliacao(List<Avaliacao> avaliacoes) {
		for (Avaliacao avaliacao : avaliacoes) {
			List<RespostaQuestionario> respostas = buscadorRespostaQuestionario.getPorIdAvaliacao(avaliacao.getId());
			
			Map<String, BigDecimal> municipioNotaArquivo = getMunicipioNotaArquivo(avaliacao);
			BigDecimal ERRO_PERMITIDO = new BigDecimal("0.01");
			for (RespostaQuestionario resposta : respostas) {
				BigDecimal notaFinalSistema = calculadorQuestionario.calculaNota(ConversorQuestionario.toBlocos(resposta.getEstrutura()));
				BigDecimal notaFinalArquivo = municipioNotaArquivo.get(resposta.getUf()+"_"+resposta.getMunicipio());
				
				// Algumas notas estavam com erro de 0,01 no cálculo. Por isso fazemos essa comparação e guardamos a nota da avaliação original nesses casos
				if (notaFinalSistema.subtract(notaFinalArquivo).abs().compareTo(ERRO_PERMITIDO) == 0) {
					notaFinalSistema = notaFinalArquivo;
				}
				
				ResultadoAvaliacao resultado = 
						ResultadoAvaliacao.builder()
						.avaliacao(avaliacao)
						.nomeMunicipio(resposta.getMunicipio())
						.uf(resposta.getUf())
						.nota(notaFinalSistema)
						.build();
				
				gerenciadorResultadoAvaliacao.salvar(resultado);
			}
		}
	}
	

	private void processaBloco(CSVRecord record, List<Bloco> blocos) {
		for (Bloco bloco : blocos) {
			List<Questao> questoes = bloco.getQuestoes();
			
			for (Questao questao : questoes) {
				if (questao.getTipo() == TipoQuestao.MULTIPLA_ESCOLHA) {
					processaQuestaoMultiplaEscolha(record, questao);
				} else if (questao.getTipo() == TipoQuestao.DESCRITIVA) {
					processaQuestaoDescritiva(record, questao);
				} else if (questao.getTipo() == TipoQuestao.MATRIZ) {
					processaQuestaoMatriz(record, questao);
				}
			}
		}
	}

	private void processaQuestaoMatriz(CSVRecord record, Questao questao) {
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

	private void processaQuestaoDescritiva(CSVRecord record, Questao questao) {
		QuestionarioEbtHeader questionarioEbtHeader = EbtUtil.MAPEAMENTO_PERGUNTA_RESPOSTA
				.get(questao.getPergunta());
		String respostaQuestao = record.get(questionarioEbtHeader);
		QuestaoDescritiva qd = (QuestaoDescritiva) questao;
		qd.setResposta(respostaQuestao);
	}

	private void processaQuestaoMultiplaEscolha(CSVRecord record, Questao questao) {
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
	}
	
	private Map<String, BigDecimal> getMunicipioNotaArquivo(Avaliacao avaliacao) {
		// Logica para salvar dados do .CSV caso o desvio seja de 0,01
		MigradorArquivoRespostaParser parser = new MigradorArquivoRespostaParser(Constantes.ARQUIVO_EBT_MIGRACAO);
		Map<String, BigDecimal> municipioNota = new HashMap<String, BigDecimal>();
		List<CSVRecord> records = parser.parse(avaliacao.getEdicao());
		for (CSVRecord record : records) { // TODO: colocar codigo do IBGE no mapa depois que tiver o SQL populado
			String uf = record.get(QuestionarioEbtHeader.uf);
			String municipio = record.get(QuestionarioEbtHeader.municipio);
			String nota = record.get(QuestionarioEbtHeader.nota);
			
			municipioNota.put(uf+"_"+municipio, new BigDecimal(nota));
		}
		
		return municipioNota;
	}
}