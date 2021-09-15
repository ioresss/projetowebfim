package tads.eaj.ufrn.projeto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import tads.eaj.ufrn.projeto.controller.FilmeController;
import tads.eaj.ufrn.projeto.model.Filme;
import tads.eaj.ufrn.projeto.model.Sessao;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilmeResponse extends RepresentationModel<FilmeResponse> {
    private Long id;
    private String titulo;
    private int duracao;
    private String classificacao;
    private String direcao;
    private int faixaEtaria;

    public FilmeResponse(Filme f){
        if(f != null){
            this.id = f.getId();
            this.titulo = f.getTitulo();
            this.duracao = f.getDuracao();
            this.classificacao = f.getClassificacao();
            this.direcao = f.getDirecao();
            this.faixaEtaria = f.getFaixaEtaria();

            this.add(linkTo(FilmeController.class).slash(f.getId()).withSelfRel());
            this.add(linkTo(FilmeController.class).slash("/editar/"+f.getId()).withRel("editar filme"));
            this.add(linkTo(FilmeController.class).slash("/deletar/"+f.getId()).withRel("deletar filme"));
            this.add(linkTo(FilmeController.class).withRel("listar filme"));
        }
    }
}
