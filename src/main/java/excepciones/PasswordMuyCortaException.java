package excepciones;
/**
 * Excepcion para evitar que la contrase�a este vac�a y que tenga m�s de 8 caracteres
 * @author Ismael Paloma Narv�ez
 */
public class PasswordMuyCortaException extends Exception{
		public PasswordMuyCortaException(String e1) {
			super(e1);
			
		}
	}

