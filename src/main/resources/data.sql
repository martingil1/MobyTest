INSERT INTO users (username, password, enabled)
values('user','password', true);
INSERT INTO users (username, password, enabled)
values('admin','password', true);
INSERT INTO authorities(username, authority)
values('user','ROLE_USER');
INSERT INTO authorities(username, authority)
values('admin','ROLE_ADMIN');


insert into candidatos (nombre,apellido,tipo,dni,nacimiento) values('Martin','Gil','dni','39098865','1995-08-22');
insert into candidatos (nombre,apellido,tipo,dni,nacimiento) values('Juan','Perez','dni','12345678','1978-04-06');
insert into candidatos (nombre,apellido,tipo,dni,nacimiento) values('Sergio','Aguero','dni','12345612','1998-03-08');

insert into tecnologias (nombre,version_tec) values ('Java',1.8);
insert into tecnologias (nombre,version_tec) values ('Java',11);
insert into tecnologias (nombre,version_tec) values ('Angular',5);

insert into experiencia (id_candidato,id_tec,experiencia) values (1,1,3);
insert into experiencia (id_candidato,id_tec,experiencia) values (1,2,3);
insert into experiencia (id_candidato,id_tec,experiencia) values (2,1,2);
insert into experiencia (id_candidato,id_tec,experiencia) values (2,2,1);
insert into experiencia (id_candidato,id_tec,experiencia) values (3,3,4);
