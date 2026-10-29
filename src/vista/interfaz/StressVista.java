package vista.interfaz;

import java.awt.event.ActionListener;

import estructuraDatosAux.Par;

public interface StressVista {

	void listenerVolverAlMenu(ActionListener listener);

	void listenerSalir(ActionListener listener);

	void listenerEmpezarTest(ActionListener listener);

	void listenerDetenerTest(ActionListener listener);

	Par<String, Integer> datosParaTest();

	void borrarResultadoAnterior();

	void habilitarBotonesTestStress(boolean corriendo);

	void mostrarResultado(String texto);

	void actualizarProgreso(int porcentaje);

	void mostrarMensajeError(String mensaje);
}
