SET SQL_SAFE_UPDATES=0;
CREATE DATABASE `dropgames` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


CREATE TABLE `juego` (
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `lenguaje` varchar(60) DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  `fechaLanzamiento` datetime DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `monedero` (
  `saldo` float DEFAULT NULL,
  `puntos` int DEFAULT NULL,
  `nombreUsuario` varchar(10) DEFAULT NULL,
  KEY `nombreUsuario` (`nombreUsuario`),
  CONSTRAINT `monedero_ibfk_1` FOREIGN KEY (`nombreUsuario`) REFERENCES `usuario` (`nombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario` (
  `nombreUsuario` varchar(10) NOT NULL,
  `aliasUsuario` varchar(10) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nivel` int DEFAULT NULL,
  `correoUsuario` varchar(40) DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`nombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabla de usuarios registrados en DropGames';


CREATE TABLE juegos_biblioteca(
usuario varchar(10),
juego varchar(20),
foreign key (usuario) references usuario (nombreUsuario),
foreign key (juego) references juego (nombre),
primary key (usuario,juego));

CREATE TABLE juegos_deseados(
usuario varchar(10),
juego varchar(20),
foreign key (usuario) references usuario (nombreUsuario),
foreign key (juego) references juego (nombre),
primary key (usuario,juego));

/*solo son necesarios los inserts de la tabla de juegos
, lo demás se va insertando con la ejecución del programa*/

# nombre, descripcion, precio, lenguaje, genero, duracion, fechaLanzamiento
INSERT INTO JUEGO VALUES("coldwar",	"Nueva entrega de call of duty ambientada en la guerra fría",	64.9	,"Español e Inglés",	"DISPAROS"	,5	,"2020/11/13");
INSERT INTO JUEGO VALUES("dayz"	,"Juego mundo abierto de zombies, pve y pvp",	23.99,	"Inglés"	,"SUPERVIVENCIA"	,100	,"2018/12/13");
INSERT INTO JUEGO VALUES("dayz",	"Juego mundo abierto de zombies, pve y pvp",	23.99	,"Inglés",	"SUPERVIVENCIA"	,100,	"2018/12/13");
INSERT INTO JUEGO VALUES("isaac",	"Consiste en pasar habitaciones y llegar a diferentes boses, es un juego infinito ya que cada run es diferente",	14.99,	"Inglés",	"INDIE",	100	,"2014/11/04");
INSERT INTO JUEGO VALUES("ittakestwo",	"La aventura cooperativa del año, explora un mundo lleno de sorpresas",	39.99,	"Inglés",	"COOPERATIVO",	50,	"2021/03/26");
INSERT INTO JUEGO VALUES("mortalkombat",	"Juego exageradamente sangriento y divertido para jugar online o local con amigos",	10.99,"	Español e Inglés",	"LUCHA",	8	,"2019/04/23");
INSERT INTO JUEGO VALUES("ori",	"Juego de plataformas moderno, en el que nos enfrentamos a boses y vamos adquiriendo habilidades",	29.99	,"Inglés",	"INDIE",	7	,"2020/03/11");
INSERT INTO JUEGO VALUES("phasmophobia",	"Juego de cazar fantasmas, cada partida es diferente por lo que es muy rejugable",	11.59,	"Inglés",	"TERROR"	,60,	"2020/09/18");
INSERT INTO JUEGO VALUES("sekiro",	"Juego de los creadores de los souls ambientado en samurais",	59.99,	"Inglés",	"ACCIÓN"	,50	,"2019/03/21");
INSERT INTO JUEGO VALUES("residentevil",	"Juego con una historia repilante que te llevará a investigar hasta el último rincon del extraño pueblo",	59.99	,	"TERROR",	9	,"2021/05/07");







