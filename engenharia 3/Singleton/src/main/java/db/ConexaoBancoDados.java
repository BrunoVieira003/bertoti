package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    private static ConexaoBancoDados instancia;
    private Connection conexao;

    private String url = "jdbc:mysql://localhost:3306/mysql";
    private String usuario = "root";
    private String senha = "root";

    private ConexaoBancoDados() {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão com o banco de dados estabelecida.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConexaoBancoDados getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoBancoDados(); // Criação da instância, se necessário
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão fechada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
