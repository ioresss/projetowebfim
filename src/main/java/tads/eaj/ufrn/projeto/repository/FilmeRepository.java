package tads.eaj.ufrn.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tads.eaj.ufrn.projeto.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
