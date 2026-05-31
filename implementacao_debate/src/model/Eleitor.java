package model;

import prototype.Prototype;

public class Eleitor implements Observer, Prototype<Eleitor> {

    private final String nome;
    private final String candidatoFavorito;

    public Eleitor(String nome, String candidatoFavorito) {
        this.nome = nome;
        this.candidatoFavorito = candidatoFavorito;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println(
                "[NOTIFICAÇÃO] Eleitor: " + nome +
                        " | Favorito: " + candidatoFavorito +
                        " | " + mensagem
        );
    }

    @Override
    public Eleitor clonar() {
        return new Eleitor(this.nome, this.candidatoFavorito);
    }

    public String getNome()              { return nome;             }
    public String getCandidatoFavorito() { return candidatoFavorito; }
}