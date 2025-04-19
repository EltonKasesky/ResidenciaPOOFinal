import database.ConnectionFactory;
import repository.FolhaDePagamentoDao;
import util.LeituraCSV;

import java.sql.Connection;
import java.util.Scanner;

public class TestFolhaDePagamentoDao {
    public static void main(String[] args) {
        Connection conn = new ConnectionFactory().getConnection();
        Scanner sc = new Scanner(System.in);
        FolhaDePagamentoDao folhaDePagamentoDao = new FolhaDePagamentoDao();

        System.out.println("Insira o caminho do diretório ou seu arquivo: ");
        String diretorio = sc.nextLine();

        folhaDePagamentoDao.inserirPagamento(LeituraCSV.leituraCSV(diretorio), conn);
    }
}
