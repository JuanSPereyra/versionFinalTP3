package estructuraDatosAux;

public class Par<A, B> {
	private A izquierda;
	private B derecha;

	public Par(A izquierda, B derecha) {
		this.izquierda = izquierda;
		this.derecha = derecha;
	}

	public A getIzquierda() {
		return izquierda;
	}

	public B getDerecha() {
		return derecha;
	}
}
