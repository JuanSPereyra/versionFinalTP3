package vista.interfaz;

import java.awt.event.ActionListener;

public interface CargarJsonVista {

	void listenerSeleccionarArchivo(ActionListener listener);

	void listenerVolverAlMenu(ActionListener listener);

	void listenerVerResultados(ActionListener listener);

	void mostrarRutaArchivo(String ruta);

	void habilitarBotonResultado(boolean habilitar);

	void mostrarMensajeError(String mensaje);
}
