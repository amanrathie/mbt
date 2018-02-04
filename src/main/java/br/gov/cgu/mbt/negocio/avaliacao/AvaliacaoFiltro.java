package br.gov.cgu.mbt.negocio.avaliacao;

import br.gov.cgu.persistencia.querybuilder.Filtro;
import lombok.Getter;
import lombok.Setter;

public class AvaliacaoFiltro extends Filtro{
	
	@Getter @Setter
	private TipoAvaliacao tipo;
	@Getter @Setter
	private TipoFaseAvaliacao fase;
	@Getter @Setter
	private TipoPoderAvaliacao poder;
	@Getter @Setter
	private TipoStatusAvaliacao status;
}
