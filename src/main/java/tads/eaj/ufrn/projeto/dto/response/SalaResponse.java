package tads.eaj.ufrn.projeto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import tads.eaj.ufrn.projeto.controller.SalaController;
import tads.eaj.ufrn.projeto.controller.SessaoController;
import tads.eaj.ufrn.projeto.model.Sala;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaResponse extends RepresentationModel<SessaoResponse> {
    private Long id;
    private String nome;
    private int acentos;
    private Boolean is3D;

    public SalaResponse(Sala s){
        if(s != null){
            this.id = s.getId();
            this.nome = s.getNome();
            this.acentos = s.getAcentos();
            this.is3D = s.getIs3D();

            this.add(linkTo(SalaController.class).slash(s.getId()).withSelfRel());
            this.add(linkTo(SalaController.class).slash("/editar/"+s.getId()).withRel("editar sala"));
            this.add(linkTo(SalaController.class).slash("/deletar/"+s.getId()).withRel("deletar sala"));
            this.add(linkTo(SalaController.class).withRel("listar salas"));
        }
    }
}
