package service;

import mediator.DRListener;
import model.PoliticoColaborador;

public class MicrofoneCronometro {

    private boolean microfoneAtivo;

    // Callback para o mediador — injetado após a criação do debate
    private DRListener listener;

    public void setDRListener(DRListener listener) {
        this.listener = listener;
    }

    public void ativar() {
        microfoneAtivo = true;
        System.out.println("Microfone ativado");
    }

    public void desativar() {
        microfoneAtivo = false;
        System.out.println("Microfone desativado");
    }

    public void esperarTempo(int tempo) {
        System.out.println("Tempo restante: " + tempo + " segundos");
        try {
            Thread.sleep(tempo * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Erro no cronômetro", e);
        }
    }

    /*
      Simula o acionamento do botão DR integrado ao microfone.
      Notifica o mediador via callback DRListener.
     */
    public void acionarBotaoDR(PoliticoColaborador politico) {
        System.out.println("[DR] Botão DR acionado por: " + politico.getNome());
        if (listener != null) {
            listener.solicitarDR(politico);
        }
    }
}