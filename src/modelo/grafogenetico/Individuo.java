package modelo.grafogenetico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import generadorRandomWrapper.Generador;
import modelo.Instancia;
import modelo.grafo.Vertice;

public class Individuo {
	private boolean[] _bits;
	private Instancia _instancia;

	private static Generador _generador;
	private static int _cantVertices;

	public static void setGenerador(Generador random) {
		_generador = random;
	}

	public static void setCantVertices(int cantVertices) {
		_cantVertices = cantVertices;
	}

	public static Individuo aleatorio(Instancia instancia) {
		Individuo ret = new Individuo(instancia);

		for (int i = 0; i < _cantVertices; ++i)
			ret.set(i, _generador.nextBoolean());

		return ret;
	}

	private static Individuo vacio(Instancia instancia) {
		return new Individuo(instancia);
	}

	static Individuo prefijado(Instancia instancia, String bits) {

		if (bits != null && bits.length() != _cantVertices)
			throw new IllegalArgumentException("El string de bits es incorrecto! bits = " + bits);

		Individuo ret = Individuo.vacio(instancia);

		for (int i = 0; i < _cantVertices; ++i)
			ret.set(i, bits.charAt(i) == '1');

		return ret;
	}

	private Individuo(Instancia instancia) {
		if (_generador == null)
			throw new IllegalArgumentException("Individuo no tiene un generador!");

		_bits = new boolean[_cantVertices];
		_instancia = instancia;
	}

	public void mutar() {
		int i = _generador.nextInt(_cantVertices);
		_bits[i] = !_bits[i];
	}

	public Individuo[] combinar(Individuo that) {
		int k = _generador.nextInt(_cantVertices);

		Individuo hijo1 = Individuo.vacio(_instancia);
		Individuo hijo2 = Individuo.vacio(_instancia);

		for (int i = 0; i < k; ++i) {
			hijo1.set(i, this.get(i));
			hijo2.set(i, that.get(i));
		}

		for (int i = k; i < _cantVertices; ++i) {
			hijo1.set(i, that.get(i));
			hijo2.set(i, this.get(i));
		}

		return new Individuo[] { hijo1, hijo2 };
	}

	public boolean get(int i) {
		return _bits[i];
	}

	void set(int i, boolean nuevo) {
		_bits[i] = nuevo;
	}

	public int fitness() {
		int verticesSeleccionados = 0;

		Set<Vertice> alcanzados = new HashSet<>();
		List<Vertice> listaVertices = new ArrayList<>(_instancia.getVertices());

		for (int i = 0; i < _bits.length; i++) {
			if (get(i)) {
				verticesSeleccionados++;
				Vertice v = listaVertices.get(i);
				alcanzados.add(v);
				alcanzados.addAll(_instancia.vecinosDe(v));
			}
		}

		int noDominados = listaVertices.size() - alcanzados.size();

		if (noDominados > 0)
			return -100 * noDominados;
		else
			return 100 - verticesSeleccionados;
	}
}
