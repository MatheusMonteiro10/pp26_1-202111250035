package builder;

import model.Eleitor;

public class EleitorBuilder implements Builder<Eleitor> {

    private String nome;
    private String candidatoFavorito;

    public EleitorBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public EleitorBuilder candidatoFavorito(String candidatoFavorito) {
        this.candidatoFavorito = candidatoFavorito;
        return this;
    }

    @Override
    public Eleitor build() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalStateException("EleitorBuilder: nome é obrigatório");
        }
        if (candidatoFavorito == null || candidatoFavorito.isBlank()) {
            throw new IllegalStateException(
                    "EleitorBuilder: candidatoFavorito é obrigatório"
            );
        }
        return new Eleitor(nome, candidatoFavorito);
    }
}