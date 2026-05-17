package model;

public class InquiridorColaborador
        extends PoliticoColaborador {

    public InquiridorColaborador(
            PoliticoColaborador politico
    ) {

        super(
                politico.getNome(),
                politico.getMediador(),
                politico.getSorteado(),
                politico.getMicrofone()
        );
    }

    public void escolherInquirido(PoliticoColaborador politico) {
        System.out.println(getNome() + " escolheu " + politico.getNome() + " como inquirido.");
    }
}