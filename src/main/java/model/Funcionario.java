package model;

import java.time.LocalDate;
import java.util.*;

import service.CalculoService;

public class Funcionario extends Pessoa {
	private Double salarioBruto;
	private Double descontoINSS;
	private Double descontoIR;
	private Set<Dependente> dependentes = new HashSet<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto, Double descontoINSS, Double descontoIR, Set<Dependente> dependentes) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		this.descontoINSS = descontoINSS;
		this.descontoIR = descontoIR;
		this.dependentes = dependentes;
	}

	public Funcionario(String nome, String cpf, LocalDate dataNascimento) {
		super(nome, cpf, dataNascimento);
	}

	public void adicionarDependente(Dependente dependente) {
		dependentes.add(dependente);
	}

	public Set<Dependente> getDependentes() {
		return dependentes;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public Double getDescontoINSS() {
		return descontoINSS;
	}

	public void setDescontoINSS(Double descontoINSS) {
		this.descontoINSS = descontoINSS;
	}

	public Double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(Double descontoIR) {
		this.descontoIR = descontoIR;
	}

	public void calcularDescontos() {
		this.descontoINSS = CalculoService.calcularINSS(salarioBruto);
		this.descontoIR = CalculoService.calcularIR(salarioBruto, descontoINSS, dependentes.size());
	}

	public Double calcularSalarioLiquido() {
		return salarioBruto - descontoINSS - descontoIR;
	}

	@Override
	public String toString() {
		return "O Funcionario" +
				" nome: " + getNome() +
				", cpf: " + getCpf() +
				", dataNascimento: " + getDataNascimento() +
				", salarioBruto: R$" + String.format("%.2f", getSalarioBruto()) +
				", descontoINSS: R$" + String.format("%.2f", getDescontoINSS()) +
				", descontoIR: R$" + String.format("%.2f", getDescontoIR()) +
				", dependentes: " + getDependentes();
	}
}
