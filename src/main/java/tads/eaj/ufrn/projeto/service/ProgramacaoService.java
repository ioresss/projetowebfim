package tads.eaj.ufrn.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.projeto.dto.response.FilmeResponse;
import tads.eaj.ufrn.projeto.dto.response.ProgramacaoResponse;
import tads.eaj.ufrn.projeto.model.Programacao;
import tads.eaj.ufrn.projeto.repository.ProgramacaoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramacaoService {
    private ProgramacaoRepository p;

    @Autowired
    public void setRepository(ProgramacaoRepository p){
        this.p = p;
    }

    public Programacao inserir(Programacao programacao){
        programacao = p.save(programacao);
        return programacao;
    }

    public List<ProgramacaoResponse> listar(){
        List<ProgramacaoResponse> programacoes = new ArrayList<>();
        for(Programacao programacao : p.findAll())
            programacoes.add(new ProgramacaoResponse(programacao));
        return programacoes;
    }

    public Optional<Programacao> buscar(Long id) {
        return this.p.findById(id);
    }

    public Programacao editar(Programacao programacao) {
        this.p.saveAndFlush(programacao);
        return programacao;
    }

    public void deletar(Long id){
        this.p.deleteById(id);
    }
}
