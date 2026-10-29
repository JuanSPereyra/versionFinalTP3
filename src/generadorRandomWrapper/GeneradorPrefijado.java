package generadorRandomWrapper;

public class GeneradorPrefijado implements Generador {
	private int _valor;

	public GeneradorPrefijado(int valor) {
		_valor = valor;
	}

	@Override
	public boolean nextBoolean() {
		return false;
	}

	@Override
	public int nextInt(int n) {
		return _valor;
	}
}
