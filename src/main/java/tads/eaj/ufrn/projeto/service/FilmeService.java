package tads.eaj.ufrn.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.projeto.dto.response.FilmeResponse;
import tads.eaj.ufrn.projeto.model.Filme;
import tads.eaj.ufrn.projeto.repository.FilmeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private FilmeRepository f;

    @Autowired
    public void setRepository(FilmeRepository f){
        this.f = f;
    }

    public Filme inserir(Filme filme){
        filme = f.save(filme);
        return filme;
    }

    public List<FilmeResponse> listar(){
        List<FilmeResponse> filmes = new ArrayList<FilmeResponse>();
        for (Filme filme : f.findAll())
            filmes.add(new FilmeResponse(filme));
        return filmes;
    }

    public Optional<Filme> buscar(Long id){
        return this.f.findById(id);
    }

    public Filme editar(Filme filme){
        this.f.saveAndFlush(filme);
        return filme;
    }

    public void deletar(Long id){
        this.f.deleteById(id);
    }
}
