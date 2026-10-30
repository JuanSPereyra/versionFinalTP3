package modelo.tests;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import modelo.Instancia;
import modelo.Solucion;
import modelo.grafo.Vertice;
import modelo.heuristicas.SolverGoloso;

public class SolverGolosoTest {

	private Instancia instancia;
	private SolverGoloso solver;
	private static final Set<Vertice> _EsperadoPorCobertura = Set.of(new Vertice("H"), new Vertice("C"),
			new Vertice("A"), new Vertice("E"));
	private static final Set<Vertice> _EsperadoPorGradoMayor = Set.of(new Vertice("H"), new Vertice("C"),
			new Vertice("B"), new Vertice("E"));
	private Instancia enunciadoDelTp;
	private SolverGoloso solverEnunciadoDelTp;
	private static final Set<Vertice> _EsperadoTp = Set.of(new Vertice("2"), new Vertice("4"));
	private Instancia instanciaVerticesAislados;
	private SolverGoloso solverVerticesAislados;
	private static final Set<Vertice> _TodosAisladosEsperado = Set.of(new Vertice("X"), new Vertice("Y"),
			new Vertice("Z"));
	private Instancia instanciaUnSoloVertice;
	private SolverGoloso solverUnSoloVertice;
	private static final Set<Vertice> _UnSoloVerticeEsperado = Set.of(new Vertice("X"));

	@Before
	public void inicializar() {
		instancia = new Instancia();
		instancia.agregarVertice("A");
		instancia.agregarVertice("B");
		instancia.agregarVertice("C");
		instancia.agregarVertice("D");
		instancia.agregarVertice("E");
		instancia.agregarVertice("F");
		instancia.agregarVertice("H");
		instancia.agregarArista("A", "B");
		instancia.agregarArista("B", "C");
		instancia.agregarArista("C", "D");
		instancia.agregarArista("C", "E");
		instancia.agregarArista("E", "F");
		solver = new SolverGoloso(instancia);

		enunciadoDelTp = new Instancia();
		enunciadoDelTp.agregarVertice("1");
		enunciadoDelTp.agregarVertice("2");
		enunciadoDelTp.agregarVertice("3");
		enunciadoDelTp.agregarVertice("4");
		enunciadoDelTp.agregarVertice("5");
		enunciadoDelTp.agregarVertice("6");
		enunciadoDelTp.agregarArista("6", "4");
		enunciadoDelTp.agregarArista("4", "5");
		enunciadoDelTp.agregarArista("4", "3");
		enunciadoDelTp.agregarArista("5", "1");
		enunciadoDelTp.agregarArista("5", "2");
		enunciadoDelTp.agregarArista("1", "2");
		enunciadoDelTp.agregarArista("3", "2");
		solverEnunciadoDelTp = new SolverGoloso(enunciadoDelTp);

		instanciaVerticesAislados = new Instancia();
		instanciaVerticesAislados.agregarVertice("X");
		instanciaVerticesAislados.agregarVertice("Y");
		instanciaVerticesAislados.agregarVertice("Z");
		solverVerticesAislados = new SolverGoloso(instanciaVerticesAislados);

		instanciaUnSoloVertice = new Instancia();
		instanciaUnSoloVertice.agregarVertice("6");
		solverUnSoloVertice = new SolverGoloso(instanciaUnSoloVertice);
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
		assertEquals(_EsperadoPorCobertura, solver.resolverPorMayorCobertura().getVertices());
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
		assertEquals(_EsperadoPorGradoMayor, solver.resolverPorGradoMayor().getVertices());
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
		assertEquals(_UnSoloVerticeEsperado.size(), solverUnSoloVertice.resolverPorGradoMayor().cardinal());
	}

	@Test
	public void todosAisladosEntranTodosTest() {
		assertEquals(_TodosAisladosEsperado.size(), solverVerticesAislados.resolverPorPesoDelVertice().cardinal());
	}

	@Test
	public void coberturaEncuentraElOptimoDelEnunciadoTest() {
		assertEquals(_EsperadoTp.size(), solverEnunciadoDelTp.resolverPorMayorCobertura().cardinal());
	}

	@Test
	public void gradoEncuentraElOptimoDelEnunciadoTest() {
		assertEquals(_EsperadoTp, solverEnunciadoDelTp.resolverPorGradoMayor().getVertices());
	}

	@Test
	public void pesoVerticeDominaElGrafoDelEnunciadoTest() {
		assertTrue(esDominante(enunciadoDelTp, solverEnunciadoDelTp.resolverPorPesoDelVertice()));
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
		for (Solucion s : solverEnunciadoDelTp.resolver())
			assertTrue(esDominante(enunciadoDelTp, s));
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
