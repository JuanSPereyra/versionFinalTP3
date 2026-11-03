package presentador;

import coordinadorMVP.CoordinadorPresentadores;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import cargadorJson.CargadorJson;
import estructuraDatosAux.AristaJson;
import estructuraDatosAux.GrafoJson;
import modelo.Instancia;
import vista.interfaz.CargarJsonVista;

public class CargarJsonPresentador {
	private CargarJsonVista _vista;
	private CoordinadorPresentadores coordinador;
	private Instancia instancia;
	private GrafoJson datosGrafo;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter filtro;
	private File archivoSeleccionado;

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

		configurarFileChooser();

		int seleccion = fileChooser.showOpenDialog(null);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			archivoSeleccionado = fileChooser.getSelectedFile();
			_vista.mostrarRutaArchivo(archivoSeleccionado.getName());

			try {
				cargarDatosAInstancia();

			} catch (Exception e) {
				manejarExcepcion();
			}
		}
	}

	private void configurarFileChooser() {
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Seleccionar grafo en formato JSON");

		filtro = new FileNameExtensionFilter("Archivos JSON (*.json)", "json");
		fileChooser.setFileFilter(filtro);
		fileChooser.setAcceptAllFileFilterUsed(false);
	}

	private void cargarDatosAInstancia() {
		datosGrafo = CargadorJson.cargarGrafo(archivoSeleccionado.getAbsolutePath());
		instancia.limpiar();
		transformarGrafoJsonAInstancia();
		_vista.habilitarBotonResultado(true);
	}

	private void transformarGrafoJsonAInstancia() {
		cargarVertices();
		cargarAristas();
	}

	private void cargarAristas() {
		if (datosGrafo.getAristas() != null) {
			for (AristaJson arista : datosGrafo.getAristas()) {
				instancia.agregarArista(arista.verticeUno().trim(), arista.verticeDos().trim());
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
	
	private void manejarExcepcion() {
		_vista.mostrarMensajeError("Error al cargar la estructura del grafo.");
		_vista.habilitarBotonResultado(false);
		_vista.mostrarRutaArchivo("Ningún archivo seleccionado");
	}
}
