package service;

import mediator.Mediador;
import model.PoliticoColaborador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorPoliticos {

    private final List<PoliticoColaborador> politicos;
    private int totalSorteados;

    public GerenciadorPoliticos() {

        this.politicos = new ArrayList<>();
        this.totalSorteados = 0;
    }

    public PoliticoColaborador criarPolitico(
            String nome,
            Mediador mediador
    ) {

        PoliticoColaborador politico =
                new PoliticoColaborador(
                        nome,
                        mediador,
                        false,
                        new MicrofoneCronometro()
                );

        politicos.add(politico);

        return politico;
    }

    public PoliticoColaborador obterPolitico(
            String nome
    ) {

        for (PoliticoColaborador politico : politicos) {

            if (politico.getNome()
                    .equalsIgnoreCase(nome)) {

                return politico;
            }
        }

        return null;
    }

    public PoliticoColaborador sortear() {

        if (todosJaForamSorteados()) {

            throw new IllegalStateException(
                    "Todos já foram sorteados"
            );
        }

        Random random = new Random();

        PoliticoColaborador politico;

        do {

            politico = politicos.get(
                    random.nextInt(
                            politicos.size()
                    )
            );

        } while (politico.getSorteado());

        PoliticoColaborador atualizado =
                new PoliticoColaborador(
                        politico.getNome(),
                        politico.getMediador(),
                        true,
                        politico.getMicrofone()
                );

        politicos.set(
                politicos.indexOf(politico),
                atualizado
        );

        totalSorteados++;

        return atualizado;
    }

    public boolean todosJaForamSorteados() {

        return totalSorteados >= politicos.size();
    }
}