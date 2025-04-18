package service;

public class CalculoService {
	private static final Double inssS = 8157.47;
	private static final Double inssAL = 0.14;
	private static final Double dependenteV = 189.59;

	public static Double calcularINSS(Double salarioBruto) {
		Double base = Math.min(salarioBruto, inssS);
		return base * inssAL;
	}

	public static Double calcularIR(Double salarioBruto, Double descontoINSS, int numeroDependentes) {
		double baseCalculo = salarioBruto - descontoINSS - (numeroDependentes * dependenteV);

		if (baseCalculo <= 2259.00) {
			return 0.0;
		} else if (baseCalculo <= 2826.65) {
			return baseCalculo * 0.075 - 169.44;
		} else if (baseCalculo <= 3751.05) {
			return baseCalculo * 0.15 - 381.44;
		} else if (baseCalculo <= 4664.68) {
			return baseCalculo * 0.225 - 662.77;
		} else {
			return baseCalculo * 0.275 - 896.00;
		}
	}
	public static Double calcularSalarioLiquido(Double salarioBruto, Double descontoINSS, Double descontoIR) {
		return salarioBruto - descontoINSS - descontoIR;
	}
}
