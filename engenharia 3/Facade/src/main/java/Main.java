import pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido();

        String produto = "Notebook";
        int quantidade = 1;
        double valor = 4500.00;
        String metodoPagamento = "Cartão de Crédito";
        String endereco = "Rua das Flores, 123, São Paulo, SP";

        pedido.realizarCompra(produto, quantidade, valor, metodoPagamento, endereco);
    }
}
