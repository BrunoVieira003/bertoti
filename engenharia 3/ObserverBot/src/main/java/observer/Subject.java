package observer;

public interface Subject {
    void adicionarObservador(String key, Observer observer);
    void removerObservador(String key);
    void notificarObservadores(String mensagem);
    void notificarObservadoresComImagem(byte[] imagem);
}
