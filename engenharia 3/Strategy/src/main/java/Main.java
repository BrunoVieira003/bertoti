import desconto.DescontoPremium;
import desconto.DescontoStrategy;
import desconto.DescontoVIP;
import desconto.SemDesconto;
import produto.Produto;

public class Main {
    public static void main(String[] args){
        DescontoStrategy semDesconto = new SemDesconto();
        DescontoStrategy descontoVIP = new DescontoVIP();
        DescontoStrategy descontoPremium = new DescontoPremium();

        // Produto com preço original de 1000
        Produto produto = new Produto("Laptop", 1000.0, semDesconto);

        System.out.println("Produto: " + produto.getNome());
        System.out.println("Preço original: " + produto.getPrecoBase());
        System.out.println("Preço para cliente comum: " + produto.getPrecoFinal());

        // Alterando para cliente VIP
        produto.setTipoDesconto(descontoVIP);
        System.out.println("Preço para cliente VIP: " + produto.getPrecoFinal());

        // Alterando para cliente Premium
        produto.setTipoDesconto(descontoPremium);
        System.out.println("Preço para cliente Premium: " + produto.getPrecoFinal());
    }
}
