package modelo.backtracking;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import generadorRandomWrapper.GeneradorPrefijado;
import generadorRandomWrapper.GeneradorAleatorio;
import modelo.grafo.Grafo;
import modelo.grafo.Vertice;

public class GeneradorGrafoTest {

	GeneradorGrafo generador;

	@Before
	public void inicializar() {
		generador = new GeneradorGrafo();
		GeneradorGrafo.setGenerador(new GeneradorAleatorio(0));
	}

	@Test
	public void generarCantidadDeVertices() {
		Grafo g = generador.grafoRandom(5, 0);
		assertEquals(5, g.cantidadVertices());
	}

	@Test
	public void generarCantidadDeAristas() {
		Grafo g = generador.grafoRandom(5, 4);
		assertEquals(4, g.cantidadAristas());
	}

	@Test
	public void rechazarMismoVerticeParaArista() {
		GeneradorGrafo.setGenerador(new GeneradorPrefijado(2));
		generador.iniciarGrafoYVertices(3);
		assertFalse(generador.agregado());
	}

	@Test
	public void aceptarDistintoVerticeParaArista() {
		generador.grafoRandom(3, 1);
		assertTrue(generador.agregado());
	}

	@Test
	public void puedeGenerarGrafoCompleto() {
		int n = 8, maximo = n * (n - 1) / 2;

		Grafo g = generador.grafoRandom(n, maximo);
		assertEquals(maximo, g.cantidadAristas());
	}

	@Test
	public void puedeGenerarGrafoCompleto2() {
		int n = 8;
		int maximo = n * (n - 1) / 2;
		Grafo g = generador.grafoRandom(n, maximo);

		assertEquals(maximo, g.cantidadAristas());
		assertTrue(esGrafoCompleto(g));
	}

	@Test(expected = IllegalArgumentException.class)
	public void rechazaMasAristasQueLasPosibles() {
		generador.grafoRandom(4, 7);
	}

	@Test
	public void grafoVacio() {
		Grafo g = generador.grafoRandom(0, 0);
		assertEquals(0, g.cantidadAristas());
	}

	@Test
	public void unSoloVerticeSinAristas() {
		Grafo g = generador.grafoRandom(1, 0);
		assertEquals(0, g.cantidadAristas());
	}

	@Test(expected = IllegalArgumentException.class)
	public void rechazaVerticeNegativo() {
		generador.grafoRandom(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rechazaAristaNegativa() {
		generador.grafoRandom(4, -1);
	}

	@Test
	public void mismaSemillaGeneraMismoGrafo() {
		int n = 8;
		generador = new GeneradorGrafo();
		GeneradorGrafo.setGenerador(new GeneradorAleatorio(42L));
		Grafo g1 = generador.grafoRandom(n, 10);
		GeneradorGrafo.setGenerador(new GeneradorAleatorio(42L));
		Grafo g2 = generador.grafoRandom(n, 10);

		assertTrue(grafoSonIguales(g1, g2));
	}

	private boolean grafoSonIguales(Grafo g1, Grafo g2) {
		int cantidadVertices = g1.cantidadVertices();
		boolean bandera = true;

		for (int i = 0; i < cantidadVertices; i++) {
			for (int j = i + 1; j < cantidadVertices; j++) {
				Vertice a1 = g1.getVertice(String.valueOf(i));
				Vertice b1 = g1.getVertice(String.valueOf(j));
				Vertice a2 = g2.getVertice(String.valueOf(i));
				Vertice b2 = g2.getVertice(String.valueOf(j));
				bandera = bandera && (g1.existeArista(a1, b1) == g2.existeArista(a2, b2));
			}
		}
		return bandera;
	}

	private boolean esGrafoCompleto(Grafo g) {
		int cantidadVertices = g.cantidadVertices();
		boolean bandera = true;

		for (int i = 0; i < cantidadVertices; i++) {
			Vertice v1 = g.getVertice(String.valueOf(i));
			for (int j = 0; j < cantidadVertices; j++) {
				if (i != j) {
					Vertice v2 = g.getVertice(String.valueOf(j));
					bandera = bandera && (g.existeArista(v1, v2));
				}
			}
		}
		return bandera;
	}
}
