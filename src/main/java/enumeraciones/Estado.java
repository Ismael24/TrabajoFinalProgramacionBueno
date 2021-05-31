package enumeraciones;

public enum Estado {
	CONECTADO,AUSENTE,INVISIBLE,DESCONECTADO;
	
	public static Estado fromString(String s) {
		switch(s) {
		case "CONECTADO":
			return CONECTADO;
		case "AUSENTE":
			return AUSENTE;
		case "INVISIBLE":
			return INVISIBLE;
		case "DESCONECTADO":
			return DESCONECTADO;
		
		}
		return null;
	
}
}


