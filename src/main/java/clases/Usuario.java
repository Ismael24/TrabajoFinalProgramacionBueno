package clases;

import java.awt.image.BufferedImage;
import java.util.HashSet;

import javax.swing.JOptionPane;

import enumeraciones.Estado;
import excepciones.PasswordMuyCortaException;
import excepciones.AliasVacioException;
import excepciones.CorreoVacioException;
import excepciones.NombreVacioException;
import excepciones.PasswordMuyCortaException;

public class Usuario extends EntidadBasica {
	private String alias;
	private String password;
	private byte nivel;
	private String correo;
	private Estado estado;
	private HashSet<Juego> deseados;
	private HashSet<Juego> biblioteca;
	private Monedero monedero;
	
	public Usuario(String nombre, BufferedImage imgPrincipal, String alias, String password, byte nivel, String correo,
			Estado estado, HashSet<Juego> deseados, HashSet<Juego> biblioteca, Monedero monedero) throws PasswordMuyCortaException, NombreVacioException, AliasVacioException,CorreoVacioException {
		super(nombre, imgPrincipal);
		this.setAlias(alias);
		this.setPassword(password);
		this.nivel = nivel;
		this.setCorreo(correo);
		this.estado = estado;
		this.deseados = deseados;
		this.biblioteca = biblioteca;
		this.monedero = monedero;
	}
	

	


	public Usuario(String nombre, BufferedImage imgPrincipal, String alias, String password, byte nivel, String correo,
			Estado estado) throws NombreVacioException, PasswordMuyCortaException,AliasVacioException,CorreoVacioException {
		super(nombre, imgPrincipal);
		this.setAlias(alias);
		this.password = password;
		this.nivel = nivel;
		this.setCorreo(correo);
		this.estado = estado;
	}





	public Usuario(String nombre, BufferedImage imgPrincipal, String alias, String password, byte nivel, String correo,
			Estado estado, Monedero monedero) throws PasswordMuyCortaException, NombreVacioException,AliasVacioException,CorreoVacioException{
		super(nombre, imgPrincipal);
		this.setAlias(alias);
		this.setPassword(password);
		this.nivel = nivel;
		this.setCorreo(correo);
		this.estado = estado;
		this.monedero = monedero;
	}
	

	





	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) throws AliasVacioException {
		if(alias.isBlank()) {
			throw new AliasVacioException("El alias no puede estar vacío");
		}
		this.alias = alias;
	}

	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) throws PasswordMuyCortaException {
		if(password.length()<8) {
			throw new PasswordMuyCortaException(""
					+ "La contrase�a es muy corta, debe tener"
					+ " al menos 8 caracteres");
		}
		this.password = password;
	}

	public byte getNivel() {
		return nivel;
	}

	public void setNivel(byte nivel) {
		this.nivel = nivel;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) throws CorreoVacioException {
		if(correo.isBlank()) {
			throw new CorreoVacioException("El correo no puede estar vacío");
		}
		this.correo = correo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public HashSet<Juego> getDeseados() {
		return deseados;
	}

	public void setDeseados(HashSet<Juego> deseados) {
		this.deseados = deseados;
	}
	

	public HashSet<Juego> getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(HashSet<Juego> biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Monedero getMonedero() {
		return monedero;
	}

	public void setMonedero(Monedero monedero) {
		
		this.monedero = monedero;
	}
	
	
	
	public void  comprarJuegos(float precioJuego, Juego juego){
		if(monedero.getSaldo()>=precioJuego) {
			monedero.setSaldo((float)(monedero.getSaldo()-precioJuego));
			biblioteca.add(juego);
			 
		}
		
	}
	


	@Override
	public String toString() {
		return "Usuario [alias=" + alias + ", password=" + password + ", nivel=" + nivel + ", correo=" + correo
				+ ", estado=" + estado + ", deseados=" + deseados + ", biblioteca=" + biblioteca + ", monedero="
				+ monedero + "]";
	}
	
	

	
}
