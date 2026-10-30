package modelo.grafo;

public class Arista {

	private Vertice verticeUno;
	private Vertice verticeDos;

	public Arista(Vertice verticeUno, Vertice verticeDos) {
		this.verticeUno = verticeUno;
		this.verticeDos = verticeDos;
	}

	public Vertice getVerticeUno() {
		return verticeUno;
	}

	public Vertice getVerticeDos() {
		return verticeDos;
	}

	public Vertice obtenerOpuesto(Vertice v) {
		if (getVerticeUno().equals(v))
			return getVerticeDos();
		if (getVerticeDos().equals(v))
			return getVerticeUno();
		throw new IllegalArgumentException("El vertice no pertenece a esta arista.");
	}

	@Override
	public String toString() {
		return verticeUno + " -- " + verticeDos;
	}
}
