package estructuraDatosAux;

public class VerticeVisual {

	private String nombre;
	private TipoVertice tipo;

	public VerticeVisual(String nombre, TipoVertice tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean esDominante() {
		return this.tipo == TipoVertice.DOMINANTE;
	}
}
