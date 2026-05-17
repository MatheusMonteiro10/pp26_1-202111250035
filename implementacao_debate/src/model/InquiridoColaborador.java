package model;

public class InquiridoColaborador
        extends PoliticoColaborador {

    public InquiridoColaborador(
            PoliticoColaborador politico
    ) {

        super(
                politico.getNome(),
                politico.getMediador(),
                politico.getSorteado(),
                politico.getMicrofone()
        );
    }
}