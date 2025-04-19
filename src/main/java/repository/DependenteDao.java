package repository;

import exception.DependenteException;
import exception.FolhaDePagamentoException;
import model.Dependente;
import model.Funcionario;
import util.CPFInvalidoCSV;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DependenteDao implements CPF {
    public void inserirDependente(List<Funcionario> funcionarios, Connection conn){
        try {
            String sql = "INSERT INTO dependente(fk_id_funcionario, nome, cpf, data_nascimento, parentesco) values (?, ?, ?, ?, ?)";

            for (Funcionario funcionario : funcionarios) {
                for(Dependente dependente : funcionario.getDependentes()){
                    try {
                        if(!verificaCPF(dependente.getCpf(), conn)){
                            PreparedStatement stmt = conn.prepareStatement(sql);

                            Integer id = pegarIDPeloCPF(funcionario.getCpf(), conn);
                            stmt.setInt(1, id);
                            stmt.setString(2, dependente.getNome());
                            stmt.setString(3, dependente.getCpf());
                            stmt.setDate(4, Date.valueOf(dependente.getDataNascimento()));
                            stmt.setString(5, dependente.getParentesco().toString());
                            stmt.execute();

                            System.out.println("O dependente " + dependente.getNome() + " foi registrado com sucesso!");
                        } else {
                            List<Funcionario> cpfInvalido = new ArrayList<>();

                            cpfInvalido.add(funcionario);
                            CPFInvalidoCSV.inserirCPF(cpfInvalido, conn);
                            throw new SQLException("O CPF do dependente " + dependente.getNome() + " já existe no banco de dados!");
                        }
                    } catch(SQLException e){
                        System.err.println("Erro ao inserir dependente: " + dependente.getNome());
                        e.printStackTrace();
                    } catch(DependenteException e){
                        System.err.println("Erro com dependente: " + dependente.getNome());
                        System.err.println("Motivo: " + e.getMessage());
                    }
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Boolean verificaCPF(String cpf, Connection conn) {
        String sql = "SELECT 1 FROM dependente WHERE cpf = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.err.println("Erro na validação de CPF(s)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer pegarIDPeloCPF(String cpf, Connection conn) {
        String sql = "SELECT id FROM funcionario WHERE cpf = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            System.err.println("Erro em pegar a chave estrangeira do ID pelo CPF na tabela de funcionario!");
            e.printStackTrace();
        }
        return null;
    }
}
