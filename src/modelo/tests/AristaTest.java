package modelo.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import modelo.grafo.Arista;
import modelo.grafo.Vertice;

public class AristaTest {

	private Vertice a;
	private Vertice b;
	private Vertice c;

	@Before
	public void inicializar() {
		a = new Vertice("Argentina");
		b = new Vertice("Brasil");
		c = new Vertice("Colombia");
	}

	@Test(expected = IllegalArgumentException.class)
	public void OpuestoDeVerticeAjenoTest() {
		new Arista(a, b).obtenerOpuesto(c);
	}

	@Test
	public void obtenerOrigenTest() {
		Arista arista = new Arista(a, b);
		assertEquals(a, arista.getOrigen());
	}

	@Test
	public void obtenerDestinoTest() {
		Arista arista = new Arista(a, b);
		assertEquals(b, arista.getDestino());
	}

	@Test
	public void obtenerExtremoTest() {
		Arista arista = new Arista(a, b);
		assertEquals(b, arista.obtenerOpuesto(a));
	}

	@Test
	public void obtenerNombreTest() {
		Arista arista = new Arista(a, b);
		assertEquals("Argentina" + " -- " + "Brasil", arista.toString());
	}
}
