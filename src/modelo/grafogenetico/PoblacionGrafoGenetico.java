package modelo.grafogenetico;

import java.util.*;
import java.util.List;

import generadorRandomWrapper.Generador;
import generadorRandomWrapper.GeneradorAleatorio;
import modelo.Instancia;
import modelo.grafo.Vertice;

public class PoblacionGrafoGenetico {

	private ArrayList<Individuo> _individuos;
	private Instancia _instancia;

	private int _tamanio = 100;
	private int _mutadosPorIteracion = 10;
	private int _combinacionesPorIteracion = 20;
	private int _eliminadosPorIteracion = 60;
	private int _maxIteraciones = 100;
	private int _iteracion;

	private Generador _generador = new GeneradorAleatorio();

	public PoblacionGrafoGenetico(Instancia instancia) {
		this._instancia = instancia;

		Individuo.setGenerador(_generador);
		Individuo.setCantVertices(_instancia.cantidadVertices());
	}

	public Set<Vertice> resolver() {
		generarAleatoriamente();
		_iteracion = 0;

		while (satisfactoria() == false) {
			mutarAlgunos();
			recombinarAlgunos();
			eliminarPeores();
			completarConAleatorios();
			_iteracion++;
		}

		Set<Vertice> mejorIndividuo = transformarIndividuoASet(mejorIndividuo());
		return mejorIndividuo;
	}

	private Set<Vertice> transformarIndividuoASet(Individuo individuo) {
		Set<Vertice> setVertices = new HashSet<>();
		List<Vertice> todosLosVertices = new ArrayList<>(_instancia.getVertices());

		for (int i = 0; i < todosLosVertices.size(); i++) {

			if (individuo.get(i)) {
				setVertices.add(todosLosVertices.get(i));
			}
		}
		return setVertices;
	}

	private void generarAleatoriamente() {

		_individuos = new ArrayList<Individuo>(2 * _tamanio);

		for (int i = 0; i < _tamanio; ++i)
			_individuos.add(Individuo.aleatorio(_instancia));
	}

	private boolean satisfactoria() {
		return iteracion() == _maxIteraciones;
	}

	private int iteracion() {
		return _iteracion;
	}

	private void mutarAlgunos() {
		for (int i = 0; i < _mutadosPorIteracion; ++i)
			individuoAleatorio().mutar();
	}

	private void recombinarAlgunos() {
		for (int i = 0; i < _combinacionesPorIteracion; ++i) {
			Individuo padre1 = individuoAleatorio();
			Individuo padre2 = individuoAleatorio();

			for (Individuo hijo : padre1.combinar(padre2))
				_individuos.add(hijo);
		}
	}

	private Individuo individuoAleatorio() {
		int i = _generador.nextInt(_individuos.size());
		return _individuos.get(i);
	}

	private void eliminarPeores() {
		ordenar();
		for (int i = 0; i < _eliminadosPorIteracion; ++i)
			_individuos.remove(_individuos.size() - 1);
	}

	private void completarConAleatorios() {
		while (_individuos.size() < _tamanio)
			_individuos.add(Individuo.aleatorio(_instancia));
	}

	public Individuo mejorIndividuo() {
		ordenar();
		return _individuos.get(0);
	}

	public Individuo peorIndividuo() {
		ordenar();
		return _individuos.get(_individuos.size() - 1);
	}

	private void ordenar() {
		Collections.sort(_individuos, (i, j) -> (Integer) (j.fitness() - i.fitness()));
	}
}
