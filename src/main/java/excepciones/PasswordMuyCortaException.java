package excepciones;
/**
 * Excepcion para evitar que la contraseña este vacía y que tenga más de 8 caracteres
 * @author Ismael Paloma Narváez
 */
public class PasswordMuyCortaException extends Exception{
		public PasswordMuyCortaException(String e1) {
			super(e1);
			
		}
	}

