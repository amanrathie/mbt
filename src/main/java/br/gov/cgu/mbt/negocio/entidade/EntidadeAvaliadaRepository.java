package br.gov.cgu.mbt.negocio.entidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeAvaliadaRepository extends JpaRepository<EntidadeAvaliada, Integer> {

}
