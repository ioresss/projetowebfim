package tads.eaj.ufrn.projeto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tads.eaj.ufrn.projeto.model.Sala;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaRequest {
    private Long id;
    private String nome;
    private int acentos;
    private Boolean is3D;

    public Sala build(){
        return new Sala()
                .setId(this.id)
                .setNome(this.nome)
                .setAcentos(this.acentos)
                .setIs3D(this.is3D);
    }
}
