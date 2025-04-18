package model;

import exception.DependenteException;

import java.time.LocalDate;
import java.time.Period;

public class Dependente extends Pessoa{
	private Parentesco parentesco;
	
	public Dependente (String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) throws DependenteException {
		super(nome, cpf, dataNascimento);
		if (Period.between(dataNascimento, LocalDate.now()).getYears()>=18) {
			throw new DependenteException("O Dependente tem que ser menor de 18 anos: " + nome);
		}
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return 	super.toString() +
				", parentesco: " + getParentesco();
	}

	public Parentesco getParentesco() {
		return parentesco;
	}
}
