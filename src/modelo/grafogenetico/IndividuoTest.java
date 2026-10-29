package modelo.grafogenetico;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import generadorRandomWrapper.GeneradorPrefijado;
import modelo.Instancia;

public class IndividuoTest {

	@Before
	public void inicializar() {
		Individuo.setCantVertices(5);
	}

	@Test
	public void individuoAleatorio() {
		Individuo ind = Individuo.aleatorio(defecto());
		Individuo.setGenerador(new GeneradorPrefijado(0));
		sonIguales("00000", ind);
	}

	@Test
	public void mutarUltimoIndividuoAleatorio() {
		Individuo ind = Individuo.aleatorio(defecto());
		Individuo.setGenerador(new GeneradorPrefijado(4));

		ind.mutar();
		sonIguales("00001", ind);
	}

	@Test
	public void mutarPrimeroIndividuoAleatorio() {
		Individuo ind = Individuo.aleatorio(defecto());
		Individuo.setGenerador(new GeneradorPrefijado(0));

		ind.mutar();
		sonIguales("10000", ind);
	}

	@Test
	public void mutarPrimeroTest() {
		Individuo.setGenerador(new GeneradorPrefijado(0));
		Individuo ind = Individuo.prefijado(defecto(), "11011");

		ind.mutar();
		sonIguales("01011", ind);
	}

	@Test
	public void mutarUltimoTest() {
		Individuo.setGenerador(new GeneradorPrefijado(4));
		Individuo ind = Individuo.prefijado(defecto(), "11011");

		ind.mutar();
		sonIguales("11010", ind);
	}

	@Test
	public void combinarTest() {
		Individuo.setGenerador(new GeneradorPrefijado(2));
		Individuo padre1 = Individuo.prefijado(defecto(), "11001");
		Individuo padre2 = Individuo.prefijado(defecto(), "01111");

		Individuo[] hijos = padre1.combinar(padre2);

		assertEquals(2, hijos.length);
		sonIguales("11111", hijos[0]);
		sonIguales("01001", hijos[1]);
	}

	@Test
	public void combinarInicioTest() {
		Individuo.setGenerador(new GeneradorPrefijado(0));
		Individuo padre1 = Individuo.prefijado(defecto(), "11001");
		Individuo padre2 = Individuo.prefijado(defecto(), "01111");

		Individuo[] hijos = padre1.combinar(padre2);

		assertEquals(2, hijos.length);
		sonIguales("01111", hijos[0]);
		sonIguales("11001", hijos[1]);
	}

	@Test
	public void combinarFinalTest() {
		Individuo.setGenerador(new GeneradorPrefijado(5));
		Individuo padre1 = Individuo.prefijado(defecto(), "11001");
		Individuo padre2 = Individuo.prefijado(defecto(), "01111");

		Individuo[] hijos = padre1.combinar(padre2);

		assertEquals(2, hijos.length);
		sonIguales("01111", hijos[1]);
		sonIguales("11001", hijos[0]);
	}

	public void sonIguales(String bits, Individuo ind) {
		for (int i = 0; i < bits.length(); ++i)
			assertEquals(bits.charAt(i) == '1', ind.get(i));
	}

	private Instancia defecto() {
		Instancia ret = new Instancia();
		ret.agregarVertice("a");
		ret.agregarVertice("b");
		ret.agregarVertice("c");
		ret.agregarVertice("d");
		ret.agregarVertice("e");

		return ret;
	}
}
