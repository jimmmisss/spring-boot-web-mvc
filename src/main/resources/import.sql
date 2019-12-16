insert into categoria(id, name) values (1, 'TECNOLOGIA')
insert into categoria(id, name) values (2, 'ARQUITETURA DE SOLUÇÕES')
insert into categoria(id, name) values (3, 'GERENCIAMENTO DE PROJETOS')
insert into categoria(id, name) values (4, 'ENGENHARIA DE SOFTWARE')
insert into categoria(id, name) values (5, 'DATA CIENCE')

insert into produto(id, logo, nome, quantidade, valor) values (1, 'legalizzr.png', 'Camiseta - Darth Vader', 1, 80)
insert into produto(id, logo, nome, quantidade, valor) values (2, 'legalizzr.png', 'Macbook Pro', 1, 12000)
insert into produto(id, logo, nome, quantidade, valor) values (3, 'legalizzr.png', 'Teclado Mecânico', 1, 450)
insert into produto(id, logo, nome, quantidade, valor) values (4, 'legalizzr.png', 'iMac Tela de Retina', 1, 22000)
insert into produto(id, logo, nome, quantidade, valor) values (5, 'legalizzr.png', 'IPhone 11 PRO', 1, 6000)

insert into produto_categoria(id_categoria, id_produto) values (1, 1)
insert into produto_categoria(id_categoria, id_produto) values (1, 2)

insert into cliente(id, idade, nome, profissao, email, senha, sobrenome, telefone) values (1, 37, 'Wesley', 'Programador Java', 'wesley@legalizzr.com.br', '123', 'Pereira', '98999437427')
insert into cliente(id, idade, nome, profissao, email, senha, sobrenome, telefone) values (2, 20, 'Isadora', 'CEO', 'isadora@legalizzr.com.br', '123', 'Pereira', '98999437427')
insert into cliente(id, idade, nome, profissao, email, senha, sobrenome, telefone) values (3, 27, 'Fadia', 'Personal Treinee', 'fadia@legalizzr.com.br', '123', 'Pereira', '98999437427')
insert into cliente(id, idade, nome, profissao, email, senha, sobrenome, telefone) values (4, 49, 'Cleisa', 'Enfermeira', 'cleisa@legalizzr.com.br', '123', 'Pereira', '98999437427')
insert into cliente(id, idade, nome, profissao, email, senha, sobrenome, telefone) values (5, 37, 'Conceição', 'Bancária', 'conceicao@legalizzr.com.br', '123', 'Pereira', '98999437427')

insert into role(id, nome) values (1, 'ROLE_ADMIN');
insert into role(id, nome) values (2, 'ROLE_USER');

insert into usuario_role(id_usuario, id_role) values (1, 1)
insert into usuario_role(id_usuario, id_role) values (1, 2)
insert into usuario_role(id_usuario, id_role) values (2, 2)
insert into usuario_role(id_usuario, id_role) values (3, 2)
insert into usuario_role(id_usuario, id_role) values (4, 2)
insert into usuario_role(id_usuario, id_role) values (5, 2)
