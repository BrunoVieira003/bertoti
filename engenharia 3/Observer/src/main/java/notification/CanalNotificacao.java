package notification;

import observer.Observer;
import observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class CanalNotificacao implements Subject {
    private List<Observer> observadores = new ArrayList<>();

    @Override
    public void adicionarObservador(Observer observer) {
        observadores.add(observer);
    }

    @Override
    public void removerObservador(Observer observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificarObservadores(String mensagem) {
        for (Observer observer : observadores) {
            observer.atualizar(mensagem);
        }
    }

    public void publicarMensagem(String mensagem) {
        System.out.println("Nova mensagem publicada: " + mensagem);
        notificarObservadores(mensagem);
    }
}
