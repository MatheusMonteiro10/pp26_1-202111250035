package facade;

import mediator.MediadorDebate;
import model.Configuracao;
import model.InquiridoColaborador;
import model.InquiridorColaborador;
import model.PoliticoColaborador;
import service.GerenciadorPoliticos;
import service.Logger;

public class Facade {

    private static Facade instance;

    private final MediadorDebate mediadorDebate;
    private final Configuracao configuracao;
    private final GerenciadorPoliticos gerenciadorPoliticos;
    private final Logger logger;

    private Facade() {
        this.mediadorDebate = new MediadorDebate();
        this.configuracao = new Configuracao();
        this.gerenciadorPoliticos = new GerenciadorPoliticos();
        this.logger = Logger.getInstance();

        logger.registerLog("Facade iniciada");
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public void cadastrarPolitico(String nome) {
        PoliticoColaborador politico = gerenciadorPoliticos.criarPolitico(nome);
        logger.registerLog("Político cadastrado: " + politico.getNome());
    }

    public void cadastrarEleitor(String nomeEleitor, String nomeCandidato) {
        gerenciadorPoliticos.cadastrarEleitor(nomeEleitor, nomeCandidato);
        logger.registerLog("Eleitor " + nomeEleitor + " inscrito em " + nomeCandidato);
    }

    public void configuracao(int pergunta, int resposta, int replica, int treplica) {
        configuracao.setPerguntaTempo(pergunta);
        configuracao.setRespostaTempo(resposta);
        configuracao.setReplicaTempo(replica);
        configuracao.setTreplicaTempo(treplica);

        logger.registerLog("Tempos configurados");
    }

    public void sortearInquiridor() {
        PoliticoColaborador politico = gerenciadorPoliticos.sortear();
        InquiridorColaborador inquiridor = new InquiridorColaborador(politico);
        mediadorDebate.setInquiridor(inquiridor);
        logger.registerLog("Inquiridor sorteado: " + politico.getNome());
    }

    public void escolherInquirido(String nome) {
        PoliticoColaborador politico = gerenciadorPoliticos.obterPolitico(nome);

        if (politico == null) {
            throw new IllegalArgumentException("Político não encontrado");
        }

        InquiridoColaborador inquirido = new InquiridoColaborador(politico);
        mediadorDebate.setInquirido(inquirido);
        logger.registerLog("Inquirido escolhido: " + politico.getNome());
    }

    public void iniciarDebate() {
        logger.registerLog("Debate iniciado");
        mediadorDebate.debate(configuracao);
        logger.registerLog("Debate encerrado");
    }

    public boolean todosJaForamInquiridores() {
        return gerenciadorPoliticos.todosJaForamSorteados();
    }

    public String getLogs() {
        return logger.getAllLogs();
    }
}