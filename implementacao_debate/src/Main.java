import facade.Facade;
import ui.CLI;

public class Main {

    public static void main(String[] args) {
        CLI cli = new CLI(Facade.getInstance());

        cli.operacao();
    }
}