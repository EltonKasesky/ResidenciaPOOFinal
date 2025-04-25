package service;

public class CalculoService {
	public static Double calcularINSS(Double salarioBruto) {
		double valorINSS;

		if(salarioBruto <= ServiceINSS.BASE1.faixa){
			valorINSS = salarioBruto * ServiceINSS.BASE1.taxa - ServiceINSS.BASE1.desconto;
		} else if(salarioBruto <= ServiceINSS.BASE2.faixa){
			valorINSS = salarioBruto * ServiceINSS.BASE2.taxa - ServiceINSS.BASE2.desconto;
		} else if (salarioBruto <= ServiceINSS.BASE3.faixa){
			valorINSS = salarioBruto * ServiceINSS.BASE3.taxa - ServiceINSS.BASE3.desconto;
		} else {
			valorINSS = salarioBruto * ServiceINSS.BASE4.taxa - ServiceINSS.BASE4.desconto;
		}

		if(valorINSS >= ServiceINSS.TETO){
			valorINSS = ServiceINSS.TETO;
		}

		return valorINSS;
	}

	public static Double calcularIR(Double salarioBruto, Double descontoINSS, int numeroDependentes) {
		double baseCalculo = salarioBruto - descontoINSS - (numeroDependentes * ServiceIR.DEPENDENTE);

		if (baseCalculo <= ServiceIR.BASE1.faixa) {
			return baseCalculo * ServiceIR.BASE1.taxa - ServiceIR.BASE1.desconto;
		} else if (baseCalculo <= ServiceIR.BASE2.faixa) {
			return baseCalculo * ServiceIR.BASE2.taxa - ServiceIR.BASE2.desconto;
		} else if (baseCalculo <= ServiceIR.BASE3.faixa) {
			return baseCalculo * ServiceIR.BASE3.taxa - ServiceIR.BASE3.desconto;
		} else if (baseCalculo <= ServiceIR.BASE4.faixa) {
			return baseCalculo * ServiceIR.BASE4.taxa - ServiceIR.BASE4.desconto;
		} else {
			return baseCalculo * ServiceIR.BASE5.taxa - ServiceIR.BASE5.desconto;
		}
	}

	public static Double calcularSalarioLiquido(Double salarioBruto, Double descontoINSS, Double descontoIR) {
		return salarioBruto - descontoINSS - descontoIR;
	}
}
