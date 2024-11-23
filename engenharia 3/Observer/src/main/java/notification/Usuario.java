package notification;

import observer.Observer;

// Classe concreta que implementa o comportamento de Observer
public class Usuario implements Observer {
    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("Usuário " + nome + " recebeu a mensagem: " + mensagem);
    }
}
