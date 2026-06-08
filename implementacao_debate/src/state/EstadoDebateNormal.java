package state;

import model.PoliticoColaborador;
import service.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class EstadoDebateNormal implements EstadoDebate {

    // Fila de políticos que solicitaram DR, em ordem de acionamento
    private final Queue<PoliticoColaborador> filaDR;

    private final Logger logger;

    public EstadoDebateNormal() {
        this.filaDR = new LinkedList<>();
        this.logger = Logger.getInstance();
    }

    @Override
    public void solicitarDR(PoliticoColaborador politico) {

        for (PoliticoColaborador p : filaDR) {
            if (p.getNome().equalsIgnoreCase(politico.getNome())) {
                System.out.println(
                        "[DR] " + politico.getNome() +
                                " já está na fila — solicitação ignorada."
                );
                return;
            }
        }

        filaDR.add(politico);
        logger.registerLog("DR solicitado por: " + politico.getNome());
        System.out.println(
                "[DR] " + politico.getNome() +
                        " enfileirado para DR (posição " + filaDR.size() + ")"
        );
    }

    @Override
    public void processarDRsPendentes(int tempoDR) {
        if (filaDR.isEmpty()) {
            return;
        }

        logger.registerLog("Mediador concedeu Direitos de Resposta pendentes");
        System.out.println("\n[Mediador] Concedendo Direitos de Resposta...");

        // Transiciona para EstadoDefesaDR e executa as defesas
        EstadoDefesaDR estadoDefesa = new EstadoDefesaDR(filaDR, tempoDR);
        estadoDefesa.executarDefesas();

        filaDR.clear();

        logger.registerLog("Defesas de DR finalizadas — debate retorna ao fluxo normal");
        System.out.println("[Mediador] Debate retorna ao fluxo normal.\n");
    }

    @Override
    public boolean emDefesaDR() {
        return false;
    }

    public int quantidadeDRsPendentes() {
        return filaDR.size();
    }
}