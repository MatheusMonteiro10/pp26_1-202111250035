package state;

import mediator.MediadorDebate;
import model.Configuracao;
import service.Logger;

public class EstadoDebateNormal implements EstadoDebate {

    @Override
    public void executar(MediadorDebate mediador, Configuracao configuracao) {

        Logger logger = Logger.getInstance();

        logger.registerLog("Pergunta iniciada");
        mediador.getInquiridor().falar(configuracao.getPerguntaTempo());

        logger.registerLog("Resposta iniciada");
        mediador.getInquirido().falar(configuracao.getRespostaTempo());

        logger.registerLog("Réplica iniciada");
        mediador.getInquiridor().falar(configuracao.getReplicaTempo());

        logger.registerLog("Tréplica iniciada");
        mediador.getInquirido().falar(configuracao.getTreplicaTempo());

        // Verifica se há DR pendente ao fim do ciclo normal
        if (mediador.temDRPendente()) {
            logger.registerLog("Direito de Resposta solicitado — transitando para EstadoDefesaDR");
            mediador.setEstado(new EstadoDefesaDR());
            mediador.getEstadoAtual().executar(mediador, configuracao);
        }
    }

    @Override
    public boolean podeSolicitarDR() {
        // Durante o debate normal, o botão DR está habilitado
        return true;
    }
}
