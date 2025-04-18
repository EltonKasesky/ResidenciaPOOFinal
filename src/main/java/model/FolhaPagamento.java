package model;

import java.time.LocalDate;

public class FolhaPagamento {
	private int codigo;
	private Funcionario funcionario;
	private LocalDate dataPagamento;
	private Double descontoINSS;
	private Double descontoIR;
	private Double salarioLiquido;

	public FolhaPagamento(int codigo, Funcionario funcionario, LocalDate dataPagamento) {
		this.codigo = codigo;
		this.funcionario = funcionario;
		this.dataPagamento = dataPagamento;
		this.descontoINSS = funcionario.getDescontoINSS();
		this.descontoIR = funcionario.getDescontoIR();
		this.salarioLiquido = funcionario.calcularSalarioLiquido();
	}

	public int getCodigo() {
		return codigo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public Double getDescontoINSS() {
		return descontoINSS;
	}

	public Double getDescontoIR() {
		return descontoIR;
	}

	public Double getSalarioLiquido() {
		return salarioLiquido;
	}

	@Override
	public String toString() {
		return "FolhaPagamento [codigo=" + codigo + ", funcionario=" + funcionario + ", dataPagamento=" + dataPagamento
				+ ", descontoINSS=" + descontoINSS + ", descontoIR=" + descontoIR + ", salarioLiquido=" + salarioLiquido
				+ "]";
	}

}
