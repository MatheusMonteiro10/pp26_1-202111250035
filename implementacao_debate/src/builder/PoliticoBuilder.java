package builder;

import model.Observer;
import model.PoliticoColaborador;
import service.MicrofoneCronometro;

import java.util.ArrayList;
import java.util.List;

public class PoliticoBuilder implements Builder<PoliticoColaborador> {

    private String nome;
    private boolean sorteado = false;
    private MicrofoneCronometro microfone;
    private final List<Observer> eleitores = new ArrayList<>();

    public PoliticoBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    //Define se já foi sorteado como inquiridor. Padrão: false
    public PoliticoBuilder sorteado(boolean sorteado) {
        this.sorteado = sorteado;
        return this;
    }

    /*
      Fornece um MicrofoneCronometro já configurado externamente.
      Se não chamado, um novo microfone é criado automaticamente no build().
     */
    public PoliticoBuilder microfone(MicrofoneCronometro microfone) {
        this.microfone = microfone;
        return this;
    }

    // Adiciona um observer já construído
    public PoliticoBuilder adicionarEleitor(Observer eleitor) {
        this.eleitores.add(eleitor);
        return this;
    }

    // Cria e vincula um Eleitor pelo nome e candidato favorito.
    public PoliticoBuilder adicionarEleitor(String nomeEleitor, String candidatoFavorito) {
        this.eleitores.add(
                new EleitorBuilder()
                        .nome(nomeEleitor)
                        .candidatoFavorito(candidatoFavorito)
                        .build()
        );
        return this;
    }

    @Override
    public PoliticoColaborador build() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalStateException("PoliticoBuilder: nome é obrigatório");
        }
        if (microfone == null) {
            microfone = new MicrofoneCronometro();
        }
        return new PoliticoColaborador(nome, sorteado, microfone, eleitores);
    }
}