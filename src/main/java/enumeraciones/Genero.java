package enumeraciones;
/**
 * Enum que representa los Generos posibles para un juego
 * @author Ismael Paloma Narv�ez
 */
public enum Genero {
	ACCI�N,
	AVENTURA,
	DEPORTES,
	ESTRATEGIA,
	INDIE,
	ROL,
	SIMULADORES,
	ARCADE,
	DISPAROS,
	SUPERVIVENCIA,
	COOPERATIVO,
	TERROR,
	LUCHA,
	RPG;
	//atajo para que todo se recoja bien de la base de datos
	public static Genero fromString(String s) {
		switch(s) {
		case "ACCI�N":
			return ACCI�N;
		case "AVENTURA":
			return AVENTURA;
		case "DEPORTES":
			return DEPORTES;
		case "ESTRATEGIA":
			return ESTRATEGIA;
		case "INDIE":
			return INDIE;
		case "ROL":
			return ROL;
		case "SIMULADORES":
			return SIMULADORES;
		case "ARCADE":
			return ARCADE;
		case "DISPAROS":
			return DISPAROS;
		case "SUPERVIVENCIA":
			return SUPERVIVENCIA;
		case "COOPERATIVO":
			return COOPERATIVO;
		case "TERROR":
			return TERROR;
		case "LUCHA":
			return LUCHA;
		case "RPG":
			return RPG;
		
		}
		return null;
	
}
}
