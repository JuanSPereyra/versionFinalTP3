package modelo.grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {
	private Map<Vertice, List<Arista>> mapaDeListaDeVecinos;

	public Grafo() {
		this.mapaDeListaDeVecinos = new HashMap<>();
	}

	public Map<Vertice, List<Arista>> getMapaDeListaDeVecinos() {
		return mapaDeListaDeVecinos;
	}

	public void agregarVertice(Vertice v) {
		if (!mapaDeListaDeVecinos.containsKey(v))
			mapaDeListaDeVecinos.put(v, new ArrayList<>());
		else {
			throw new IllegalArgumentException("Vertice ya existente");
		}
	}

	public void agregarArista(Vertice origen, Vertice destino) {
		if (validarArista(origen, destino)) {
			Arista arista = new Arista(origen, destino);

			mapaDeListaDeVecinos.get(origen).add(arista);
			mapaDeListaDeVecinos.get(destino).add(arista);
		}
	}

	public int cantidadAristas() {
		return obtenerTodasLasAristas().size();
	}

	public boolean existeArista(Vertice a, Vertice b) {
		validarVerticesExistenEnGrafo(a, b);
		return buscarArista(a, b) != null;
	}

	private boolean validarArista(Vertice origen, Vertice destino) {
		if (!mapaDeListaDeVecinos.containsKey(origen) || !mapaDeListaDeVecinos.containsKey(destino))
			throw new IllegalArgumentException("Ambos vertices deben existir en el grafo antes de agregar la arista.");

		else if (origen.equals(destino))
			throw new IllegalArgumentException("Origen debe ser distino a destino.");

		else if (buscarArista(origen, destino) != null)
			throw new IllegalArgumentException("Ya existe una arista entre estos dos vertices.");

		else {
			return true;
		}
	}

	public boolean contieneVertice(Vertice v) {
		return mapaDeListaDeVecinos.containsKey(v);
	}

	public boolean estaAislado(Vertice v) {
		return grado(v) == 0;
	}

	public boolean esConjuntoDominante(Set<Vertice> conjunto) {
		for (Vertice v : mapaDeListaDeVecinos.keySet()) {
			if (!conjunto.contains(v) && !tieneVecinoEn(v, conjunto))
				return false;
		}
		return true;
	}

	private boolean tieneVecinoEn(Vertice v, Set<Vertice> conjunto) {
		for (Vertice vecino : obtenerVecinos(v)) {
			if (conjunto.contains(vecino))
				return true;
		}
		return false;
	}

	public void eliminarVertice(Vertice v) {
		validarVerticePertenceAGrafo(v);

		for (Arista arista : new ArrayList<>(mapaDeListaDeVecinos.get(v))) {
			mapaDeListaDeVecinos.get(arista.obtenerOpuesto(v)).remove(arista);
		}
		mapaDeListaDeVecinos.remove(v);
	}

	private void validarVerticePertenceAGrafo(Vertice v) {
		if (!mapaDeListaDeVecinos.containsKey(v))
			throw new IllegalArgumentException("El vertice no pertenece al grafo.");
	}

	public Vertice getVertice(String nombre) {
		for (Vertice v : mapaDeListaDeVecinos.keySet()) {
			if (v.getNombre().equals(nombre)) {
				return v;
			}
		}
		throw new IllegalArgumentException("No existe un vertice con el nombre: " + nombre);
	}

	public Set<Vertice> obtenerVertices() {
		return new HashSet<>(mapaDeListaDeVecinos.keySet());
	}

	public List<Arista> obtenerAristasDe(Vertice v) {
		if (!mapaDeListaDeVecinos.containsKey(v)) {
			throw new IllegalArgumentException("El vértice no pertenece al grafo");
		}
		return new ArrayList<>(mapaDeListaDeVecinos.get(v));
	}

	public List<Arista> obtenerTodasLasAristas() {
		Set<Arista> aristasUnicas = new HashSet<>();

		for (List<Arista> aristasDeUnVertice : mapaDeListaDeVecinos.values()) {
			aristasUnicas.addAll(aristasDeUnVertice);
		}
		return new ArrayList<>(aristasUnicas);
	}

	public int cantidadVertices() {
		return mapaDeListaDeVecinos.size();
	}

	public void eliminarArista(Vertice a, Vertice b) {
		validarVerticesExistenEnGrafo(a, b);
		Arista arista = buscarArista(a, b);

		validarAristaNoNull(arista);

		mapaDeListaDeVecinos.get(a).remove(arista);
		mapaDeListaDeVecinos.get(b).remove(arista);
	}

	private void validarVerticesExistenEnGrafo(Vertice a, Vertice b) {
		if (!mapaDeListaDeVecinos.containsKey(a) || !mapaDeListaDeVecinos.containsKey(b))
			throw new IllegalArgumentException("Ambos vértices deben existir en el grafo");
	}

	private void validarAristaNoNull(Arista arista) {
		if (arista == null)
			throw new IllegalArgumentException("No existe una arista entre estos dos vértices");
	}

	private Arista buscarArista(Vertice a, Vertice b) {
		for (Arista arista : mapaDeListaDeVecinos.get(a)) {
			if (arista.obtenerOpuesto(a).equals(b)) {
				return arista;
			}
		}
		return null;
	}

	public Set<Vertice> obtenerVecinos(Vertice v) {
		validarVerticePertenceAGrafo(v);
		Set<Vertice> vecinos = new HashSet<>();
		for (Arista a : mapaDeListaDeVecinos.get(v))
			vecinos.add(a.obtenerOpuesto(v));
		return vecinos;
	}

	public int grado(Vertice v) {
		validarVerticePertenceAGrafo(v);
		return mapaDeListaDeVecinos.get(v).size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Grafo:\n");

		sb.append("Vértices: ");
		sb.append(obtenerVertices());
		sb.append("\n");

		sb.append("Aristas:\n");
		for (Arista a : obtenerTodasLasAristas()) {
			sb.append("  ").append(a).append("\n");
		}

		return sb.toString();
	}
}
