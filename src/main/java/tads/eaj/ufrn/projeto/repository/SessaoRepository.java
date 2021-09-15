package tads.eaj.ufrn.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tads.eaj.ufrn.projeto.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}
