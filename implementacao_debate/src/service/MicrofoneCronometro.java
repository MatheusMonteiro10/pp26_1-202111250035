package service;

public class MicrofoneCronometro {

    private boolean microfoneAtivo;

    public void ativar() {
        microfoneAtivo = true;

        System.out.println("Microfone ativado");
    }

    public void desativar() {
        microfoneAtivo = false;

        System.out.println("Microfone desativado");
    }

    public void esperarTempo(int tempo) {

        System.out.println(
                "Tempo restante: " + tempo + " segundos"
        );

        try {
            Thread.sleep(tempo * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Erro no cronômetro",
                    e
            );
        }
    }
}