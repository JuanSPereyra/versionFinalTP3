package modelo.backtracking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import modelo.grafo.Grafo;
import modelo.grafo.Vertice;

public class BackTrackingTest {

	private BackTracking backTracking;
	private Grafo grafo;
	private Vertice a, b, c, d, e, f, h;
	private Set<Vertice> resultado;

	@Before
	public void inicializar() {
		backTracking = new BackTracking();
		grafo = new Grafo();
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

		resultado = backTracking.resolverGrafo(grafo);
	}

	@Test
	public void buscarResuelveElGrafoTest() {
		assertEquals(4, resultado.size());
	}

	@Test
	public void cancelarAntesDeResolverDevuelveNullTest() {
		backTracking.cancelar();
		assertNull(backTracking.resolverGrafo(grafo));
	}

	@Test
	public void grafoVacioDevuelveConjuntoVacioTest() {
		assertEquals(0, backTracking.resolverGrafo(new Grafo()).size());
	}

	@Test
	public void grafoSinVerticesDevuelveTodosLosVerticesComoConjuntoTest() {
		assertEquals(3, backTracking.resolverGrafo(grafoSinAristas()).size());
	}

	@Test
	public void grafoCompletoDevuelveUnVerticeComoConjuntoTest() {
		assertEquals(1, backTracking.resolverGrafo(grafoCompleto()).size());
	}

	@Test
	public void resolverDosVecesDaElMismoResultadoEnElConjuntoTest() {
		int primero = backTracking.resolverGrafo(grafo).size();
		int segundo = backTracking.resolverGrafo(grafo).size();
		assertEquals(primero, segundo);
	}

	private Grafo grafoSinAristas() {
		Grafo g = new Grafo();
		g.agregarVertice(a);
		g.agregarVertice(b);
		g.agregarVertice(c);
		return g;
	}

	private Grafo grafoCompleto() {
		Grafo g = new Grafo();
		g.agregarVertice(a);
		g.agregarVertice(b);
		g.agregarVertice(c);

		g.agregarArista(a, b);
		g.agregarArista(a, c);
		g.agregarArista(b, c);
		return g;
	}
}
