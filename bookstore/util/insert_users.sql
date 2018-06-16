delete from user_role;
delete from role;
delete from user;

insert into role values ('ROLE_ADMIN');

insert into user (email, name, passwd) values ('admin@casadocodigo.com.br', 'Administrador', '$2a$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq');
insert into user (email, name, passwd) values ('sylvio.pedroza@gmail.com', 'Sylvio', '$2a$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq');

insert into user_role(user_email, roles_name) values ('admin@casadocodigo.com.br', 'ROLE_ADMIN');
insert into user_role(user_email, roles_name) values ('sylvio.pedroza@gmail.com', 'ROLE_ADMIN');