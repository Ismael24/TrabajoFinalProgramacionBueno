package clases;

import java.util.HashMap;
/**
 * Clase que representa la tienda del programa
 * @author Ismael Paloma Narváez
 */
public class Tienda {
	private HashMap<String,Juego> todoJuegos;//HashMap en el que guardo todos los juegos de la tienda
	/**
     * Constructor con todos los datos de la tienda.
     * @param todoJuegos todos los juegos añadidos a la tienda vias base de datos
     */
	public Tienda(HashMap<String, Juego> todoJuegos) {
		super();
		this.todoJuegos = todoJuegos;
	}
	/**
     * obtiene el hashmap con todos los juegos cargados
     * @return los juegos cargados
     */
	public HashMap<String, Juego> getTodoJuegos() {
		return todoJuegos;
	}
	/**
     * Establece los juegos con string identificativo
     * @param todosJuegos nuevas inserciones 
     */
	public void setTodoJuegos(HashMap<String, Juego> todoJuegos) {
		this.todoJuegos = todoJuegos;
	}

	@Override
	public String toString() {
		return "Tienda [todoJuegos=" + todoJuegos + "]";
	}
	
	
}
