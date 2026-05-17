package model;

public class Eleitor implements Observer {

    private final String nome;
    private final String candidatoFavorito;

    public Eleitor(String nome, String candidatoFavorito) {
        this.nome = nome;
        this.candidatoFavorito = candidatoFavorito;
    }

    @Override
    public void atualizar(String mensagem) {

        System.out.println(
                "[NOTIFICAÇÃO] Eleitor: " +
                        nome +
                        " | " +
                        mensagem
        );
    }

    public String getCandidatoFavorito() {
        return candidatoFavorito;
    }
}