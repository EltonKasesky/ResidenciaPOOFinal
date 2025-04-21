CREATE TABLE funcionario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
	salario_bruto NUMERIC(10, 2) NOT NULL
);

CREATE TABLE dependente (
    id SERIAL PRIMARY KEY,
    fk_id_funcionario INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    parentesco VARCHAR(20) NOT NULL,
    CONSTRAINT fk_funcionario FOREIGN KEY (fk_id_funcionario)
    REFERENCES funcionario(id) ON DELETE CASCADE
);

CREATE TABLE folha_de_pagamento (
	id SERIAL PRIMARY KEY,
	fk_id_funcionario INT NOT NULL,
	salario_bruto NUMERIC(10, 2) NOT NULL,
	desconto_inss NUMERIC(10, 2) NOT NULL,
	desconto_ir NUMERIC(10, 2) NOT NULL,
    salario_liquido NUMERIC(10, 2) NOT NULL,
	CONSTRAINT fk_funcionario FOREIGN KEY (fk_id_funcionario)
	REFERENCES funcionario(id) ON DELETE CASCADE
);

SELECT * FROM funcionario;
SELECT * FROM dependente;
SELECT * FROM folha_de_pagamento;

TRUNCATE TABLE funcionario, dependente, folha_de_pagamento;