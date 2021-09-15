package tads.eaj.ufrn.projeto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import tads.eaj.ufrn.projeto.controller.SessaoController;
import tads.eaj.ufrn.projeto.model.Sessao;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessaoResponse extends RepresentationModel<SessaoResponse>{
    private long id;
    private String horario;
    private FilmeResponse filme;
    private SalaResponse sala;

    public SessaoResponse(Sessao s){
        if(s != null){
            this.id = s.getId();
            this.horario = s.getHorario();
            if(s.getFilme() != null)
                this.filme = new FilmeResponse(s.getFilme());
            if(s.getSala() != null)
                this.sala = new SalaResponse(s.getSala());

            this.add(linkTo(SessaoController.class).slash(s.getId()).withSelfRel());
            this.add(linkTo(SessaoController.class).slash("/editar/"+s.getId()).withRel("editar sessão"));
            this.add(linkTo(SessaoController.class).slash("/deletar/"+s.getId()).withRel("deletar sessão"));
            this.add(linkTo(SessaoController.class).withRel("listar sessões"));
        }
    }
}
