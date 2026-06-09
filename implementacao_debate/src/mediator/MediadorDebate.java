package mediator;

import model.Configuracao;
import model.InquiridoColaborador;
import model.InquiridorColaborador;
import model.PoliticoColaborador;
import service.Logger;
import state.EstadoDebate;
import state.EstadoDebateNormal;

import java.util.LinkedList;
import java.util.Queue;

public class MediadorDebate implements Mediador, DRListener {

    private InquiridorColaborador inquiridor;
    private InquiridoColaborador inquirido;

    // Contexto do State
    private EstadoDebate estadoAtual;

    private final Queue<PoliticoColaborador> filaDR;

    public MediadorDebate() {
        this.estadoAtual = new EstadoDebateNormal();
        this.filaDR = new LinkedList<>();
    }

    @Override
    public void debate(Configuracao configuracao) {
        estadoAtual.executar(this, configuracao);
    }

    @Override
    public void solicitarDR(PoliticoColaborador politico) {
        if (estadoAtual.podeSolicitarDR()) {
            filaDR.add(politico);
            Logger.getInstance().registerLog(
                    "Botão DR acionado por: " + politico.getNome()
            );
        } else {
            Logger.getInstance().registerLog(
                    "DR bloqueado — fase de defesas em andamento: " + politico.getNome()
            );
        }
    }

    public boolean temDRPendente() {
        return !filaDR.isEmpty();
    }

    public PoliticoColaborador proximoDRPendente() {
        return filaDR.poll();
    }

    public void setEstado(EstadoDebate estado) {
        this.estadoAtual = estado;
    }

    public EstadoDebate getEstadoAtual() {
        return estadoAtual;
    }

    public InquiridorColaborador getInquiridor() {
        return inquiridor;
    }

    public InquiridoColaborador getInquirido() {
        return inquirido;
    }

    public void setInquiridor(InquiridorColaborador inquiridor) {
        this.inquiridor = inquiridor;
    }

    public void setInquirido(InquiridoColaborador inquirido) {
        this.inquirido = inquirido;
    }
}