package clases;

public class Monedero {
	private float saldo;
	private short puntos;
	public Monedero(short saldo, short puntos) {
		super();
		this.saldo = saldo;
		this.puntos = puntos;
	}
	
	public Monedero() {
		super();
	}

	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public short getPuntos() {
		return puntos;
	}
	public void setPuntos(short puntos) {
		this.puntos = puntos;
	}
	
	@Override
	public String toString() {
		return "Monedero [saldo=" + saldo + ", puntos=" + puntos + "]";
	}
	
	
	
}
