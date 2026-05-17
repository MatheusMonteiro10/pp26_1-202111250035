package model;

import mediator.Mediador;
import service.MicrofoneCronometro;

import java.util.ArrayList;
import java.util.List;

public class PoliticoColaborador {

    private final String nome;
    private final Mediador mediador;
    private final boolean sorteado;
    private final MicrofoneCronometro microfone;

    private final List<Observer> eleitores;

    public PoliticoColaborador(String nome) {

        this(
                nome,
                null,
                false,
                new MicrofoneCronometro()
        );
    }

    public PoliticoColaborador(
            String nome,
            Mediador mediador,
            boolean sorteado,
            MicrofoneCronometro microfone
    ) {

        this.nome = nome;
        this.mediador = mediador;
        this.sorteado = sorteado;
        this.microfone = microfone;

        this.eleitores = new ArrayList<>();
    }

    public void adicionarEleitor(
            Observer observer
    ) {
        eleitores.add(observer);
    }

    public void removerEleitor(
            Observer observer
    ) {
        eleitores.remove(observer);
    }

    private void notificarEleitores() {

        String mensagem =
                "Candidato " +
                        nome +
                        " está falando";

        for (Observer observer : eleitores) {
            observer.atualizar(mensagem);
        }
    }

    public void falar(int tempo) {

        notificarEleitores();

        microfone.ativar();

        try {
            microfone.esperarTempo(tempo);
        } finally {
            microfone.desativar();
        }
    }

    public String getNome() {
        return nome;
    }

    public Mediador getMediador() {
        return mediador;
    }

    public boolean getSorteado() {
        return sorteado;
    }

    public MicrofoneCronometro getMicrofone() {
        return microfone;
    }
}