package tads.eaj.ufrn.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.ufrn.projeto.model.Programacao;

public interface ProgramacaoRepository extends JpaRepository<Programacao, Long> {
}