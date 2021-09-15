package tads.eaj.ufrn.projeto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "duracao", nullable = false)
    private int duracao;
    @Column(name = "classificacao", nullable = false)
    private String classificacao;
    @Column(name = "direcao", nullable = false)
    private String direcao;
    @Column(name = "faixaEtaria", nullable = false)
    private int faixaEtaria;

    @OneToOne(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    private Sessao sessao;
}
