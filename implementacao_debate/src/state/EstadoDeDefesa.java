package state;

import model.PoliticoColaborador;
import service.Logger;

import java.util.Queue;

/*
  Neste estado:
     O fluxo normal do debate é interrompido
     Cada político da fila tem seu microfone aberto por tempoDR segundos
     Novas solicitações de DR são bloqueadas
     Ao final, o mediador retorna a EstadoDebateNormal
 */
public class EstadoDefesaDR implements EstadoDebate {

    private final Queue<PoliticoColaborador> filaDR;
    private final int tempoDR;
    private final Logger logger;

    public EstadoDefesaDR(Queue<PoliticoColaborador> filaDR, int tempoDR) {
        this.filaDR  = filaDR;
        this.tempoDR = tempoDR;
        this.logger  = Logger.getInstance();
    }

    @Override
    public void solicitarDR(PoliticoColaborador politico) {
        // Bloqueado durante as defesas — evita ciclos infinitos de DR
        System.out.println(
                "[DR BLOQUEADO] " + politico.getNome() +
                        " tentou solicitar DR durante as defesas — ignorado."
        );
    }

    @Override
    public void processarDRsPendentes(int tempoDR) {}

    @Override
    public boolean emDefesaDR() {
        return true;
    }

    /*
      Percorre a fila e abre o microfone de cada solicitante.
      Chama falarDR() que notifica os eleitores com mensagem de DR.
    */
    public void executarDefesas() {
        while (!filaDR.isEmpty()) {
            PoliticoColaborador politico = filaDR.poll();

            logger.registerLog(
                    "Direito de Resposta concedido a: " + politico.getNome()
            );
            System.out.println(
                    "\n[DR] Microfone aberto para defesa de " +
                            politico.getNome() + " (" + tempoDR + "s)"
            );

            // falarDR notifica eleitores
            politico.falarDR(tempoDR);
        }
    }
}