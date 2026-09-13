create database sauap;
use sauap;

create table profesor(id_profesor int auto_increment primary key, nombre varchar(50) not null, apellido_pat varchar (50) not null, apellido_mat varchar(50) not null, rfc varchar(13) not null unique);
create table unidad_de_aprendizaje(id_ua int auto_increment primary key, nombre_ua varchar(50) not null, horas_clase int check(horas_clase between 0 and 4), horas_taller int check(horas_taller between 0 and 4), horas_lab int check(horas_lab between 0 and 4));
create table usuario(id_usuario int auto_increment primary key, nombre_usuario varchar(50), contrasena varchar(50));
create table profesor_tiene_unidad_de_aprendizaje(id_asignacion int auto_increment primary key, id_profesor int not null , id_ua int not null, foreign key(id_profesor) references profesor(id_profesor), foreign key(id_ua) references unidad_de_aprendizaje(id_ua));
