package excepciones;
/**
 * Excepcion para evitar que el correo este vac�o
 * @author Ismael Paloma Narv�ez
 */
public class CorreoVacioException extends Exception{
			public CorreoVacioException(String e1) {
				super(e1);
				
			}
		}