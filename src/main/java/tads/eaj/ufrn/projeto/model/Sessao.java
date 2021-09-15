package tads.eaj.ufrn.projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String horario;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "filme_id")
    private Filme filme;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @ManyToMany(mappedBy = "sessoes", cascade = CascadeType.ALL)
    private List<Programacao> programacoes;

}
