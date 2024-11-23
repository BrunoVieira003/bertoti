package filesystem;

public class Arquivo implements ComponenteArquivo {
    private String nome;
    private int tamanho;

    public Arquivo(String nome, int tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Arquivo: " + nome + " | Tamanho: " + tamanho + " KB");
    }
}
