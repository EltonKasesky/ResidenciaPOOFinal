package repository;

import java.sql.Connection;

public interface CPF {
    Boolean verificaCPF(String cpf, Connection conn);
    Integer pegarIDPeloCPF(String cpf, Connection conn);
}