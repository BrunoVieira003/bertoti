package pedido.subsistemas;

public class Estoque {
    public boolean verificarDisponibilidade(String produto, int quantidade) {
        System.out.println("Verificando disponibilidade de " + quantidade + " unidade(s) do produto: " + produto);
        return true;
    }

    public void atualizarEstoque(String produto, int quantidade) {
        System.out.println("Atualizando estoque: removendo " + quantidade + " unidade(s) do produto: " + produto);
    }
}
