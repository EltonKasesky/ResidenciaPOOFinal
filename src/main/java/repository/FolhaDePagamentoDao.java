package repository;

import exception.FolhaDePagamentoException;
import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FolhaDePagamentoDao implements CPF {
    public void inserirPagamento(List<Funcionario> funcionarios, Connection conn){
        try {
            String sql = "INSERT INTO folha_de_pagamento(fk_id_funcionario, salario_bruto, desconto_inss, desconto_ir, salario_liquido) VALUES (?, ?, ?, ?, ?)";

            for(Funcionario funcionario : funcionarios){
                try {
                    Integer id = pegarIDPeloCPF(funcionario.getCpf(), conn);
                    if(!verificaID(id, conn)){
                        PreparedStatement stmt = conn.prepareStatement(sql);

                        funcionario.calcularDescontos();
                        stmt.setInt(1, id);
                        stmt.setDouble(2, funcionario.getSalarioBruto());
                        stmt.setDouble(3, funcionario.getDescontoINSS());
                        stmt.setDouble(4, funcionario.getDescontoIR());
                        stmt.setDouble(5, funcionario.calcularSalarioLiquido());
                        stmt.execute();

                        System.out.println("Folha de pagamento registrada no nome de " + funcionario.getNome());
                    } else {
                        throw new SQLException("O funcionario " + funcionario.getNome() + " já esta registrado na folha de pagamento!");
                    }
                } catch(SQLException e){
                    System.err.println("Erro ao inserir folha de pagamento relacionada a: " + funcionario.getNome());
                    e.printStackTrace();
                } catch(FolhaDePagamentoException e){
                    System.err.println("Erro na folha de pagamento para: " + funcionario.getNome());
                    System.err.println("Motivo: " + e.getMessage());
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Boolean verificaID(Integer id, Connection conn) {
        String sql = "SELECT 1 FROM folha_de_pagamento WHERE fk_id_funcionario = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.err.println("Erro na validação de CPF(s)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean verificaCPF(String cpf, Connection conn) {
        return null;
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
