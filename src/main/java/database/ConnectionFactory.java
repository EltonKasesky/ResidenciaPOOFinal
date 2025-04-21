package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {
    Dotenv dotenv = Dotenv.load();

    private final String url = dotenv.get("DB_URL");

    Connection conn = null;
    public Connection getConnection(){
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Banco de dados conectado com sucesso!");
        } catch(SQLException e){
            System.err.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
        return conn;
    }
}
