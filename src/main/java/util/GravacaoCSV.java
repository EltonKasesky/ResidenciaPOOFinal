package util;

import model.Funcionario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GravacaoCSV {
    public static void gerarCSV(List<Funcionario> funcionarios) {
        try {
            FileWriter fw = new FileWriter("/Curso/logFuncionario.csv");
            PrintWriter pw = new PrintWriter(fw);

            for (Funcionario f : funcionarios) {
                pw.printf("%s;%s;%.2f;%.2f;%.2f%n",
                        f.getNome(),
                        f.getCpf(),
                        f.getDescontoINSS(),
                        f.getDescontoIR(),
                        f.getSalarioLiquido()
                );
            }

            pw.close();
        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo CSV!");
            e.printStackTrace();
        }
    }
}
