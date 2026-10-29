package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import estructuraDatosAux.Par;
import modelo.grafo.Arista;
import modelo.grafo.Grafo;
import modelo.grafo.Vertice;

public class Instancia {
	private final Grafo grafo;

	public Instancia() {
		grafo = new Grafo();
	}

	public Set<Vertice> getVertices() {
		return grafo.obtenerVertices();
	}

	public Set<Vertice> vecinosDe(Vertice v) {
		return grafo.obtenerVecinos(v);
	}

	public int grado(Vertice v) {
		return grafo.grado(v);
	}

	public int cantidadVertices() {
		return grafo.cantidadVertices();
	}

	public void agregarVertice(String nombre) {
		grafo.agregarVertice(new Vertice(nombre));
	}

	public void agregarArista(String a, String b) {
		grafo.agregarArista(grafo.getVertice(a), grafo.getVertice(b));
	}

	public void eliminarVertice(Vertice vertice) {
		grafo.eliminarVertice(vertice);
	}

	public List<Arista> obtenerAristasDe(Vertice v) {
		if (!grafo.getMapaDeListaDeVecinos().containsKey(v)) {
			throw new IllegalArgumentException("El vértice no pertenece al grafo");
		}
		return new ArrayList<>(grafo.getMapaDeListaDeVecinos().get(v));
	}

	public Vertice getVertice(String nombre) {
		return grafo.getVertice(nombre);
	}

	public void eliminarArista(Vertice origen, Vertice destino) {
		grafo.eliminarArista(origen, destino);
	}

	public List<Par<String, String>> getAristasComoNombres() {
		List<Par<String, String>> aristas = new ArrayList<>();

		for (Vertice v : getVertices()) {
			for (Vertice vecino : vecinosDe(v)) {

				if (v.getNombre().compareTo(vecino.getNombre()) < 0) {
					aristas.add(new Par<>(v.getNombre(), vecino.getNombre()));
				}
			}
		}

		return aristas;
	}

	public Grafo getGrafo() {
		return this.grafo;
	}
}
