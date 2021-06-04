package excepciones;
/**
 * Excepcion para evitar que el correo este vacío
 * @author Ismael Paloma Narváez
 */
public class CorreoVacioException extends Exception{
			public CorreoVacioException(String e1) {
				super(e1);
				
			}
		}