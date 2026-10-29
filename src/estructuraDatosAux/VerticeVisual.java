package estructuraDatosAux;

public class VerticeVisual {
	private String nombre;
	private boolean esDominante;

	public VerticeVisual(String nombre, boolean esDominante) {
		this.nombre = nombre;
		this.esDominante = esDominante;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean esDominante() {
		return this.esDominante;
	}
}
