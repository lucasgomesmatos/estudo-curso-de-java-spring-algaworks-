CREATE TABLE forma_pagamento
(
    id        BIGINT      NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE grupo
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE grupo_permissao
(
    grupo_id     BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;


CREATE TABLE permissao
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    nome      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE produto
(
    ativo          BIT            NOT NULL,
    preco          DECIMAL(38, 2) NOT NULL,
    id             BIGINT         NOT NULL AUTO_INCREMENT,
    restaurante_id BIGINT         NOT NULL,
    descricao      TEXT           NOT NULL,
    nome           VARCHAR(255)   NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE restaurante
(
    taxa_frete           DECIMAL(38, 2) NOT NULL,
    cozinha_id           BIGINT         NOT NULL,
    data_atualizacao     DATETIME       NOT NULL,
    data_cadastro        DATETIME       NOT NULL,
    endereco_cidade_id   BIGINT,
    id                   BIGINT         NOT NULL AUTO_INCREMENT,
    endereco_bairro      VARCHAR(255),
    endereco_cep         VARCHAR(255),
    endereco_complemento VARCHAR(255),
    endereco_logradouro  VARCHAR(255),
    endereco_numero      VARCHAR(255),
    nome                 VARCHAR(255)   NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;


CREATE TABLE restaurante_forma_pagamento
(
    forma_pagamento_id BIGINT NOT NULL,
    restaurante_id     BIGINT NOT NULL
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE usuario
(
    data_cadastro DATETIME     NOT NULL,
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    email         VARCHAR(255) NOT NULL,
    nome          VARCHAR(255) NOT NULL,
    senha         VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

CREATE TABLE usuario_grupo
(
    grupo_id   BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

alter table usuario
    add constraint email_unique unique (email);
alter table grupo_permissao
    add constraint fk_grupo_permissao_premissao foreign key (permissao_id) references permissao (id);
alter table grupo_permissao
    add constraint fk_grupo_permissao foreign key (grupo_id) references grupo (id);
alter table produto
    add constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante (id);
alter table restaurante
    add constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id);
alter table restaurante
    add constraint fk_restaurante_endreco foreign key (endereco_cidade_id) references cidade (id);
alter table restaurante_forma_pagamento
    add constraint fk_restaurante_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);
alter table restaurante_forma_pagamento
    add constraint fk_restaurante_pagamento foreign key (restaurante_id) references restaurante (id);
alter table usuario_grupo
    add constraint fk_usuario_grupo foreign key (grupo_id) references grupo (id);
alter table usuario_grupo
    add constraint fk_usuario_id foreign key (usuario_id) references usuario (id);
