package tads.eaj.ufrn.projeto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tads.eaj.ufrn.projeto.model.Programacao;
import tads.eaj.ufrn.projeto.model.Sessao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramacaoRequest {
    private Long id;
    private Date data;
    private List<SessaoRequest> sessoes;

    public Programacao build(){
        ArrayList<Sessao> lista = new ArrayList<>();
        for(SessaoRequest sr : this.sessoes)
            lista.add(sr.build());
        return new Programacao()
                .setId(id)
                .setData(data)
                .setSessoes(lista);
    }
}
