package mediator;

import model.Configuracao;
import model.InquiridoColaborador;
import model.InquiridorColaborador;
import model.PoliticoColaborador;
import service.Logger;
import service.DRListener;
import state.EstadoDebate;
import state.EstadoDebateNormal;

import java.util.HashMap;
import java.util.Map;

public class MediadorDebate implements Mediador, DRListener {

    private InquiridorColaborador inquiridor;
    private InquiridoColaborador inquirido;

    private EstadoDebate estadoAtual;

    private final Map<String, PoliticoColaborador> politicosRegistrados;

    public MediadorDebate() {
        this.estadoAtual = new EstadoDebateNormal();
        this.politicosRegistrados = new HashMap<>();
    }

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

        estadoAtual.processarDRsPendentes(configuracao.getDireitoRespostaTempo());
    }

    public void registrarPolitico(PoliticoColaborador politico) {
        politicosRegistrados.put(politico.getNome().toLowerCase(), politico);
        politico.getMicrofone().setDrListener(this);
    }

    @Override
    public void onBotaoDRAcionado(String nomePolitico) {
        PoliticoColaborador politico =
                politicosRegistrados.get(nomePolitico.toLowerCase());
        if (politico != null) {
            estadoAtual.solicitarDR(politico);
        }
    }

    public void setInquiridor(InquiridorColaborador inquiridor) {
        this.inquiridor = inquiridor;
    }

    public void setInquirido(InquiridoColaborador inquirido) {
        this.inquirido = inquirido;
    }
}