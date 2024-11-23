package observer;

public interface Subject {
    void adicionarObservador(Observer observer);
    void removerObservador(Observer observer);
    void notificarObservadores(String mensagem);
}
