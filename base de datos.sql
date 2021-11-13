create schema reto_5;
use reto_5;

create table producto(
	idproducto 	int primary key not null auto_increment,
    nombre		varchar(45) not null,
    precio		decimal(8,2)
);

select * from producto;

insert into producto (nombre,precio) values ('tornillo el general',93277.00);
insert into producto (nombre,precio) values ('champagne',15728.00);
insert into producto (nombre,precio) values ('mora',63781.00);
insert into producto (nombre,precio) values ('cereal',33796.00);
insert into producto (nombre,precio) values ('garbanzo',86495.00);
insert into producto (nombre,precio) values ('vino espumoso',47392.00);
insert into producto (nombre,precio) values ('arroz',67370.00);
insert into producto (nombre,precio) values ('aguardiente',69188.00);
insert into producto (nombre,precio) values ('condimentos mixtos',3025.00);
insert into producto (nombre,precio) values ('cerezas dulces',96453.00);
insert into producto (nombre,precio) values ('aguardiente',46004.00);