package tads.eaj.ufrn.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.projeto.dto.request.ProgramacaoRequest;
import tads.eaj.ufrn.projeto.dto.response.ProgramacaoResponse;
import tads.eaj.ufrn.projeto.model.Programacao;
import tads.eaj.ufrn.projeto.service.ProgramacaoService;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/programacao")
public class ProgramacaoController {

    private ProgramacaoService programacaoService;

    @Autowired
    public void setProgramacaoService(ProgramacaoService programacaoService) {
        this.programacaoService = programacaoService;
    }

    @PostMapping
    public ResponseEntity inserir (@RequestBody ProgramacaoRequest programacaoRequest){
        Programacao programacao = programacaoRequest.build();
        programacao = programacaoService.inserir(programacao);
        return ResponseEntity.created(URI.create("/programacao/"+programacao.getId())).body(new ProgramacaoResponse(programacao));
    }

    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok().body(programacaoService.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Optional<Programacao> programacao = programacaoService.buscar(id);
        if (programacao.isPresent()){
            return ResponseEntity.ok().body(new ProgramacaoResponse(programacao.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/editar/{id}")
    public ResponseEntity editar (@RequestBody Programacao programacao, @PathVariable Long id){
        Optional<Programacao> c = programacaoService.buscar(id);
        if(c.isPresent() && c.get().getId().equals(id) && programacao.getId().equals(id))
            return ResponseEntity.ok().body(new ProgramacaoResponse(programacaoService.editar(programacao)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        if(programacaoService.buscar(id).isPresent()){
            programacaoService.deletar(id);
            return ResponseEntity.ok(linkTo(ProgramacaoController.class).withRel("Listar Programacaos"));
        }else
            return ResponseEntity.notFound().build();
    }
}
