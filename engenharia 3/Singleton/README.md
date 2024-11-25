# Singleton

## Utilização do Singleton
```java
import db.ConexaoBancoDados;

public class Main {
    public static void main(String[] args) {
        // Acessando a instância única de ConexaoBancoDados
        ConexaoBancoDados conexao1 = ConexaoBancoDados.getInstancia();
        System.out.println("Conexão 1: " + conexao1.getConexao());

        // Acessando novamente a instância de ConexaoBancoDados
        ConexaoBancoDados conexao2 = ConexaoBancoDados.getInstancia();
        System.out.println("Conexão 2: " + conexao2.getConexao());

        // Verificando que as duas variáveis apontam para a mesma instância
        System.out.println("\nAs instâncias são iguais? " + (conexao1 == conexao2));

        // Fechando a conexão
        conexao1.fecharConexao();
    }
}
```

## Diagrama
![Singleton drawio](https://github.com/user-attachments/assets/e29e4822-0482-4ca7-a6d1-60f5c6d7cec7)
