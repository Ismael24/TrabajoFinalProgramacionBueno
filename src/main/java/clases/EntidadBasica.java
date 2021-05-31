package clases;

import java.awt.image.BufferedImage;
import java.io.File;

import excepciones.NombreVacioException;



public class EntidadBasica {
	private String nombre;
	BufferedImage imgPrincipal;
	
	public EntidadBasica(String nombre, BufferedImage imgPrincipal) throws NombreVacioException{
		super();
		this.setNombre(nombre);
		this.imgPrincipal = imgPrincipal;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) throws NombreVacioException {
		if(nombre.isBlank()) {
			throw new NombreVacioException("El nombre no puede estar vacío");
		}
		this.nombre = nombre;
	}

	public BufferedImage getImgPrincipal() {
		return imgPrincipal;
	}

	public void setImgPrincipal(BufferedImage imgPrincipal) {
		this.imgPrincipal = imgPrincipal;
	}

	@Override
	public String toString() {
		return "EntidadBasica [nombre=" + nombre + ", imgPrincipal=" + imgPrincipal + "]";
	}

	
	


	
	
	
	
}
