package ui;

import facade.Facade;

public class CLI implements UserInterface {

    private final Facade facade;

    public CLI(Facade facade) {
        this.facade = facade;
    }

    @Override
    public void operacao() {

        facade.cadastrarPolitico("Ana");
        facade.cadastrarPolitico("Carlos");
        facade.cadastrarPolitico("Marina");

        facade.cadastrarEleitor(
                "João",
                "Ana"
        );

        facade.cadastrarEleitor(
                "Maria",
                "Ana"
        );

        facade.cadastrarEleitor(
                "Pedro",
                "Carlos"
        );

        facade.configuracao(
                3,
                3,
                2,
                2
        );

        while (!facade.todosJaForamInquiridores()) {

            facade.sortearInquiridor();

            facade.escolherInquirido("Carlos");

            facade.iniciarDebate();

            System.out.println("----------------");
        }

        System.out.println("\nRELATÓRIO FINAL");
        System.out.println(facade.getLogs());
    }
}