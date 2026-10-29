package vista.interfaz;

import java.awt.event.ActionListener;

import estructuraDatosAux.Par;

public interface CargarDatosVista {

	void listenerAgregarVertice(ActionListener listener);

	void listenerAgregarArista(ActionListener listener);

	void listenerEliminarVertice(ActionListener listener);

	void listenerEliminarArista(ActionListener listener);

	void listenerCalcularDominante(ActionListener listener);

	void listenerVolverAlMenu(ActionListener listener);

	String obtenerTextoVertice();

	Par<String, String> obtenerOrigenDestinoDeArista();

	String obtenerVerticeAEliminar();

	Par<String, String> obtenerAristaAEliminar();

	void agregarVertice(String nombre);

	void agregarArista(String origen, String destino, String peso);

	void eliminarVertice(String nombre);

	void eliminarArista(String origen, String destino);

	void limpiarCampoVertice();

	void mostrarMensajeError(String mensaje);
}
