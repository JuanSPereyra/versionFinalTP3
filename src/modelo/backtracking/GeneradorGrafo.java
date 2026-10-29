package modelo.backtracking;

import java.util.ArrayList;
import java.util.List;

import generadorRandomWrapper.Generador;
import modelo.grafo.Grafo;
import modelo.grafo.Vertice;

public class GeneradorGrafo {
	private int cantidadVertices;
	private int cantidadAristas;
	long semilla;
	private Grafo grafoARetornar;
	private List<Vertice> listaDeVertices;
	private static Generador generadorRnd;

	public Grafo grafoRandom(int cantidadVertices, int cantidadAristas) {
		return random(cantidadVertices, cantidadAristas);
	}

	public static void setGenerador(Generador generador) {
		generadorRnd = generador;
	}

	private Grafo random(int cantidadVertices, int cantidadAristas) {

		validarCantVertices(cantidadVertices);
		validadCantAristas(cantidadAristas);
		validarMaximoAristas(cantidadVertices, cantidadAristas);

		iniciarGrafoYVertices(cantidadVertices);

		this.cantidadAristas = cantidadAristas;
		agregarAristas();

		return grafoARetornar;
	}

	void iniciarGrafoYVertices(int cantidadVertices) {
		this.cantidadVertices = cantidadVertices;
		this.grafoARetornar = new Grafo();
		this.listaDeVertices = new ArrayList<Vertice>();

		agregarVertices();
	}

	private void validarCantVertices(int cantidadVertices) {
		if (cantidadVertices < 0)
			throw new IllegalArgumentException("Vertice no puede ser menor a 0");
	}

	private void validadCantAristas(int cantidadVertices) {
		if (cantidadVertices < 0)
			throw new IllegalArgumentException("Arista no puede ser menor a 0");
	}

	private void validarMaximoAristas(int cantidadVertices, int cantidadAristas) {
		int cantMaxAristas = cantidadVertices * (cantidadVertices - 1) / 2;

		if (cantidadAristas > cantMaxAristas)
			throw new IllegalArgumentException("Cantidad de Aristas supera el maximo" + cantMaxAristas);
	}

	private void agregarAristas() {

		int agregados = 0;

		while (agregados < cantidadAristas) {
			if (agregado())
				agregados++;
		}
	}

	boolean agregado() {
		Vertice origen = listaDeVertices.get(generadorRnd.nextInt(cantidadVertices));
		Vertice destino = listaDeVertices.get(generadorRnd.nextInt(cantidadVertices));

		if (!origen.equals(destino) && !grafoARetornar.existeArista(origen, destino)) {
			grafoARetornar.agregarArista(origen, destino);
			return true;
		} else {
			return false;
		}
	}

	private void agregarVertices() {

		for (int i = 0; i < cantidadVertices; i++) {
			Vertice v = new Vertice(String.valueOf(i));
			grafoARetornar.agregarVertice(v);
			listaDeVertices.add(v);
		}
	}
}
