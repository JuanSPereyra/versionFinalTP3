package presentador;

import coordinadorMVP.CoordinadorPresentadores;
import estructuraDatosAux.Par;
import modelo.*;
import modelo.grafo.Arista;
import modelo.grafo.Vertice;
import vista.interfaz.CargarDatosVista;

public class CargarDatosPresentador {
	private CargarDatosVista _cdVista;
	private CoordinadorPresentadores coordinador;
	private Instancia instancia;

	public CargarDatosPresentador(CargarDatosVista cargarDatosVista, Instancia instancia,
			CoordinadorPresentadores coordinador) {
		this._cdVista = cargarDatosVista;
		this.coordinador = coordinador;
		this.instancia = instancia;

		iniciarListeners();
	}

	private void iniciarListeners() {
		listenerAgregarVertice();
		listenerAgregarArista();
		listenerEliminarVertice();
		listenerEliminarArista();
		listenerCalcularDominante();
		listenerVolverMenu();
	}

	private void listenerAgregarVertice() {
		this._cdVista.listenerAgregarVertice(e -> {
			String nombre = _cdVista.obtenerTextoVertice();
			manejarClickAgregarVertice(nombre);
		});
		;
	}

	private void listenerAgregarArista() {
		this._cdVista.listenerAgregarArista(e -> {
			Par<String, String> aristaNueva = this._cdVista.obtenerOrigenDestinoDeArista();
			manejarClickAgregarArista(aristaNueva);
		});
	}

	private void listenerEliminarVertice() {
		this._cdVista.listenerEliminarVertice(e -> {
			String nombre = _cdVista.obtenerVerticeAEliminar();
			manejarClickEliminarVertice(nombre);
		});
	}

	private void listenerEliminarArista() {
		this._cdVista.listenerEliminarArista(e -> {
			Par<String, String> aristaAEliminar = _cdVista.obtenerAristaAEliminar();
			String origen = aristaAEliminar.getIzquierda();
			String destino = aristaAEliminar.getDerecha();

			manejarClickEliminarArista(origen, destino);
		});
	}

	private void listenerCalcularDominante() {
		this._cdVista.listenerCalcularDominante(e -> manejarClickCalcularDominante());
	}

	private void listenerVolverMenu() {
		this._cdVista.listenerVolverAlMenu(e -> manejarClickVolverAlMenu());
	}

	private void manejarClickAgregarVertice(String nombre) {
		try {
			if (nombre == null || nombre.trim().isEmpty()) {
				_cdVista.mostrarMensajeError("El nombre del vértice no puede estar vacío.");
				return;
			}

			instancia.agregarVertice(nombre.trim());

			_cdVista.agregarVertice(nombre.trim());
			_cdVista.limpiarCampoVertice();
		} catch (Exception e) {
			_cdVista.mostrarMensajeError(e.getMessage());
		}
	}

	private void manejarClickAgregarArista(Par<String, String> aristaNueva) {
		try {
			String origen = aristaNueva.getIzquierda();
			String destino = aristaNueva.getDerecha();

			if (origen == null || destino == null || origen.equals(destino)) {
				_cdVista.mostrarMensajeError("Debe seleccionar origen y destino válidos (y distintos)");
				return;
			}

			instancia.agregarArista(origen, destino);

			_cdVista.agregarArista(origen, destino, "");
		} catch (Exception e) {
			_cdVista.mostrarMensajeError(e.getMessage());
		}
	}

	private void manejarClickEliminarArista(String origen, String destino) {
		instancia.eliminarArista(instancia.getVertice(origen), instancia.getVertice(destino));
		_cdVista.eliminarArista(origen, destino);
	}

	private void manejarClickEliminarVertice(String nombre) {
		Vertice vertice = instancia.getVertice(nombre);

		for (Arista arista : instancia.obtenerAristasDe(vertice)) {
			_cdVista.eliminarArista(arista.getOrigen().getNombre(), arista.getDestino().getNombre());
		}

		instancia.eliminarVertice(vertice);
		_cdVista.eliminarVertice(nombre);
	}

	private void manejarClickCalcularDominante() {
		try {
			if (instancia.getVertices().isEmpty()) {
				_cdVista.mostrarMensajeError("Debe agregar al menos un vértice para calcular el conjunto dominante");
				return;
			}

			coordinador.irAResultado();

		} catch (Exception e) {
			_cdVista.mostrarMensajeError(e.getMessage());
		}
	}

	private void manejarClickVolverAlMenu() {
		coordinador.iniciarAplicacion();
	}

	public void cargarDatosDesdeModelo() {

		if (instancia.getVertices() != null) {

			for (Vertice v : instancia.getVertices()) {
				_cdVista.agregarVertice(v.getNombre());
			}
		}

		if (instancia.getAristasComoNombres() != null) {
			for (Par<String, String> arista : instancia.getAristasComoNombres()) {
				String origen = arista.getIzquierda();
				String destino = arista.getDerecha();
				_cdVista.agregarArista(origen, destino, "");
			}
		}
	}
}
