package produto;

import desconto.DescontoStrategy;

public class Produto {
    public DescontoStrategy tipoDesconto;
    private String nome;
    private double preco;

    public Produto(String nome, double preco, DescontoStrategy tipoDesconto){
        this.nome = nome;
        this.preco = preco;
        this.tipoDesconto = tipoDesconto;
    }

    public double getPrecoBase() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setTipoDesconto(DescontoStrategy tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public double getPrecoFinal(){
        return tipoDesconto.aplicar(this.preco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
