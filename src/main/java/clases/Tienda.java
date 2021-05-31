package clases;

import java.util.HashMap;

public class Tienda {
	private HashMap<String,Juego> todoJuegos;

	public Tienda(HashMap<String, Juego> todoJuegos) {
		super();
		this.todoJuegos = todoJuegos;
	}

	public HashMap<String, Juego> getTodoJuegos() {
		return todoJuegos;
	}

	public void setTodoJuegos(HashMap<String, Juego> todoJuegos) {
		this.todoJuegos = todoJuegos;
	}

	@Override
	public String toString() {
		return "Tienda [todoJuegos=" + todoJuegos + "]";
	}
	
	
}
