package tads.eaj.ufrn.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.projeto.dto.response.SalaResponse;
import tads.eaj.ufrn.projeto.model.Sala;

import tads.eaj.ufrn.projeto.model.Sessao;
import tads.eaj.ufrn.projeto.repository.SalaRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    SalaRepository s;

    @Autowired
    public void setSalaRepository(SalaRepository salaRepository) {
        this.s = salaRepository;
    }

    public Sala inserir(Sala sala){
        sala = s.save(sala);
        return sala;
    }

    public List<SalaResponse> listar(){
        List<SalaResponse> salas = new ArrayList<SalaResponse>();
        for (Sala sala : s.findAll())
            salas.add(new SalaResponse(sala));
        return salas;
    }

    public Optional<Sala> buscar(Long id){
        return this.s.findById(id);
    }

    public Sala editar(Sala sala){
        this.s.saveAndFlush(sala);
        return sala;
    }

    public void deletar(Long id){
        this.s.deleteById(id);
    }
}
