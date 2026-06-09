package state;

import mediator.MediadorDebate;
import model.Configuracao;

public interface EstadoDebate {
    void executar(MediadorDebate mediador, Configuracao configuracao);
    boolean podeSolicitarDR();
}
