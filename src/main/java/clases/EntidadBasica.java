package clases;

import java.awt.image.BufferedImage;
import java.io.File;

import excepciones.NombreVacioException;

/**
 * Clase abstracta que representa a un elemento que puede tener nombre e imagen
 * @author Ismael Paloma Narváez
 */

public abstract class EntidadBasica {
	private String nombre;//nombre de la cosa
	BufferedImage imgPrincipal;//imagen de la cosa
	 /**
     * Constructor con todos los datos del elemento.
     * @param nombre nombre que se le añadirá a la cosa.
 * @param imgPrincipal la imagen que se le añadirá a la cosa.
     */
	public EntidadBasica(String nombre, BufferedImage imgPrincipal) throws NombreVacioException{
		super();
		this.setNombre(nombre);
		this.imgPrincipal = imgPrincipal;
	}
	/**
     * obtiene el nombre de la cosa
     * @return el nombre de la cosa
     */
	public String getNombre() {
		return nombre;
	}

	/**
     * establece el nombre de la cosa y dicho campo requerido no puede estar en blanco/vacío
     * @param nombre nuevo nombre de la cosa
     */
	public void setNombre(String nombre) throws NombreVacioException {
		if(nombre.isBlank()) {
			throw new NombreVacioException("El nombre no puede estar vacío");
		}
		this.nombre = nombre;
	}
	/**
     * obtiene la imagen de la cosa
     * @return la imagen de la cosa
     */
	public BufferedImage getImgPrincipal() {
		return imgPrincipal;
	}
	/**
     * establece la imagen de la cosa
     * @param imgPrincipal nueva imagen de la cosa
     */
	public void setImgPrincipal(BufferedImage imgPrincipal) {
		this.imgPrincipal = imgPrincipal;
	}

	@Override
	public String toString() {
		return "EntidadBasica [nombre=" + nombre + ", imgPrincipal=" + imgPrincipal + "]";
	}

	
	


	
	
	
	
}
