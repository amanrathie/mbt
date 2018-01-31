package br.gov.cgu.mbt.negocio.avaliacao.questionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Integer> {

}
