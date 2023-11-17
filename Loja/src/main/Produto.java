package main;

public class Produto {
	private String nome;
	private String marca;
	private int quantidade;
	private double preco;
	
	public Produto(String nome, String marca, int quantidade, double preco) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	

}
