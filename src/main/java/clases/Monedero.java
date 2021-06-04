package clases;
/**
 * Clase que representa el monedero del usuario, en el que añadimos y restamos cantidades de dinero
 * @author Ismael Paloma Narváez
 */
public class Monedero {
	private float saldo;//dinero 
	private short puntos;//sin uso de momento por falta de tiempo
	
	 /**
     *  Constructor de monedero con todos los campos
     * @param saldo el saldo que hay en el monedero
     * @param puntos los puntos acumulados por la compra de juegos
     */
	public Monedero(float saldo, short puntos) {
		super();
		this.saldo = saldo;
		this.puntos = puntos;
	}
	 /**
     *  Constructor de monedero sin campos
     */
	public Monedero() {
		super();
	}
	 /**
     * obtiene el saldo del monedero
     * @return el saldo del monedero
     */
	public float getSaldo() {
		return saldo;
	}
	/**
     * establece el saldo del monedero
     * @param saldo nuevo saldo
     */
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	 /**
     * obtiene los puntos acumulados en el monedero
     * @return los puntos del monedero
     */
	public short getPuntos() {
		return puntos;
	}
	/**
     * establece los puntos del monedero
     * @param puntos nuevos puntos
     */
	public void setPuntos(short puntos) {
		this.puntos = puntos;
	}
	
	@Override
	public String toString() {
		return "Monedero [saldo=" + saldo + ", puntos=" + puntos + "]";
	}
	
	
	
}
