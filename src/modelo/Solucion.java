package modelo;

import java.util.HashSet;
import java.util.Set;

import modelo.grafo.Vertice;

public class Solucion {
	private Instancia instancia;
	private Set<Vertice> verticesConjuntoMinimo;
	private Set<Vertice> dominados;

	public Solucion(Instancia instancia) {
		this.instancia = instancia;
		this.verticesConjuntoMinimo = new HashSet<>();
		this.dominados = new HashSet<>();
	}

	public void agregar(Vertice v) {
		verticesConjuntoMinimo.add(v);
		dominados.add(v);
		dominados.addAll(instancia.vecinosDe(v));
	}

	public boolean contiene(Vertice v) {
		return verticesConjuntoMinimo.contains(v);
	}

	public boolean estaDominado(Vertice v) {
		return dominados.contains(v);
	}

	public boolean dominioIgualGrafo() {
		return dominados.size() == instancia.cantidadVertices();
	}

	public int nuevosCubiertos(Vertice v) {
		int cantidad = 0;

		if (!estaDominado(v)) {
			cantidad = 1;
		}
		for (Vertice vecino : instancia.vecinosDe(v)) {
			if (!estaDominado(vecino)) {
				cantidad++;
			}
		}
		return cantidad;
	}

	public int cardinal() {
		return verticesConjuntoMinimo.size();
	}

	public Set<Vertice> getVertices() {
		return new HashSet<>(verticesConjuntoMinimo);
	}

	@Override
	public String toString() {
		return "Solucion " + verticesConjuntoMinimo;
	}
}
