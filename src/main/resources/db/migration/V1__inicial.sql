CREATE TABLE usuario (
id INTEGER PRIMARY KEY,
login TEXT,
senha TEXT
);

CREATE TABLE endereco (
id INTEGER PRIMARY KEY,
estado TEXT,
cidade TEXT,
rua TEXT,
numero TEXT,
bairro TEXT,
complemento TEXT
);
CREATE TABLE pessoa (
id INTEGER PRIMARY KEY,
ficha SERIAL,
endereco INTEGER UNIQUE,
nome TEXT,
cpf TEXT,
rg TEXT,
data_nasc DATE,
data_emissao DATE,
estadocivil TEXT,
profissao TEXT,
pai TEXT,
mae TEXT,
FOREIGN KEY(endereco) REFERENCES endereco (id)
);

CREATE SEQUENCE hibernate_sequence START 1;
CREATE SEQUENCE usuarioidsequence START 1;
CREATE SEQUENCE pessoaidsequence START 1;
CREATE SEQUENCE enderecoidsequence START 1;

