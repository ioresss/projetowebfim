package tads.eaj.ufrn.projeto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import tads.eaj.ufrn.projeto.model.Filme;
import tads.eaj.ufrn.projeto.model.Sessao;
import tads.eaj.ufrn.projeto.service.FilmeService;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoRequest {
    private long id;
    private String horario;
    private FilmeRequest filme;
    private SalaRequest sala;

    public Sessao build(){
        Sessao s = new Sessao().setId(id).setHorario(horario);
        if(filme != null)
            s.setFilme(filme.build());
        if(sala != null)
            s.setSala(sala.build());
        return s;
    }
}
