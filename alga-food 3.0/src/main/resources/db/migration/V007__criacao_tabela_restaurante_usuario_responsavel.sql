CREATE TABLE restaurante_usuario_responsavel
(
    restaurante_id BIGINT NOT NULL,
    usuario_id     BIGINT NOT NULL
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;

ALTER TABLE restaurante_usuario_responsavel
    ADD CONSTRAINT fk_restaurante_usuario_responsavel_restaurante FOREIGN KEY (usuario_id) REFERENCES usuario (id);
ALTER TABLE restaurante_usuario_responsavel
    ADD CONSTRAINT fk_restaurante_usuario_responsavel_usuario FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);