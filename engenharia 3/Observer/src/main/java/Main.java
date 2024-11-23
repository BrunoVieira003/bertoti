import notification.CanalNotificacao;
import notification.Usuario;

public class Main {
    public static void main(String[] args) {
        CanalNotificacao canal = new CanalNotificacao();

        Usuario usuario1 = new Usuario("Alice");
        Usuario usuario2 = new Usuario("Bob");
        Usuario usuario3 = new Usuario("Carlos");

        canal.adicionarObservador(usuario1);
        canal.adicionarObservador(usuario2);
        canal.adicionarObservador(usuario3);

        canal.publicarMensagem("Bem-vindos ao canal!");
        canal.publicarMensagem("Novo conteúdo disponível!");

        canal.removerObservador(usuario2);
        canal.publicarMensagem("Apenas para observadores restantes.");
    }
}
