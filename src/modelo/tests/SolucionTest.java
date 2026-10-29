package modelo.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import modelo.Instancia;
import modelo.Solucion;
import modelo.grafo.Vertice;

public class SolucionTest {

	private Instancia instancia;
	private Solucion solucion;
	private Vertice a, b, c, d, e, f, h;

	@Before
	public void inicializar() {
		instancia = new Instancia();
		for (String n : new String[] { "A", "B", "C", "D", "E", "F", "H" })
			instancia.agregarVertice(n);

		instancia.agregarArista("A", "B");
		instancia.agregarArista("B", "C");
		instancia.agregarArista("C", "D");
		instancia.agregarArista("C", "E");
		instancia.agregarArista("E", "F");

		solucion = new Solucion(instancia);

		a = new Vertice("A");
		b = new Vertice("B");
		c = new Vertice("C");
		d = new Vertice("D");
		e = new Vertice("E");
		f = new Vertice("F");
		h = new Vertice("H");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nuevosCubiertosDeVerticeInexistenteLanzaExcepcionTest() {
		solucion.nuevosCubiertos(new Vertice("Z"));
	}

	@Test
	public void solucionNuevaEstaVaciaTest() {
		assertTrue(solucion.getVertices().isEmpty());
	}

	@Test
	public void solucionNuevaNoTieneDominadosTest() {
		assertFalse(solucion.estaDominado(a));
	}

	@Test
	public void solucionNuevaNoDominaTodoTest() {
		assertFalse(solucion.dominioIgualGrafo());
	}

	@Test
	public void solucionDeInstanciaVaciaDominaTodoTest() {
		assertTrue(new Solucion(new Instancia()).dominioIgualGrafo());
	}

	@Test
	public void agregarVerticeLoDejaEnLaSolucionTest() {
		solucion.agregar(c);
		assertTrue(solucion.contiene(c));
	}

	@Test
	public void agregarVerticeLoAgregoASolucionTest() {
		solucion.agregar(c);
		assertEquals(1, solucion.cardinal());
	}

	@Test
	public void agregarVerticeNoAgregaSusVecinosALaSolucionTest() {
		solucion.agregar(c);
		assertEquals(new HashSet<>(Arrays.asList(c)), solucion.getVertices());
	}

	@Test
	public void agregarVerticeDominaALosVecinosYASiMismoTest() {
		solucion.agregar(c);
		assertEquals(new HashSet<>(Arrays.asList(b, c, d, e)), dominados());
	}

	@Test
	public void agregarElMismoVerticeDosVecesNoAumentaElCardinalTest() {
		agregarTodos(Arrays.asList(c, c));
		assertEquals(1, solucion.cardinal());
	}

	@Test
	public void agregarVerticeAisladoSoloSeDominaASiMismoTest() {
		solucion.agregar(h);
		assertEquals(new HashSet<>(Arrays.asList(h)), dominados());
	}

	@Test
	public void dominaTodoEsFalsoSiFaltanVerticesTest() {
		agregarTodos(Arrays.asList(c, a, e));
		assertFalse(solucion.dominioIgualGrafo());
	}

	@Test
	public void dominaTodoEsVerdaderoCuandoSeCubrenTodosTest() {
		agregarTodos(Arrays.asList(c, a, e, h));
		assertTrue(solucion.dominioIgualGrafo());
	}

	@Test
	public void getVerticesDevuelveLosElegidosTest() {
		agregarTodos(Arrays.asList(c, h));
		assertEquals(new HashSet<>(Arrays.asList(c, h)), solucion.getVertices());
	}

	@Test
	public void modificarVerticesObtenidosNoAfectaALaSolucionTest() {
		solucion.agregar(c);
		solucion.getVertices().clear();
		assertEquals(1, solucion.cardinal());
	}

	@Test
	public void nuevosCubiertosDeUnVerticeYaAgregadoEsCeroTest() {
		solucion.agregar(c);
		assertEquals(0, solucion.nuevosCubiertos(c));
	}

	@Test
	public void nuevosCubiertosDeVerticeAisladoEsUnoTest() {
		assertEquals(1, solucion.nuevosCubiertos(h));
	}

	@Test
	public void agregarEDominaAFTest() {
		solucion.agregar(e);
		assertTrue(solucion.estaDominado(f));
	}

	@Test
	public void nuevosCubiertosDeVerticeAisladoYaAgregadoEsCeroTest() {
		solucion.agregar(h);

		assertEquals(0, solucion.nuevosCubiertos(h));
	}

	@Test
	public void consultarNuevosCubiertosNoModificaLaSolucionTest() {
		solucion.nuevosCubiertos(c);
		assertFalse(solucion.estaDominado(c));
	}

	@Test
	public void toStringIncluyeLosVerticesElegidosTest() {
		solucion.agregar(c);
		assertTrue(solucion.toString().contains("C"));
	}

	private Set<Vertice> dominados() {
		Set<Vertice> ret = new HashSet<>();
		for (Vertice v : instancia.getVertices())
			if (solucion.estaDominado(v))
				ret.add(v);
		return ret;
	}

	private void agregarTodos(List<Vertice> vertices) {
		for (Vertice v : vertices) {
			solucion.agregar(v);
		}
	}
}
