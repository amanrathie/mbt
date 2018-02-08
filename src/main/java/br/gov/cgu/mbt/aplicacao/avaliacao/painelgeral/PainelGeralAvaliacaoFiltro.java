package br.gov.cgu.mbt.aplicacao.avaliacao.painelgeral;

import br.gov.cgu.mbt.negocio.TipoPoder;
import br.gov.cgu.mbt.negocio.TipoStatus;
import br.gov.cgu.mbt.negocio.avaliacao.TipoAvaliacao;
import br.gov.cgu.mbt.negocio.avaliacao.TipoFaseAvaliacao;
import br.gov.cgu.persistencia.querybuilder.Filtro;
import lombok.Data;

@Data
public class PainelGeralAvaliacaoFiltro extends Filtro {
	
	private TipoAvaliacao tipo;
	private TipoFaseAvaliacao fase;
	private TipoPoder poder;
	private TipoStatus status;
}
