package service;

import builder.EleitorBuilder;
import builder.PoliticoBuilder;
import model.Eleitor;
import model.Observer;
import model.PoliticoColaborador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorPoliticos {

    private final List<PoliticoColaborador> politicos;
    private int totalSorteados;

    public GerenciadorPoliticos() {
        this.politicos      = new ArrayList<>();
        this.totalSorteados = 0;
    }

    // Cria candidato via PoliticoBuilder e registra na lista
    public PoliticoColaborador criarPolitico(String nome) {
        PoliticoColaborador politico = new PoliticoBuilder()
                .nome(nome)
                .build();
        politicos.add(politico);
        return politico;
    }

    /*
     Cadastra eleitor no candidato de seu interesse via EleitorBuilder
     Preserva a referência existente, sem recriar instância
     */
    public void cadastrarEleitor(String nomeEleitor, String nomeCandidato) {
        PoliticoColaborador politico = obterPolitico(nomeCandidato);
        if (politico == null) {
            throw new IllegalArgumentException(
                    "Candidato não encontrado: " + nomeCandidato
            );
        }
        Eleitor eleitor = new EleitorBuilder()
                .nome(nomeEleitor)
                .candidatoFavorito(nomeCandidato)
                .build();
        politico.adicionarEleitor(eleitor);
    }

    public PoliticoColaborador obterPolitico(String nome) {
        for (PoliticoColaborador p : politicos) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        return null;
    }

    // Sorteia candidato ainda não inquiridor
    public PoliticoColaborador sortear() {
        if (todosJaForamSorteados()) {
            throw new IllegalStateException("Todos os candidatos já foram sorteados.");
        }
        Random random = new Random();
        PoliticoColaborador politico;
        do {
            politico = politicos.get(random.nextInt(politicos.size()));
        } while (politico.getSorteado());

        politico.setSorteado(true);   // na mesma instância — observers intactos
        totalSorteados++;
        return politico;
    }

    public boolean todosJaForamSorteados() {
        return totalSorteados >= politicos.size();
    }
}