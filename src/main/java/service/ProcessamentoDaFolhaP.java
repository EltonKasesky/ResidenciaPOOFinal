package service;

import exception.DependenteException;
import model.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProcessamentoDaFolhaP {

	private static final DateTimeFormatter formaData = DateTimeFormatter.ofPattern("yyyyMMdd");

	public static List<Funcionario> processaArquivo(String arquivoEntrada, String arquivoRejeitado) throws IOException {
		List<Funcionario> funcionarios = new ArrayList<>();
		Set<String> cpfsDosFuncionarios = new HashSet<>();
		Set<String> cpfsDosDependentes = new HashSet<>();
		List<String> linhasRejeitadas = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader (new FileReader (arquivoEntrada));
			BufferedWriter bwRejeitados = new BufferedWriter (new FileWriter(arquivoRejeitado))) {	
			
				String linha;
				Funcionario funcionarioAtual = null;
				
				while ((linha = br.readLine()) != null) {
					if (linha.trim().isEmpty()) {
						funcionarioAtual = null;
						continue;
					}
				String[] campos = linha.split(";");
				if (campos.length < 4) {
					linhasRejeitadas.add(linha);
					continue;
				}
				if (funcionarioAtual == null) {
					String nome = campos[0].trim();
					String cpf = campos[1].trim();
					LocalDate dataNascimento;
					Double salarioBruto;
					
					try {
						dataNascimento = LocalDate.parse(campos[2].trim(), formaData);
						salarioBruto = Double.parseDouble(campos[3].trim());
					}
					catch (Exception e) {
						linhasRejeitadas.add(linha);
						continue;
					}
					
					if (!cpfsDosFuncionarios.add(cpf)) {
						bwRejeitados.write("Funcionário com CPF duplicado: " + cpf);
						continue;
					}
					
						funcionarioAtual = new Funcionario(nome, cpf, dataNascimento, salarioBruto, salarioBruto, salarioBruto, null);
						funcionarios.add(funcionarioAtual);
					}
					else {
						String nome = campos[0].trim();
						String cpf = campos[1].trim();
						LocalDate dataNascimento;
						Parentesco parentesco;
						
						try {
							dataNascimento = LocalDate.parse(campos[2].trim(), formaData);
							parentesco = Parentesco.valueOf(campos[3].trim().toUpperCase());
							}
						catch (Exception e) {
							linhasRejeitadas.add(linha);
							continue;
						}
						if (!cpfsDosDependentes.add(cpf)) {
							bwRejeitados.write("Dependente com CPF duplicado: " + cpf);
							continue;
						}
						
						try {Dependente dependente = new Dependente(nome, cpf, dataNascimento, parentesco);
						funcionarioAtual.adicionarDependente(dependente);
						
						} 
						catch(DependenteException e) {
							bwRejeitados.write("Dependente inválido: " + e.getMessage());
						}
					}
					
				}
				
		}
		return funcionarios;
	}
	public static void gerarArquivosSaida(List<Funcionario> funcionarios, String arquivoSaida) throws IOException{ 
		try (BufferedWriter bwSaida = new BufferedWriter(new FileWriter(arquivoSaida))) {
			for (Funcionario funcionario : funcionarios) {
				funcionario.calcularDescontos();
				
				bwSaida.write(String.format("%s;%s;%.2f;%.2f;%.2f\n", funcionario.getNome(), funcionario.getCpf(), funcionario.getDescontoINSS(), funcionario.getDescontoIR(), funcionario.calcularSalarioLiquido()));
			}
		}
	}

}
