package generadorRandomWrapper;

import java.util.Random;

public class GeneradorAleatorio implements Generador {
	private Random _random;

	public GeneradorAleatorio(long semilla) {
		_random = new Random(semilla);
	}

	public GeneradorAleatorio() {
		_random = new Random();
	}

	@Override
	public boolean nextBoolean() {
		return _random.nextBoolean();
	}

	@Override
	public int nextInt(int rango) {
		return _random.nextInt(rango);
	}
}
