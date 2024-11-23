import filesystem.Arquivo;
import filesystem.ComponenteArquivo;
import filesystem.Diretorio;

public class Main {
    public static void main(String[] args) {
        // Criando arquivos
        ComponenteArquivo arquivo1 = new Arquivo("arquivo1.txt", 10);
        ComponenteArquivo arquivo2 = new Arquivo("arquivo2.txt", 20);

        // Criando diretórios
        ComponenteArquivo diretorio1 = new Diretorio("Diretório1");
        ComponenteArquivo diretorio2 = new Diretorio("Diretório2");

        // Adicionando arquivos ao diretório
        ((Diretorio) diretorio1).adicionar(arquivo1);
        ((Diretorio) diretorio2).adicionar(arquivo2);

        // Criando um diretório principal que irá conter outros diretórios
        Diretorio diretorioPrincipal = new Diretorio("Raiz");
        diretorioPrincipal.adicionar(diretorio1);
        diretorioPrincipal.adicionar(diretorio2);

        // Exibindo os detalhes do diretório principal
        diretorioPrincipal.exibirDetalhes();
    }
}
