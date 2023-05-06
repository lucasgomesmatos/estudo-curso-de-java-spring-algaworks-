insert into cozinha (id, nome) values (1 , 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Brasil');
insert into cozinha (id, nome) values (4, 'Japonesa');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);


insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.5, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (1, 'Cozinha Mineira', 9.5, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Cozinha Goiana', 0, 3, utc_timestamp, utc_timestamp);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (forma_pagamento_id, restaurante_id) values (1,1), (1,2), (1,3),(2,1),(2,2), (2,3),(3,1),  (3,2), (3,3);

UPDATE `algafood`.`restaurante` SET `endereco_cidade_id` = '1', `endereco_bairro` = 'centro', `endereco_cep` = '39817-000', `endereco_complemento` = 'casa', `endereco_logradouro` = 'Rua São Paulo', `endereco_numero` = '589' WHERE (`id` = '1');
