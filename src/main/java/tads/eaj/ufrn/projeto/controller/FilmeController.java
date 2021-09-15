package tads.eaj.ufrn.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.projeto.dto.request.FilmeRequest;
import tads.eaj.ufrn.projeto.dto.response.FilmeResponse;
import tads.eaj.ufrn.projeto.model.Filme;
import tads.eaj.ufrn.projeto.service.FilmeService;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/filme")
public class FilmeController {

    private FilmeService filmeService;

    @Autowired
    public void setFilmeService(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity inserir (@RequestBody FilmeRequest filmeRequest){
        Filme filme = filmeRequest.build();
        filme = filmeService.inserir(filme);
        return ResponseEntity.created(URI.create("/filme/"+filme.getId())).body(new FilmeResponse(filme));
    }

    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok().body(filmeService.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        Optional<Filme> filme = filmeService.buscar(id);
        if (filme.isPresent()){
            return ResponseEntity.ok().body(new FilmeResponse(filme.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/editar/{id}")
    public ResponseEntity editar (@RequestBody Filme filme, @PathVariable Long id){
        Optional<Filme> c = filmeService.buscar(id);
        if(c.isPresent() && c.get().getId().equals(id) && filme.getId().equals(id))
            return ResponseEntity.ok().body(new FilmeResponse(filmeService.editar(filme)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        if(filmeService.buscar(id).isPresent()){
            filmeService.deletar(id);
            return ResponseEntity.ok(linkTo(FilmeController.class).withRel("Listar Filmes"));
        }else
            return ResponseEntity.notFound().build();
    }
}
