package pedido.subsistemas;

public class Pagamento {
    public boolean processarPagamento(String metodo, double valor) {
        System.out.println("Processando pagamento de R$" + valor + " via " + metodo);
        return true;
    }
}
