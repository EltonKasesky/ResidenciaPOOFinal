import util.GravacaoCSV;
import util.LeituraCSV;

import java.util.Scanner;

public class TestGravacaoCSV {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o caminho do diretório ou seu arquivo: ");
        String diretorio = sc.nextLine();

        GravacaoCSV.gerarCSV(LeituraCSV.leituraCSV(diretorio));
    }
}
