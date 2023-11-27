CREATE TABLE imovel (
    id INTEGER PRIMARY KEY,
    numero_matricula INTEGER UNIQUE,
    livro INTEGER,
    folha TEXT,
    protocolo INTEGER UNIQUE,
    abertura DATE,
    tipo_imovel TEXT,
    localizacao TEXT,
    cns TEXT,
    mat TEXT,
    tipo_logradouro TEXT,
    titulo_logradouro TEXT,
    endereco INTEGER UNIQUE,
    cin TEXT,
    latitude TEXT,
    longitude TEXT,
    area_total TEXT,
    area_construida TEXT,
    unidade TEXT,
    iptu TEXT,
    descricao TEXT,
    FOREIGN KEY(endereco) REFERENCES endereco (id)
);

CREATE TABLE proprietario (
    id INTEGER PRIMARY KEY,
    registro INTEGER UNIQUE,
    livro INTEGER,
    folha TEXT,
    nome TEXT,
    nacionalidade TEXT,
    estado_civil TEXT,
    profissao TEXT,
    cpf TEXT UNIQUE,
    rg TEXT UNIQUE,
    informacao_adicional TEXT,
    endereco INTEGER UNIQUE,
    FOREIGN KEY(endereco) REFERENCES endereco (id)
);

CREATE TABLE imovel_proprietario (
    imovel_id INTEGER,
    proprietario_id INTEGER,
    FOREIGN KEY (imovel_id) REFERENCES imovel (id) ON DELETE CASCADE,
    FOREIGN KEY (proprietario_id) REFERENCES proprietario (id) ON DELETE CASCADE
);

CREATE SEQUENCE imovelidsequence START 1;
CREATE SEQUENCE proprietarioidsequence START 1;
