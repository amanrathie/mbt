package br.gov.cgu.mbt.negocio.configuracao;

import br.gov.cgu.persistencia.jpa.RepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class ConfiguracaoRepository extends RepositoryJpa<Configuracao, String> {
}
