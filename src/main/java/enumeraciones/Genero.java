package enumeraciones;

public enum Genero {
	ACCIÓN,
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
	
	public static Genero fromString(String s) {
		switch(s) {
		case "ACCIÓN":
			return ACCIÓN;
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
