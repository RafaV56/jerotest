/*Insertarmos unos Jugadores de prueba*/
INSERT INTO jugadores(id,nombre,peso,talla,dia,mes,anno,hora,minuto)VALUES (1,'jero',60,160,4,10,2004,2,0);
INSERT INTO jugadores(id,nombre,peso,talla,dia,mes,anno,hora,minuto)VALUES (2,'rafa',60,180,2,10,1984,16,0);
INSERT INTO jugadores(id,nombre,peso,talla,dia,mes,anno,hora,minuto)VALUES (3,'juanis',40,140,20,11,2001,16,0);
INSERT INTO jugadores(id,nombre,peso,talla,dia,mes,anno,hora,minuto)VALUES (4,'paula',50,150,13,6,1979,16,0);

/*Usuarios iniciales*/
INSERT INTO usuarios(id,alias,nombre, apellido,password,activo)VALUES (1,'admin','rafael','velásquez','$2a$10$JS7iOrTDGDBOZzdZ2/cHrOgQQkQRgO8AOwNQz2NpEa6FrOW3zUsDq',1);
INSERT INTO usuarios(id,alias,nombre, apellido,password,activo)VALUES (3,'admin2','rafael','velásquez','$2a$10$JS7iOrTDGDBOZzdZ2/cHrOgQQkQRgO8AOwNQz2NpEa6FrOW3zUsDq',1);
INSERT INTO usuarios(id,alias,nombre, apellido,password,activo)VALUES (2,'usuario','usuario','registrado','$2a$10$1u8p66xrrfOl.i7oyBoHnuwrEK8gyItxXwrZ0p5n7bMXGSUROxu3G',1);

/*Insertamos roles*/
INSERT INTO roles(usuario_id,nombre)VALUES (1,'ROLE_ADMIN');
INSERT INTO roles(usuario_id,nombre)VALUES (3,'ROLE_ADMIN');
INSERT INTO roles(usuario_id,nombre)VALUES (1,'ROLE_USER');
INSERT INTO roles(usuario_id,nombre)VALUES (3,'ROLE_USER');
INSERT INTO roles(usuario_id,nombre)VALUES (2,'ROLE_USER');