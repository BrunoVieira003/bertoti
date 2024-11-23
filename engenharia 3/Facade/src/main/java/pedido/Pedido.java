package pedido;

import pedido.subsistemas.Envio;
import pedido.subsistemas.Estoque;
import pedido.subsistemas.Pagamento;

public class Pedido {
    private Estoque estoque;
    private Pagamento pagamento;
    private Envio envio;

    public Pedido() {
        this.estoque = new Estoque();
        this.pagamento = new Pagamento();
        this.envio = new Envio();
    }

    public void realizarCompra(String produto, int quantidade, double valor, String metodoPagamento, String endereco) {
        System.out.println("Iniciando o processo de compra...");

        if (estoque.verificarDisponibilidade(produto, quantidade)) {
            if (pagamento.processarPagamento(metodoPagamento, valor)) {
                estoque.atualizarEstoque(produto, quantidade);
                envio.agendarEnvio(produto, endereco);
                System.out.println("Compra realizada com sucesso!");
            } else {
                System.out.println("Falha no processamento do pagamento.");
            }
        } else {
            System.out.println("Produto fora de estoque.");
        }
    }
}
