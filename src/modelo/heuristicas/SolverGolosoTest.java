package modelo.heuristicas;

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

public class SolverGolosoTest {

	private Instancia instancia;
	private SolverGoloso solver;

	@Before
	public void inicializar() {
		instancia = new Instancia();
		cargarVertices(instancia, Arrays.asList("A", "B", "C", "D", "E", "F", "H"));
		instancia.agregarArista("A", "B");
		instancia.agregarArista("B", "C");
		instancia.agregarArista("C", "D");
		instancia.agregarArista("C", "E");
		instancia.agregarArista("E", "F");
		solver = new SolverGoloso(instancia);
	}

	@Test
	public void coberturaDevuelveConjuntoDominanteTest() {
		Solucion s = solver.resolverPorMayorCobertura();
		assertTrue(esDominante(instancia, s));
	}

	@Test
	public void coberturaIncluyeAlVerticeAisladoTest() {
		assertTrue(solver.resolverPorMayorCobertura().contiene(new Vertice("H")));
	}

	@Test
	public void coberturaDaElConjuntoEsperadoTest() {
		assertEquals(conjunto(Arrays.asList("H", "C", "A", "E")), solver.resolverPorMayorCobertura().getVertices());
	}

	@Test
	public void gradoDevuelveConjuntoDominanteTest() {
		Solucion s = solver.resolverPorGradoMayor();
		assertTrue(esDominante(instancia, s));
	}

	@Test
	public void gradoIncluyeAlVerticeAisladoTest() {
		assertTrue(solver.resolverPorGradoMayor().contiene(new Vertice("H")));
	}

	@Test
	public void gradoDaElConjuntoEsperadoTest() {

		assertEquals(conjunto(Arrays.asList("H", "C", "B", "E")), solver.resolverPorGradoMayor().getVertices());
	}

	@Test
	public void pesoVerticeDevuelveConjuntoDominanteTest() {
		Solucion s = solver.resolverPorPesoDelVertice();
		assertTrue(esDominante(instancia, s));
	}

	@Test
	public void pesoVerticeIncluyeAlVerticeAisladoTest() {
		assertTrue(solver.resolverPorPesoDelVertice().contiene(new Vertice("H")));
	}

	@Test
	public void resolverDosVecesDaElMismoResultadoTest() {
		Set<Vertice> primera = solver.resolverPorMayorCobertura().getVertices();
		assertEquals(primera, solver.resolverPorMayorCobertura().getVertices());
	}

	@Test
	public void instanciaVaciaDaSolucionVaciaTest() {
		Solucion s = new SolverGoloso(new Instancia()).resolverPorMayorCobertura();
		assertEquals(0, s.cardinal());
	}

	@Test
	public void unSoloVerticeEntraEnLaSolucionTest() {
		Solucion s = new SolverGoloso(instanciaConAislados(Arrays.asList("X"))).resolverPorGradoMayor();
		assertEquals(1, s.cardinal());
	}

	@Test
	public void todosAisladosEntranTodosTest() {
		Instancia aislados = instanciaConAislados(Arrays.asList("X", "Y", "Z"));
		assertEquals(3, new SolverGoloso(aislados).resolverPorPesoDelVertice().cardinal());
	}

	@Test
	public void coberturaEncuentraElOptimoDelEnunciadoTest() {
		Instancia enunciado = enunciadoDelTp();
		assertEquals(2, new SolverGoloso(enunciado).resolverPorMayorCobertura().cardinal());
	}

	@Test
	public void gradoEncuentraElOptimoDelEnunciadoTest() {
		Instancia enunciado = enunciadoDelTp();
		assertEquals(conjunto(Arrays.asList("2", "4")),
				new SolverGoloso(enunciado).resolverPorGradoMayor().getVertices());
	}

