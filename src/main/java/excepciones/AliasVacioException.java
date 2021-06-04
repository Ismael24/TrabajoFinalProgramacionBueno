package excepciones;
/**
 * Excepcion para evitar que el alias este vacío
 * @author Ismael Paloma Narváez
 */
public class AliasVacioException extends Exception{
			public AliasVacioException(String e1) {
				super(e1);
				
			}
		}