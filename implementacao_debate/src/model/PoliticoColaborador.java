package model;

import prototype.Prototype;
import service.MicrofoneCronometro;

import java.util.ArrayList;
import java.util.List;

public class PoliticoColaborador implements Prototype<PoliticoColaborador> {

    private final String nome;
    private boolean sorteado;
    private final MicrofoneCronometro microfone;
    private final List<Observer> eleitores;

    public PoliticoColaborador(
            String nome,
            boolean sorteado,
            MicrofoneCronometro microfone,
            List<Observer> eleitores
    ) {
        this.nome      = nome;
        this.sorteado  = sorteado;
        this.microfone = microfone;
        this.eleitores = new ArrayList<>(eleitores);
    }

    public void adicionarEleitor(Observer observer) {
        eleitores.add(observer);
    }

    public void removerEleitor(Observer observer) {
        eleitores.remove(observer);
    }

    private void notificarEleitores(String mensagem) {
        for (Observer observer : eleitores) {
            observer.atualizar(mensagem);
        }
    }

    public void falar(int tempo) {
        notificarEleitores("Candidato " + nome + " está falando");
        microfone.ativar();
        try {
            microfone.esperarTempo(tempo);
        } finally {
            microfone.desativar();
        }
    }

    @Override
    public PoliticoColaborador clonar() {
        List<Observer> eleitoresClonados = new ArrayList<>();
        for (Observer observer : this.eleitores) {
            if (observer instanceof Eleitor) {
                eleitoresClonados.add(((Eleitor) observer).clonar());
            } else {
                eleitoresClonados.add(observer);
            }
        }
        return new PoliticoColaborador(
                this.nome,
                this.sorteado,
                this.microfone,
                eleitoresClonados
        );
    }

    public void falarDR(int tempo) {
        notificarEleitores("Candidato " + nome + " está exercendo seu Direito de Resposta");
        microfone.ativar();
        try {
            microfone.esperarTempo(tempo);
        } finally {
            microfone.desativar();
        }
    }

    public String getNome() { return nome; }
    public boolean getSorteado() { return sorteado; }
    public MicrofoneCronometro getMicrofone() { return microfone; }

    public void setSorteado(boolean sorteado) {
        this.sorteado = sorteado;
    }

    // Retorna cópia da lista para evitar modificação externa
    public List<Observer> getEleitores() {
        return new ArrayList<>(eleitores);
    }
}