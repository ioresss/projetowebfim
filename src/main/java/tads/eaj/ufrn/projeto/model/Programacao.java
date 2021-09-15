package tads.eaj.ufrn.projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Programacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "programacao_sessao",
                joinColumns = @JoinColumn(name = "programacao_id"),
                inverseJoinColumns = @JoinColumn(name = "sessao_id"))
    private List<Sessao> sessoes;

}
