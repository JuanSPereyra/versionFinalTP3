package vista.interfaz;

import java.awt.event.ActionListener;
import java.util.*;
import estructuraDatosAux.Par;
import estructuraDatosAux.VerticeVisual;

public interface ResultadoVista {

	void listenerMostrarSolucion(ActionListener listener);

	void listenerVolverAlMenu(ActionListener listener);

	void listenerSalir(ActionListener listener);

	void listenerModificarDatos(ActionListener listener);

	int solucionElegida();

	void mostrarResultado(String string);

	void completarComboSoluciones(List<String> nombreHeuristica);

	void mostrarGrafo(List<VerticeVisual> vertices, List<Par<String, String>> aristas);

	void mostrarMensajeError(String mensaje);
}
