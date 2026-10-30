package estructuraDatosAux;

public class Par<A, B> {
	private A objetoUno;
	private B objetoDos;

	public Par(A objetoUno, B objetoDos) {
		this.objetoUno = objetoUno;
		this.objetoDos = objetoDos;
	}

	public A getObjetoUno() {
		return objetoUno;
	}

	public B getObjetoDos() {
		return objetoDos;
	}
}
