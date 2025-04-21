package util;

import model.Dependente;
import model.Funcionario;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

public class CPFInvalidoCSV {
    public static void inserirCPF(List<Funcionario> funcionarios, Connection conn){
        try {
             FileWriter fw = new FileWriter("/Curso/POOFinal/cpfinvalido.csv", true);
             PrintWriter pw = new PrintWriter(fw);

            for (Funcionario funcionario : funcionarios) {
                funcionario.calcularDescontos();
                pw.printf("%s;%s;%s;%.2f;%.2f;%.2f;%.2f\n",
                        funcionario.getNome(),
                        funcionario.getCpf(),
                        funcionario.getDataNascimento(),
                        funcionario.getSalarioBruto(),
                        funcionario.getDescontoINSS(),
                        funcionario.getDescontoIR(),
                        funcionario.calcularSalarioLiquido());
                for (Dependente dependente : funcionario.getDependentes()) {
                    pw.printf("%s;%s;%s;%s\n",
                            dependente.getNome(),
                            dependente.getCpf(),
                            dependente.getDataNascimento(),
                            dependente.getParentesco());
                }
                pw.println();
            }
             pw.close();
        } catch(Exception e) {
            System.out.println("Erro ao inserir CPF invalido!");
            e.printStackTrace();
        }
    }
}
