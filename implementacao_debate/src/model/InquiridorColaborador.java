package model;

public class InquiridorColaborador extends PoliticoColaborador {

    public InquiridorColaborador(PoliticoColaborador politico) {
        super(
                politico.getNome(),
                politico.getSorteado(),
                politico.getMicrofone(),
                politico.getEleitores()
        );
    }

    public void escolherInquirido(PoliticoColaborador politico) {
        if (politico.getNome().equalsIgnoreCase(this.getNome())) {
            throw new IllegalArgumentException(
                    "Esse candidato não pode ser escolhido como inquirido."
            );
        }
        System.out.println(
                "[RODADA] " + getNome() + " escolheu " +
                        politico.getNome() + " como inquirido."
        );
    }
}