create table users
(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled  boolean not null
);
create table authorities
(
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create
unique index ix_auth_username on authorities (username,authority);

create table `tecnologias`(
                              id int auto_increment,
                              nombre varchar(100),
                              version_tec varchar(10),
                              constraint pk_tecnologias primary key (id)
);

create table `candidatos` (
                              id int auto_increment,
                              nombre varchar(50),
                              apellido varchar(50),
                              tipo_doc varchar(3),
                              dni varchar(50) not null unique,
                              nacimiento date,
                              constraint pk_candidatos primary key (id)
);

create table `experiencia`(
                              id int auto_increment,
                              id_tec int not null,
                              id_candidato int not null,
                              experiencia int,
                              constraint pk_can_tec primary key (id),
                              constraint fk_tecnologia_candidato foreign key (id_tec) references tecnologias (id),
                              constraint fk_candidato_tecnologia foreign key (id_candidato) references candidatos (id)
);
