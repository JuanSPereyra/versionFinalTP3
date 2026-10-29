package presentador;

import coordinadorMVP.CoordinadorPresentadores;
import estructuraDatosAux.Par;
import swingWorker.InterfazTestStressPresentador;
import swingWorker.TestStressWorker;
import vista.interfaz.StressVista;

public class TestStressPresentador implements InterfazTestStressPresentador {
	private StressVista _tsVista;
	private CoordinadorPresentadores coordinador;
	private TestStressWorker swingworker;

	public TestStressPresentador(StressVista testStressVista, CoordinadorPresentadores coordinador) {
		this._tsVista = testStressVista;
		this.coordinador = coordinador;

		iniciarListeners();
	}

	private void iniciarListeners() {
		listenerVolverAlMenu();
		listenerSalir();
		listenerEmpezarTest();
		listenerDetenerTest();
	}

	private void listenerVolverAlMenu() {
		this._tsVista.listenerVolverAlMenu(e -> {
			manejarClickVolverAlMenu();
		});
	}

	private void listenerSalir() {
		this._tsVista.listenerSalir(e -> {
			manejarClickBtnSalir();
		});
	}

	private void listenerEmpezarTest() {
		this._tsVista.listenerEmpezarTest(e -> {
			Par<String, Integer> datosParaTest = this._tsVista.datosParaTest();

			String cantVertices = datosParaTest.getIzquierda();
			int densidad = datosParaTest.getDerecha();

			manejarClickBtnEmpezarTest(cantVertices, densidad);
		});
	}

	private void listenerDetenerTest() {
		this._tsVista.listenerDetenerTest(e -> {
			manejarClickBtnDetenerTest();
		});
	}

	private void manejarClickBtnEmpezarTest(String vertices, int densidad) {
		if (swingworker != null && !swingworker.isDone()) {
			return;
		}

		try {
			int cantVertices = Integer.parseInt(vertices.trim());

			_tsVista.borrarResultadoAnterior();
			_tsVista.habilitarBotonesTestStress(true);

			swingworker = new TestStressWorker(this, cantVertices, densidad);
			swingworker.execute();

		} catch (NumberFormatException e) {
			_tsVista.mostrarMensajeError("Ingrese un número válido y mayor a 0");
		}
	}

	private void manejarClickBtnDetenerTest() {
		if (swingworker != null && !swingworker.isDone()) {
			swingworker.detener();
		}
	}

	private void manejarClickVolverAlMenu() {
		coordinador.iniciarAplicacion();
	}

	private void manejarClickBtnSalir() {
		System.exit(0);
	}

	@Override
	public void mostrarResultado(String texto) {
		_tsVista.mostrarResultado(texto);
	}

	@Override
	public void actualizarProgreso(int porcentaje) {
		_tsVista.actualizarProgreso(porcentaje);
	}

	@Override
	public void finalizarTest() {
		_tsVista.habilitarBotonesTestStress(false);
	}

	@Override
	public void habilitarBotonesTestStress(boolean b) {
		_tsVista.habilitarBotonesTestStress(b);
	}
}
