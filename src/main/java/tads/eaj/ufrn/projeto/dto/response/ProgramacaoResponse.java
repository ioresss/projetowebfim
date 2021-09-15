package tads.eaj.ufrn.projeto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tads.eaj.ufrn.projeto.dto.request.SessaoRequest;
import tads.eaj.ufrn.projeto.model.Programacao;
import tads.eaj.ufrn.projeto.model.Sessao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramacaoResponse {
    private Long id;
    private Date data;
    private List<SessaoResponse> sessoes;

    public ProgramacaoResponse(Programacao p){
        if(p != null){
            this.id = p.getId();
            this.data = p.getData();
            ArrayList<SessaoResponse> lista = new ArrayList<>();
            for(Sessao s : p.getSessoes())
                lista.add(new SessaoResponse(s));
            this.sessoes = lista;
        }
    }
}
