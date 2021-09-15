package tads.eaj.ufrn.projeto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import tads.eaj.ufrn.projeto.model.Filme;
import tads.eaj.ufrn.projeto.model.Sessao;
import tads.eaj.ufrn.projeto.service.FilmeService;
import tads.eaj.ufrn.projeto.service.SessaoService;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmeRequest {
    private long id;
    private String titulo;
    private int duracao;
    private String classificacao;
    private String direcao;
    private int faixaEtaria;
    private SessaoRequest sessao;


    public Filme build(){
        Filme f = new Filme()
                .setId(id)
                .setTitulo(titulo)
                .setDuracao(duracao)
                .setClassificacao(classificacao)
                .setDirecao(direcao)
                .setFaixaEtaria(faixaEtaria);
        if(this.sessao != null)
            f.setSessao(sessao.build());
        return f;
    }
}
