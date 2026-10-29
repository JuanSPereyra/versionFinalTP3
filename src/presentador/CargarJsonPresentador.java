package presentador;

import coordinadorMVP.CoordinadorPresentadores;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import datos.CargadorJson;
import estructuraDatosAux.AristaJson;
import estructuraDatosAux.GrafoJson;
import modelo.Instancia;
import vista.interfaz.CargarJsonVista;

public class CargarJsonPresentador {
	private CargarJsonVista _vista;
	private CoordinadorPresentadores coordinador;
	private Instancia instancia;
	private GrafoJson datosGrafo;

	public CargarJsonPresentador(CargarJsonVista vista, Instancia instancia, CoordinadorPresentadores coordinador) {
		this._vista = vista;
		this.instancia = instancia;
		this.coordinador = coordinador;

		iniciarListeners();
	}

	private void iniciarListeners() {
		listenerSeleccionarArchivo();
		listenerVolverMenu();
		listenerVerResultado();
	}

	private void listenerSeleccionarArchivo() {
		_vista.listenerSeleccionarArchivo(e -> abrirExploradorArchivos());
	}

	private void listenerVolverMenu() {
		_vista.listenerVolverAlMenu(e -> coordinador.iniciarAplicacion());
	}

	private void listenerVerResultado() {
		_vista.listenerVerResultados(e -> coordinador.irAResultado());
	}

	private void abrirExploradorArchivos() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Seleccionar grafo en formato JSON");

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos JSON (*.json)", "json");
		fileChooser.setFileFilter(filtro);
		fileChooser.setAcceptAllFileFilterUsed(false);

		int seleccion = fileChooser.showOpenDialog(null);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = fileChooser.getSelectedFile();
			_vista.mostrarRutaArchivo(archivoSeleccionado.getName());

			try {
				this.datosGrafo = CargadorJson.cargarGrafo(archivoSeleccionado.getAbsolutePath());

				transformarGrafoJsonAInstancia();
				_vista.habilitarBotonResultado(true);

			} catch (Exception e) {
				_vista.mostrarMensajeError("Error al cargar la estructura del grafo.");
				_vista.habilitarBotonResultado(false);
				_vista.mostrarRutaArchivo("Ningún archivo seleccionado");
			}
		}
	}

	private void transformarGrafoJsonAInstancia() {
		cargarVertices();
		cargarAristas();
	}

	private void cargarAristas() {
		if (datosGrafo.getAristas() != null) {
			for (AristaJson arista : datosGrafo.getAristas()) {
				instancia.agregarArista(arista.getOrigen().trim(), arista.getDestino().trim());
			}
		}
	}

	private void cargarVertices() {
		if (datosGrafo.getVertices() != null) {
			for (String v : datosGrafo.getVertices()) {
				instancia.agregarVertice(v.trim());
			}
		}
	}
}
