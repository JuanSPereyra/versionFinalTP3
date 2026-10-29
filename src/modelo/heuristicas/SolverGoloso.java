package modelo.heuristicas;

import java.util.Comparator;
import java.util.List;

import modelo.Instancia;
import modelo.Solucion;
import modelo.grafo.Vertice;

public class SolverGoloso {
	private Instancia instancia;
	private List<Solucion> conjuntoSoluciones;

	public SolverGoloso(Instancia instancia) {
		this.instancia = instancia;
	}

	public List<Solucion> resolver() {

		conjuntoSoluciones = List.of(

				resolverPorMayorCobertura(),
				resolverPorMenorCobertura(),
				resolverPorGradoMayor(),
				resolverPorGradoMenor(),
				resolverPorNombre(),
				resolverPorNombreInverso(),
				resolverPorPesoDelVertice());

		return conjuntoSoluciones;
	}

	Solucion resolverPorMayorCobertura() {
		Solucion parcial = new Solucion(instancia);

		return resolver(parcial, (a, b) -> Integer.compare(parcial.nuevosCubiertos(b), parcial.nuevosCubiertos(a)));
	}

	Solucion resolverPorMenorCobertura() {
		Solucion parcial = new Solucion(instancia);

		return resolver(parcial, (a, b) -> Integer.compare(parcial.nuevosCubiertos(a), parcial.nuevosCubiertos(b)));
	}

	Solucion resolverPorGradoMayor() {
		return resolver(new Solucion(instancia), (a, b) -> Integer.compare(instancia.grado(b), instancia.grado(a)));
	}

	Solucion resolverPorGradoMenor() {
		return resolver(new Solucion(instancia), (a, b) -> Integer.compare(instancia.grado(a), instancia.grado(b)));
	}

	Solucion resolverPorNombre() {
		return resolver(new Solucion(instancia), (a, b) -> a.getNombre().compareTo(b.getNombre()));
	}

	Solucion resolverPorNombreInverso() {
		return resolver(new Solucion(instancia), (a, b) -> b.getNombre().compareTo(a.getNombre()));
	}

	Solucion resolverPorPesoDelVertice() {
		Solucion parcial = new Solucion(instancia);

		return resolver(parcial, (a, b) -> Double.compare(puntajePorVecino(b, parcial), puntajePorVecino(a, parcial)));
	}

	private Solucion resolver(Solucion sol, Comparator<Vertice> comparador) {

		for (Vertice v : instancia.getVertices()) {
			if (instancia.grado(v) == 0) {
				sol.agregar(v);
			}
		}
		while (!sol.dominioIgualGrafo()) {
			sol.agregar(elegirMejor(sol, comparador));
		}
		return sol;
	}

	private Vertice elegirMejor(Solucion parcial, Comparator<Vertice> comparador) {
		Vertice mejor = null;

		for (Vertice v : instancia.getVertices()) {
			if (!parcial.contiene(v) && parcial.nuevosCubiertos(v) != 0) {
				if (mejor == null || esMejor(v, mejor, comparador)) {
					mejor = v;
				}
			}
		}
		return mejor;
	}

	private boolean esMejor(Vertice candidato, Vertice actual, Comparator<Vertice> comparador) {
		int c = comparador.compare(candidato, actual);
		if (c != 0) {
			return c < 0;
		}

		return candidato.getNombre().compareTo(actual.getNombre()) < 0;
	}

	private double puntajePorVecino(Vertice v, Solucion parcial) {
		double puntaje = 0;

		if (!parcial.estaDominado(v)) {
			puntaje = puntaje + peso(v);
		}
		for (Vertice vecino : instancia.vecinosDe(v)) {
			if (!parcial.estaDominado(vecino)) {
				puntaje = puntaje + peso(vecino);
			}
		}
		return puntaje;
	}

	private double peso(Vertice v) {
		return 1.0 / (instancia.grado(v) + 1);
	}
}
