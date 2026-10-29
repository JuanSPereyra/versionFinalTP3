package modelo.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.grafo.Vertice;

public class VerticeTest {
	private Vertice vertice;

	@Before
	public void inicializar() {
		vertice = new Vertice("Argentina");
	}

	@Test
	public void obtenerNombreDelvuelveNombreTest() {
		assertEquals("Argentina", vertice.getNombre());
	}

	@Test
	public void verticesConMismoNombreSonIgualesTest() {
		assertEquals(vertice, new Vertice("Argentina"));
	}

	@Test
	public void verticesIgualesTienenMismoHashCodeTest() {
		assertEquals(vertice.hashCode(), new Vertice("Argentina").hashCode());
	}

	@Test
	public void verticesConDistintoNombreSonDistintosTest() {
		assertNotEquals(vertice, new Vertice("Brasil"));
	}

	@Test
	public void verticeNoEsIgualANullTest() {
		assertFalse(vertice.equals(null));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void verticeNoEsIgualAOtroTipoDeObjetoTest() {
		assertFalse(vertice.equals("12"));
	}

	@Test
	public void equalsEsReflexivoTest() {
		assertTrue(vertice.equals(vertice));
	}

	@Test
	public void toStringDevuelveElNombre() {
		assertEquals("Argentina", vertice.toString());
	}
}
