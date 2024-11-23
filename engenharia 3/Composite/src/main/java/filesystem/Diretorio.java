package filesystem;

import java.util.ArrayList;
import java.util.List;

public class Diretorio implements ComponenteArquivo {
    private String nome;
    private List<ComponenteArquivo> componentes = new ArrayList<>();

    public Diretorio(String nome) {
        this.nome = nome;
    }

    // Adiciona arquivos ou subdiretórios ao diretório
    public void adicionar(ComponenteArquivo componente) {
        componentes.add(componente);
    }

    // Remove arquivos ou subdiretórios do diretório
    public void remover(ComponenteArquivo componente) {
        componentes.remove(componente);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Diretório: " + nome);
        for (ComponenteArquivo componente : componentes) {
            componente.exibirDetalhes();  // Chama o método de exibição para arquivos ou subdiretórios
        }
    }
}
