package model;

public class InquiridoColaborador extends PoliticoColaborador {

    public InquiridoColaborador(PoliticoColaborador politico) {
        super(
                politico.getNome(),
                politico.getSorteado(),
                politico.getMicrofone(),
                politico.getEleitores()
        );
    }
}