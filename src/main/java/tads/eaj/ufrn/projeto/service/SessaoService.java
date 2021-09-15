package tads.eaj.ufrn.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.projeto.dto.response.SessaoResponse;
import tads.eaj.ufrn.projeto.model.Filme;
import tads.eaj.ufrn.projeto.model.Sessao;
import tads.eaj.ufrn.projeto.repository.SessaoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoService {
    SessaoRepository s;

    @Autowired
    public void setSessaoRepository(SessaoRepository sessaoRepository) {
        this.s = sessaoRepository;
    }

    public Sessao inserir(Sessao sessao){
        sessao = s.save(sessao);
        return sessao;
    }

    public List<SessaoResponse> listar(){
        List<SessaoResponse> sessoes = new ArrayList<>();
        for (Sessao sessao : s.findAll())
            sessoes.add(new SessaoResponse(sessao));
        return sessoes;
    }

    public Optional<Sessao> buscar(Long id){
        return this.s.findById(id);
    }

    public Sessao editar(Sessao sessao){
        this.s.saveAndFlush(sessao);
        return sessao;
    }

    public void deletar(Long id){
        this.s.deleteById(id);
    }
}
