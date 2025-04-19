package repository;

import model.Dependente;
import model.Funcionario;

import java.sql.*;
import java.util.List;

public class DependenteDao implements CPF {
    public void inserirDependente(List<Funcionario> funcionarios, Connection conn){
        try {
            String sql = "INSERT INTO dependentes(fk_id_funcionario, nome, cpf, data_nascimento, parentesco) values (?, ?, ?, ?, ?)";

            for (Funcionario funcionario : funcionarios) {
                for(Dependente dependente : funcionario.getDependentes()){
                    if(!verificaCPF(dependente.getCpf(), conn)){
                        PreparedStatement stmt = conn.prepareStatement(sql);

                        Integer id = pegarIDPeloCPF(funcionario.getCpf(), conn);
                        stmt.setInt(1, id);
                        stmt.setString(2, dependente.getNome());
                        stmt.setString(3, dependente.getCpf());
                        stmt.setDate(4, Date.valueOf(dependente.getDataNascimento()));
                        stmt.setString(5, dependente.getParentesco().toString());
                        stmt.execute();

                        System.out.println("O dependente " + dependente.getNome() + " foi inserido com sucesso!");
                    } else {
                        throw new SQLException("O CPF do dependente " + dependente.getNome() + " já existe no banco de dados!");
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
        String sql = "SELECT 1 FROM funcionario WHERE cpf = ?";

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
