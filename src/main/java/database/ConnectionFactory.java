package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String url = "jdbc:postgresql://localhost:5432/jdbc_poofinal";
    private String user = "postgres";
    private String password = "123456";

    Connection conn = null;
    public Connection getConnection(){
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Banco de dados conectado com sucesso!");
        } catch(SQLException e){
            System.err.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
        return conn;
    }
}
