package model;

public class Configuracao {

    private int pergunta;
    private int resposta;
    private int replica;
    private int treplica;

    public int getPerguntaTempo() {
        return pergunta;
    }

    public void setPerguntaTempo(int pergunta) {
        this.pergunta = pergunta;
    }

    public int getRespostaTempo() {
        return resposta;
    }

    public void setRespostaTempo(int resposta) {
        this.resposta = resposta;
    }

    public int getReplicaTempo() {
        return replica;
    }

    public void setReplicaTempo(int replica) {
        this.replica = replica;
    }

    public int getTreplicaTempo() {
        return treplica;
    }

    public void setTreplicaTempo(int treplica) {
        this.treplica = treplica;
    }
}