package swingWorker;

public interface InterfazTestStressPresentador {

	void mostrarResultado(String texto);

	void actualizarProgreso(int porcentaje);

	void finalizarTest();

	void habilitarBotonesTestStress(boolean b);
}
