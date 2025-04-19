import database.ConnectionFactory;
import repository.FuncionarioDao;
import util.LeituraCSV;

import java.sql.Connection;
import java.util.Scanner;

public class TestFuncionarioDao {
    public static void main(String[] args) {
        Connection conn = new ConnectionFactory().getConnection();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o caminho do diretório ou seu arquivo: ");
        String diretorio = sc.nextLine();

        funcionarioDao.inserirFuncionarios(LeituraCSV.leituraCSV(diretorio), conn);
    }
}
