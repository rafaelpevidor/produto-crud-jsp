CREATE TABLE fornecedor (
	id IDENTITY PRIMARY KEY,
	nome VARCHAR(255)  NOT NULL,
	descricao VARCHAR(600)
);

CREATE TABLE produto (
	id IDENTITY PRIMARY KEY,
	fornecedor_id INTEGER NOT NULL,
	nome VARCHAR(255) NOT NULL,
	cor VARCHAR(50) NOT NULL,
	referencia INTEGER,
	lote VARCHAR(20) NOT NULL,
	quantidade DOUBLE NOT NULL,
	vlr_compra DOUBLE NOT NULL,
	preco DOUBLE NOT NULL,
	descricao VARCHAR(800)
);

ALTER TABLE produto
ADD FOREIGN KEY (fornecedor_id)
REFERENCES fornecedor (id);