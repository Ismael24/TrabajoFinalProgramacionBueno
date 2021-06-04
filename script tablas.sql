SET SQL_SAFE_UPDATES=0;
UPDATE monedero set saldo = 0 where nombreUsuario= "Ismael";
SELECT*FROM juego;

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