	@Test
	public void pesoVerticeDominaElGrafoDelEnunciadoTest() {
		Instancia enunciado = enunciadoDelTp();
		assertTrue(esDominante(enunciado, new SolverGoloso(enunciado).resolverPorPesoDelVertice()));
	}

	@Test
	public void resolverDevuelveUnaSolucionPorEstrategiaTest() {
		assertEquals(7, solver.resolver().size());
	}

	@Test
	public void resolverDevuelveSoloSolucionesDominantesTest() {
		for (Solucion s : solver.resolver())
			assertTrue(esDominante(instancia, s));
	}

	@Test
	public void resolverIncluyeAlVerticeAisladoEnTodasLasSolucionesTest() {
		for (Solucion s : solver.resolver())
			assertTrue(s.contiene(new Vertice("H")));
	}

	@Test
	public void resolverEnInstanciaVaciaDevuelveSolucionesVaciasTest() {
		for (Solucion s : new SolverGoloso(new Instancia()).resolver())
			assertEquals(0, s.cardinal());
	}

	@Test
	public void menosCoberturaIncluyeAlVerticeAisladoTest() {
		assertTrue(solver.resolverPorMenorCobertura().contiene(new Vertice("H")));
	}

	@Test
	public void gradoMenorDevuelveConjuntoDominanteTest() {
		assertTrue(esDominante(instancia, solver.resolverPorGradoMenor()));
	}

	@Test
	public void gradoMenorIncluyeAlVerticeAisladoTest() {
		assertTrue(solver.resolverPorGradoMenor().contiene(new Vertice("H")));
	}

	@Test
	public void nombreDevuelveConjuntoDominanteTest() {
		assertTrue(esDominante(instancia, solver.resolverPorNombre()));
	}

	@Test
	public void nombreIncluyeAlVerticeAisladoTest() {
		assertTrue(solver.resolverPorNombre().contiene(new Vertice("H")));
	}

	@Test
	public void nombreInversoDevuelveConjuntoDominanteTest() {
		assertTrue(esDominante(instancia, solver.resolverPorNombreInverso()));
	}

	@Test
	public void nombreInversoIncluyeAlVerticeAisladoTest() {
		assertTrue(solver.resolverPorNombreInverso().contiene(new Vertice("H")));
	}

	@Test
	public void todasLasEstrategiasDominanElGrafoDelEnunciadoTest() {
		Instancia enunciado = enunciadoDelTp();
		for (Solucion s : new SolverGoloso(enunciado).resolver())
			assertTrue(esDominante(enunciado, s));
	}

	private void cargarVertices(Instancia i, List<String> nombres) {
		for (String n : nombres)
			i.agregarVertice(n);
	}

	private Instancia instanciaConAislados(List<String> nombres) {
		Instancia i = new Instancia();
		cargarVertices(i, nombres);
		return i;
	}

	private Instancia enunciadoDelTp() {
		Instancia i = new Instancia();
		String[] vertices = { "1", "2", "3", "4", "5", "6" };
		String[][] aristas = { { "6", "4" }, { "4", "5" }, { "4", "3" }, { "5", "1" }, { "5", "2" }, { "1", "2" },
				{ "3", "2" } };

		for (String v : vertices)
			i.agregarVertice(v);
		for (String[] a : aristas)
			i.agregarArista(a[0], a[1]);
		return i;
	}

	private Set<Vertice> conjunto(List<String> nombres) {
		Set<Vertice> ret = new HashSet<>();
		for (String n : nombres)
			ret.add(new Vertice(n));
		return ret;
	}

	private boolean esDominante(Instancia i, Solucion s) {
		for (Vertice v : i.getVertices())
			if (!s.contiene(v) && !tieneVecinoEnSolucion(i, v, s))
				return false;
		return true;
	}

	private boolean tieneVecinoEnSolucion(Instancia i, Vertice v, Solucion s) {
		for (Vertice vecino : i.vecinosDe(v))
			if (s.contiene(vecino))
				return true;
		return false;
	}
}
