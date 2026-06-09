package state;

import mediator.MediadorDebate;
import model.Configuracao;
import model.PoliticoColaborador;
import service.Logger;

public class EstadoDefesaDR implements EstadoDebate {

    private static final int TEMPO_DR_SEGUNDOS = 3;

    @Override
    public void executar(MediadorDebate mediador, Configuracao configuracao) {

        Logger logger = Logger.getInstance();

        logger.registerLog("--- Fase de Direito de Resposta iniciada ---");

        PoliticoColaborador solicitante;

        // Processa cada solicitante na ordem em que pressionou o botão DR
        while ((solicitante = mediador.proximoDRPendente()) != null) {
            logger.registerLog(
                    "DR concedido a: " + solicitante.getNome()
            );
            // falarDR notifica eleitores com mensagem específica de DR
            solicitante.falarDR(TEMPO_DR_SEGUNDOS);
        }

        logger.registerLog("--- Fase de Direito de Resposta encerrada ---");

        // Retorna ao estado normal para o próximo ciclo
        mediador.setEstado(new EstadoDebateNormal());
    }

    @Override
    public boolean podeSolicitarDR() {
        // Bloqueia novas solicitações durante as defesas
        return false;
    }
}
