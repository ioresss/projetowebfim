package tads.eaj.ufrn.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.projeto.dto.request.SalaRequest;
import tads.eaj.ufrn.projeto.dto.response.SalaResponse;
import tads.eaj.ufrn.projeto.model.Sala;
import tads.eaj.ufrn.projeto.service.SalaService;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/sala")
public class SalaController {
    SalaService salaService;

    @Autowired
    public void setSalaService(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity inserir (@RequestBody SalaRequest salaRequest){
        Sala sala = salaRequest.build();
        sala = salaService.inserir(sala);
        return ResponseEntity.created(URI.create("/sala/"+sala.getId())).body(new SalaResponse(sala));
    }

    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok().body(salaService.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Optional<Sala> sala = salaService.buscar(id);
        if (sala.isPresent()){
            return ResponseEntity.ok().body(new SalaResponse(sala.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/editar/{id}")
    public ResponseEntity editar (@RequestBody Sala sala, @PathVariable Long id){
        Optional<Sala> c = salaService.buscar(id);
        if(c.isPresent() && c.get().getId().equals(id) && sala.getId().equals(id))
            return ResponseEntity.ok().body(new SalaResponse(salaService.editar(sala)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        if(salaService.buscar(id).isPresent()){
            salaService.deletar(id);
            return ResponseEntity.ok(linkTo(SalaController.class).withRel("Listar Sessões"));
        }else
            return ResponseEntity.notFound().build();
    }

}
