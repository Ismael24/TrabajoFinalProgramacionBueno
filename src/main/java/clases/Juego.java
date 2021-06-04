package clases;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

import enumeraciones.Genero;
import excepciones.NombreVacioException;
/**
 * Clase que dedicada al objeto juego en la cual tenemos las variables más comunes de un videojuego.
 * @author Ismael Paloma Narváez
 */
public class Juego extends EntidadBasica{
	private String descripcion;
	private float precio;
	private String lenguaje;
	private Genero genero;
	private short duracion;
	private String fechaLanzamiento;
	/**
     * Constructor con todos los datos de juego.
     * @param nombre el nombre del juego.
 * @param imgPrincipal la tipica "caratula" del juego.
 * @param descripcion pequeña descripción del juego.
 * @param precio precio del juego
 * @param lenguaje lenguaje/s del juego
 * @param genero tipo de genero del juego
 * @param duracion durecion media del juego
 * @param fechaLanzamiento fecha en la que se estrenó el juego
     */
	public Juego(String nombre, BufferedImage imgPrincipal, String descripcion, float precio, String lenguaje, Genero genero, short duracion, String fechaLanzamiento) throws NombreVacioException {
		super(nombre, imgPrincipal);
		this.descripcion = descripcion;
		this.precio = precio;
		this.lenguaje = lenguaje;
		this.genero = genero;
		this.duracion = duracion;
		this.fechaLanzamiento = fechaLanzamiento;
	}
	/**
     * Constructor de juego pero solo recibe el nombre y la imagen.
     * @param nombre el nombre del juego.
 * @param imgPrincipal la tipica "caratula" del juego.
     */

	public Juego(String nombre, BufferedImage imgPrincipal) throws NombreVacioException {
		super(nombre, imgPrincipal);
	}


	/**
     * obtiene el nivel de satisfacción de paseo del tamagotchi, entre 0 y 100
     * @return nivel de satisfacción del tamagotchi
     */
	public String getDescripcion() {
		return descripcion;
	}
	/**
     * Establece la nueva descripcion del juego
     * @param descripcion nueva descripcion
     */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
     * obtiene el precio del juego
     * @return el precio del juego
     */
	public float getPrecio() {
		return precio;
	}
	/**
     * Establece el nuevo precio del juego
     * @param precio nuevo precio
     */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/**
     * obtiene el nlenguaje/s del juego
     * @return lenguaje/s del juego
     */
	public String getLenguaje() {
		return lenguaje;
	}
	/**
     * Establece nuevo lenguaje para el juego
     * @param lenguaje nuevo lenguaje
     */
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	/**
     * obtiene el tipo de genero del juego
     * @return el genero del juego
     */
	public Genero getGenero() {
		return genero;
	}
	/**
     * Establece el nuevo genero del juego
     * @param genero nuevo genero de tipo genero
     */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	/**
     * obtiene la duración media del juego
     * @return la duración del juego
     */
	public short getDuracion() {
		return duracion;
	}
	/**
     * Establece la nueva duración del juego
     * @param duracion nueva duracion
     */
	public void setDuracion(short duracion) {
		this.duracion = duracion;
	}
	/**
     * obtiene la fecha de lanzamiento del juego
     * @return la fecha de lanzamiento del juego
     */
	public String getFechaLanzamiento() {
		return fechaLanzamiento;
	}
	/**
     * Establece la nueva fecha de lanzamiento del juego
     * @param fechaLanzamiento nueva fecha
     */
	public void setFechaLanzamiento(String fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	@Override
	public String toString() {
		return "Juego [descripcion=" + descripcion + ", precio=" + precio + ", vecesValorado=" 
				+ ", valoracion="  + ", lenguaje=" + lenguaje + ", genero=" + genero + ", duracion="
				+ duracion + ", fechaLanzamiento=" + fechaLanzamiento + "]";
	}
	
	
	
	
}
