package clases;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

import enumeraciones.Genero;
import excepciones.NombreVacioException;

public class Juego extends EntidadBasica{
	private String descripcion;
	private float precio;
	private int vecesValorado;
	private float valoracion;
	private String lenguaje;
	private Genero genero;
	private short duracion;
	private LocalDate fechaLanzamiento;
	
	public Juego(String nombre, BufferedImage imgPrincipal, String descripcion, float precio, int vecesValorado,
			float valoracion, String lenguaje, Genero genero, short duracion, LocalDate fechaLanzamiento) throws NombreVacioException {
		super(nombre, imgPrincipal);
		this.descripcion = descripcion;
		this.precio = precio;
		this.vecesValorado = vecesValorado;
		this.valoracion = valoracion;
		this.lenguaje = lenguaje;
		this.genero = genero;
		this.duracion = duracion;
		this.fechaLanzamiento = fechaLanzamiento;
	}


	public Juego(String nombre, BufferedImage imgPrincipal, String descripcion, float precio, int vecesValorado,
			float valoracion, String lenguaje, Genero genero, short duracion) throws NombreVacioException {
		super(nombre, imgPrincipal);
		this.descripcion = descripcion;
		this.precio = precio;
		this.vecesValorado = vecesValorado;
		this.valoracion = valoracion;
		this.lenguaje = lenguaje;
		this.genero = genero;
		this.duracion = duracion;
	}











	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getVecesValorado() {
		return vecesValorado;
	}

	public void setVecesValorado(int vecesValorado) {
		this.vecesValorado = vecesValorado;
	}

	public float getValoracion() {
		return valoracion;
	}

	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public short getDuracion() {
		return duracion;
	}

	public void setDuracion(short duracion) {
		this.duracion = duracion;
	}

	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	@Override
	public String toString() {
		return "Juego [descripcion=" + descripcion + ", precio=" + precio + ", vecesValorado=" + vecesValorado
				+ ", valoracion=" + valoracion + ", lenguaje=" + lenguaje + ", genero=" + genero + ", duracion="
				+ duracion + ", fechaLanzamiento=" + fechaLanzamiento + "]";
	}
	
	
	
	
}
