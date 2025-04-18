import database.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionDB {
    public static void main(String[] args) {
        try {
            Connection conn = new ConnectionFactory().getConnection();
            conn.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
