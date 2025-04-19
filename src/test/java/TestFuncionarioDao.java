import database.ConnectionFactory;
import repository.FuncionarioDao;
import util.LeituraCSV;

import java.sql.Connection;

public class TestFuncionarioDao {
    public static void main(String[] args) {
        Connection conn = new ConnectionFactory().getConnection();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        funcionarioDao.inserirFuncionarios(LeituraCSV.leituraCSV(), conn);
    }
}
