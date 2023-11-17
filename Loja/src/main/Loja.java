package main;

import java.util.List;

public class Loja {
	private List<Produto> produtos;
	private double dinheiroCaixa;

	public Loja(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Produto pegarProduto(String nomeProduto) throws ProdutoNaoEncontrado {
		for (Produto produto : produtos) {
			if (nomeProduto.equals(produto.getNome())) {
				return produto;
			}
		}
		
		throw new ProdutoNaoEncontrado(nomeProduto + " não encontrado");
	}
	
	public void venderProduto(String nomeProduto, int quantidade) throws Exception{
		Produto prod = this.pegarProduto(nomeProduto);
		if (prod.getQuantidade() <= quantidade) {
			throw new EstoqueInsuficiente(nomeProduto + " possui menos de " + quantidade + " unidades");
		}
		
		prod.setQuantidade(prod.getQuantidade() - quantidade);
		double precoTotal = prod.getPreco() * quantidade;
		this.setDinheiroCaixa(this.dinheiroCaixa + precoTotal);
		
		int index = this.produtos.indexOf(prod);
		this.produtos.set(index, prod);
		
		System.out.println("========== Vendido ==========");
		System.out.println("Produto: " + prod.getNome() + "-" + prod.getMarca());
		System.out.println("Qtd: " + quantidade);
		System.out.println("Preco unitario: " + prod.getPreco());
		System.out.println("Preco total: " + precoTotal);
		
	}
	
	
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public double getDinheiroCaixa() {
		return dinheiroCaixa;
	}

	public void setDinheiroCaixa(double dinheiroCaixa) {
		this.dinheiroCaixa = dinheiroCaixa;
	}



	public static class ProdutoNaoEncontrado extends Exception{
		private static final long serialVersionUID = 1L;
		
		public ProdutoNaoEncontrado(String errorMessage) {
	        super(errorMessage);
	    }
	}
	
	public static class EstoqueInsuficiente extends Exception{
		private static final long serialVersionUID = 1L;
		
		public EstoqueInsuficiente(String errorMessage) {
			super(errorMessage);
		}
	}
	
}
