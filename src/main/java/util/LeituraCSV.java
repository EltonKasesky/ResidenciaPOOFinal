package util;

import exception.DependenteException;
import model.Funcionario;
import model.Dependente;
import model.Parentesco;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LeituraCSV {
    public static List<Funcionario> leituraCSV(String diretorio) {
        List<Funcionario> funcionarios = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        try {
            Scanner leitor = new Scanner(new FileReader(diretorio));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            List<String> bloco = new ArrayList<>();
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();

                if (linha.isEmpty() && !bloco.isEmpty()) {
                    Funcionario f = processarBloco(bloco, formatter);
                    if (f != null){
                        f.calcularDescontos();
                        f.calcularSalarioLiquido();
                        funcionarios.add(f);
                    }
                    bloco.clear();
                } else {
                    bloco.add(linha);
                }
            }

            if (!bloco.isEmpty()) {
                Funcionario f = processarBloco(bloco, formatter);
                if (f != null){
                    f.calcularDescontos();
                    f.calcularSalarioLiquido();
                    funcionarios.add(f);
                }
            }

            leitor.close();
        } catch (FileNotFoundException e) {
            System.err.println("Caminho especificado não encontrado!");
            e.printStackTrace();
        }
        return funcionarios;
    }

    private static Funcionario processarBloco(List<String> bloco, DateTimeFormatter formatter) {
        try {
            if (bloco.isEmpty()) return null;

            String[] dadosFuncionario = bloco.get(0).split(";");

            Funcionario f = new Funcionario(dadosFuncionario[0], dadosFuncionario[1], LocalDate.parse(dadosFuncionario[2], formatter));
            f.setSalarioBruto(Double.parseDouble(dadosFuncionario[3]));

            for(int i = 1; i < bloco.size(); i++) {
                String[] dadosDep = bloco.get(i).split(";");
                Dependente d = new Dependente(dadosDep[0], dadosDep[1], LocalDate.parse(dadosDep[2], formatter), Parentesco.valueOf(dadosDep[3].toUpperCase()));

                f.getDependentes().add(d);
            }
            return f;

        } catch(DependenteException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}