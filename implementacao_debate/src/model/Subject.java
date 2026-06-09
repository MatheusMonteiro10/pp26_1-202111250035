package model;

public interface Subject {

    void adicionarEleitor(Observer observer);

    void removerEleitor(Observer observer);

    void notificarEleitores();
}