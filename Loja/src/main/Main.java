package main;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String args[]) throws Exception {
		Produto prod1 = new Produto("Amaciante", "Omo", 10, 7.80);
		Produto prod2 = new Produto("Manteiga", "Doriana", 25, 4.39);
		
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(prod1);
		produtos.add(prod2);
		Loja loja = new Loja(produtos);
		
		loja.venderProduto("Manteiga", 2);
	}
}
