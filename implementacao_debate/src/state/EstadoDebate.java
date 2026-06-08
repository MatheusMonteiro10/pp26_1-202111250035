package state;

import model.PoliticoColaborador;

/*
  Define as operações cujo comportamento varia conforme o estado atual do debate:
    - Debate Normal: rodadas ocorrem e DRs são enfileirados
    - Defesa DR: defesas ocorrem; novas solicitações são bloqueadas
 */
public interface EstadoDebate {

    // Tenta registrar uma solicitação de Direito de Resposta
    void solicitarDR(PoliticoColaborador politico);

    // Verifica DRs pendentes e, se houver, executa as defesas em ordem de solicitação
    void processarDRsPendentes(int tempoDR);

    // Indica se o debate está em modo de defesa DR.
    boolean emDefesaDR();
}