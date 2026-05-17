package mediator;

import model.Configuracao;
import model.InquiridoColaborador;
import model.InquiridorColaborador;
import service.Logger;

public class MediadorDebate implements Mediador {

    private InquiridorColaborador inquiridor;
    private InquiridoColaborador inquirido;

    @Override
    public void debate(Configuracao configuracao) {

        Logger logger = Logger.getInstance();

        logger.registerLog("Pergunta iniciada");
        inquiridor.falar(configuracao.getPerguntaTempo());

        logger.registerLog("Resposta iniciada");
        inquirido.falar(configuracao.getRespostaTempo());

        logger.registerLog("Réplica iniciada");
        inquiridor.falar(configuracao.getReplicaTempo());

        logger.registerLog("Tréplica iniciada");
        inquirido.falar(configuracao.getTreplicaTempo());
    }

    public void setInquiridor(InquiridorColaborador inquiridor) {
        this.inquiridor = inquiridor;
    }

    public void setInquirido(InquiridoColaborador inquirido) {
        this.inquirido = inquirido;
    }
}