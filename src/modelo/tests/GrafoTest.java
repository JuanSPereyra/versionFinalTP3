package modelo.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import modelo.grafo.Grafo;
import modelo.grafo.Vertice;

public class GrafoTest {

	private Grafo grafo;
	private Grafo grafoVacio;
	private Vertice a, b, c, d, e, f, h;

	@Before
	public void inicializar() {
		grafo = new Grafo();
		grafoVacio = new Grafo();

		a = new Vertice("A");
		b = new Vertice("B");
		c = new Vertice("C");
		d = new Vertice("D");
		e = new Vertice("E");
		f = new Vertice("F");
		h = new Vertice("H");

		grafo.agregarVertice(a);
		grafo.agregarVertice(b);
		grafo.agregarVertice(c);
		grafo.agregarVertice(d);
		grafo.agregarVertice(e);
		grafo.agregarVertice(f);
		grafo.agregarVertice(h);

		grafo.agregarArista(a, b);
		grafo.agregarArista(b, c);
		grafo.agregarArista(c, d);
		grafo.agregarArista(c, e);
		grafo.agregarArista(e, f);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarAristaConVerticeInexistenteLanzaExcepcionTest() {
		grafoVacio.agregarVertice(a);
		grafoVacio.eliminarArista(a, b);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaConDestinoInexistenteLanzaExcepcionTest() {
		grafoVacio.agregarVertice(a);
		grafoVacio.agregarArista(a, b);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaDuplicadaLanzaExcepcionTest() {
		grafo.agregarArista(a, b);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaDuplicadaInvertidaLanzaExcepcionTest() {
		grafo.agregarArista(b, a);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaHaciaSiMismoLanzaExcepcionTest() {
		grafo.agregarArista(a, a);
	}

	@Test(expected = IllegalArgumentException.class)
	public void aristaConOrigenInexistenteLanzaExcepcionTest() {
		grafo.agregarArista(new Vertice("Alemania"), b);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarAristaInexistenteLanzaExcepcionTest() {
		grafo.eliminarArista(a, c);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarVerticeInexistenteLanzaExcepcionTest() {
		grafo.eliminarVertice(new Vertice("Z"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void busquedaEnGrafoVacioLanzaExcepcionTest() {
		grafoVacio.getVertice("Argentina");
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAristasDeVerticeInexistenteLanzaExcepcionTest() {
		grafo.obtenerAristasDe(new Vertice("Z"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticesConMismoNombreSonElMismoVerticeTest() {
		grafoVacio.agregarVertice(new Vertice("Argentina"));
		grafoVacio.agregarVertice(new Vertice("Argentina"));
	}

	@Test
	public void getVerticePorNombreDevuelveElVerticeTest() {
		assertEquals(a, grafo.getVertice("A"));
	}

	@Test
	public void agregaVerticeNuevoYAumentaCantidadTest() {
		grafoVacio.agregarVertice(a);
		assertEquals(1, grafoVacio.cantidadVertices());
		assertTrue(grafoVacio.obtenerVertices().contains(a));
	}

	@Test
	public void contieneVerticeAgregadoTest() {
		assertTrue(grafo.contieneVertice(a));
	}

	@Test
	public void noContieneVerticeNoAgregadoTest() {
		assertFalse(grafo.contieneVertice(new Vertice("Z")));
	}

	@Test
	public void eliminaVerticeSinAristasTest() {
		grafo.eliminarVertice(h);
		assertFalse(grafo.contieneVertice(h));
	}

	@Test
	public void eliminarVerticeConAristasLoQuitaDelGrafoTest() {
		grafo.eliminarVertice(a);
		assertFalse(grafo.contieneVertice(a));
	}

	@Test
	public void eliminarVerticeConAristasQuitaLaAristaDelVecinoTest() {
		grafo.eliminarVertice(a);
		assertEquals(1, grafo.obtenerAristasDe(b).size());
	}

	@Test
	public void eliminaAristaExistenteTest() {
		grafo.eliminarArista(a, b);
		assertEquals(0, grafo.obtenerAristasDe(a).size());
	}

	@Test
	public void eliminarAristaTambienLaQuitaDelOtroVerticeTest() {
		grafo.eliminarArista(a, b);
		assertEquals(1, grafo.obtenerAristasDe(b).size());
	}

	@Test
	public void getAristasDeVerticeSinAristasDevuelveListaVaciaTest() {
		assertTrue(grafo.obtenerAristasDe(h).isEmpty());
	}

	@Test
	public void todasLasAristasDeGrafoVacioEsListaVaciaTest() {
		assertTrue(grafoVacio.obtenerTodasLasAristas().isEmpty());
	}

	@Test
	public void todasLasAristasNoDuplicaLasAristasCompartidasTest() {
		assertEquals(5, grafo.obtenerTodasLasAristas().size());
	}

	@Test
	public void modificarVerticesObtenidosNoAfectaAlGrafoTest() {
		grafo.obtenerVertices().clear();
		assertEquals(7, grafo.cantidadVertices());
	}

	@Test
	public void modificarAristasObtenidasNoAfectaAlGrafoTest() {
		grafo.obtenerAristasDe(a).clear();
		assertEquals(1, grafo.obtenerAristasDe(a).size());
	}

	@Test
	public void toStringIncluyeEncabezadoDelGrafoTest() {
		assertTrue(grafo.toString().contains("Grafo:"));
	}

	@Test
	public void toStringIncluyeLaSeccionDeAristasTest() {
		assertTrue(grafo.toString().contains("Aristas:"));
	}

	@Test
	public void verticeAisladoTest() {
		assertTrue(grafo.estaAislado(h));
	}

	@Test
	public void verticeConAristaNoEstaAisladoTest() {
		assertFalse(grafo.estaAislado(a));
	}

	@Test
	public void conjuntoDominanteValidoTest() {
		Set<Vertice> conjunto = new HashSet<>(Arrays.asList(b, c, e, h));
		assertTrue(grafo.esConjuntoDominante(conjunto));
	}

	@Test
	public void conjuntoSinElAisladoNoDominaTest() {
		Set<Vertice> conjunto = new HashSet<>(Arrays.asList(b, c, e));
		assertFalse(grafo.esConjuntoDominante(conjunto));
	}

	@Test
	public void conjuntoQueNoDominaTest() {
		Set<Vertice> conjunto = new HashSet<>(Arrays.asList(c, h));
		assertFalse(grafo.esConjuntoDominante(conjunto));
	}

	@Test
	public void conjuntoVacioNoDominaGrafoConVerticesTest() {
		assertFalse(grafo.esConjuntoDominante(new HashSet<>()));
	}

	@Test
	public void conjuntoVacioDominaGrafoVacioTest() {
		assertTrue(grafoVacio.esConjuntoDominante(new HashSet<>()));
	}
}
