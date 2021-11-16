INSERT INTO users (username, password, enabled)
values('user','password', true);
INSERT INTO users (username, password, enabled)
values('admin','password', true);
INSERT INTO authorities(username, authority)
values('user','ROLE_USER');
INSERT INTO authorities(username, authority)
values('admin','ROLE_ADMIN');


insert into candidatos (nombre,apellido,tipo,numero_documento) values('Martin','Gil','1','39098865');
insert into candidatos (nombre,apellido,tipo,numero_documento) values('Juan','Perez','1','12345678');
insert into candidatos (nombre,apellido,tipo,numero_documento) values('Sergio','Aguero','1','12345612');

insert into tecnologias (nombre,version) values ('Java',1.8);
insert into tecnologias (nombre,version) values ('Java',11);
insert into tecnologias (nombre,version) values ('Angular',5);

insert into experiencia (candidato,tecnologia,experiencia) values (1,1,3);
insert into experiencia (candidato,tecnologia,experiencia) values (1,2,3);
insert into experiencia (candidato,tecnologia,experiencia) values (2,1,2);
insert into experiencia (candidato,tecnologia,experiencia) values (2,2,1);
insert into experiencia (candidato,tecnologia,experiencia) values (3,3,4);
