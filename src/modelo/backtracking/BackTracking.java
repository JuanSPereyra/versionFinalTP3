package modelo.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import modelo.grafo.Grafo;
import modelo.grafo.Vertice;

public class BackTracking {
	private Grafo grafo;
	private List<Vertice> vertices;
	private Set<Vertice> mejorSolucion;
	private volatile boolean cancelado;

	public BackTracking() {
		this.cancelado = false;
	}

	public Set<Vertice> resolverGrafo(Grafo grafo) {
		this.grafo = grafo;
		this.vertices = new ArrayList<>(grafo.obtenerVertices());
		this.mejorSolucion = null;

		buscar(new HashSet<>());
		return mejorSolucion;
	}

	public void cancelar() {
		cancelado = true;
	}

	private void buscar(Set<Vertice> solucionParcial) {
		if (cancelado)
			return;

		if (mejorSolucion != null && solucionParcial.size() >= mejorSolucion.size()) {
			return;
		}

		Vertice sinDominar = verticeNoDominado(solucionParcial);

		if (sinDominar == null) {
			mejorSolucion = new HashSet<>(solucionParcial);
			return;
		}

		List<Vertice> candidatos = new ArrayList<>(grafo.obtenerVecinos(sinDominar));
		candidatos.add(sinDominar);

		for (Vertice v : candidatos) {
			solucionParcial.add(v);
			buscar(solucionParcial);
			solucionParcial.remove(v);
		}
	}

	private Vertice verticeNoDominado(Set<Vertice> actual) {
		for (Vertice v : vertices) {
			if (!verticeEstaDominado(v, actual)) {
				return v;
			}
		}
		return null;
	}

	private boolean verticeEstaDominado(Vertice vertice, Set<Vertice> actual) {
		if (actual.contains(vertice))
			return true;

		for (Vertice v : grafo.obtenerVecinos(vertice)) {
			if (actual.contains(v)) {
				return true;
			}
		}
		return false;
	}
}
