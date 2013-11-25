create table persona (
	id int not null,
	nombre varchar(255) null,
	apellido varchar(255) null,
	edad int null,
	username varchar (255) not null,
	password varchar (255) not null
);

insert into persona values (1,'Nombre', 'Apellido', 30, 'username', 'pasword');