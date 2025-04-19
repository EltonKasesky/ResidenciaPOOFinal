import database.ConnectionFactory;
import repository.DependenteDao;
import repository.FuncionarioDao;
import util.LeituraCSV;

import java.sql.Connection;
import java.util.Scanner;

public class TestDependenteDao {
    public static void main(String[] args) {
        Connection conn =  new ConnectionFactory().getConnection();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        DependenteDao dependenteDao = new DependenteDao();
        Scanner sc = new Scanner(System.in);


        System.out.println("Insira o caminho do diretório ou seu arquivo: ");
        String diretorio = sc.nextLine();

        funcionarioDao.inserirFuncionarios(LeituraCSV.leituraCSV(diretorio), conn);
        dependenteDao.inserirDependente(LeituraCSV.leituraCSV(diretorio), conn);
    }
}
