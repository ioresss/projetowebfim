package tads.eaj.ufrn.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.projeto.dto.request.SessaoRequest;
import tads.eaj.ufrn.projeto.dto.request.SessaoRequest;
import tads.eaj.ufrn.projeto.dto.response.SessaoResponse;
import tads.eaj.ufrn.projeto.model.Sessao;
import tads.eaj.ufrn.projeto.model.Sessao;
import tads.eaj.ufrn.projeto.repository.SessaoRepository;
import tads.eaj.ufrn.projeto.service.SessaoService;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/sessao")
public class SessaoController {
    SessaoService sessaoService;

    @Autowired
    public void setSessaoService(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping
    public ResponseEntity inserir (@RequestBody SessaoRequest sessaoRequest){
        Sessao sessao = sessaoRequest.build();
        sessao = sessaoService.inserir(sessao);
        return ResponseEntity.created(URI.create("/sessao/"+sessao.getId())).body(new SessaoResponse(sessao));
    }

    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok().body(sessaoService.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Optional<Sessao> sessao = sessaoService.buscar(id);
        if (sessao.isPresent()){
            return ResponseEntity.ok().body(new SessaoResponse(sessao.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/editar/{id}")
    public ResponseEntity editar (@RequestBody Sessao sessao, @PathVariable Long id){
        Optional<Sessao> c = sessaoService.buscar(id);
        if(c.isPresent() && c.get().getId().equals(id) && sessao.getId().equals(id))
            return ResponseEntity.ok().body(new SessaoResponse(sessaoService.editar(sessao)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        if(sessaoService.buscar(id).isPresent()){
            sessaoService.deletar(id);
            return ResponseEntity.ok(linkTo(SessaoController.class).withRel("Listar Sessões"));
        }else
            return ResponseEntity.notFound().build();
    }

}
