package presentador;

import coordinadorMVP.CoordinadorPresentadores;
import vista.interfaz.InicioVista;

public class BienvenidaPresentador {

	private InicioVista _iVista;
	private CoordinadorPresentadores coordinador;

	public BienvenidaPresentador(InicioVista inicioVista, CoordinadorPresentadores coordinador) {

		this._iVista = inicioVista;
		this.coordinador = coordinador;

		iniciarListeners();
	}

	private void iniciarListeners() {

		listenerCargaManual();
		listenerCargaJSON();
		listenerTestStress();
		listenerSalir();
	}

	private void listenerTestStress() {
		_iVista.listenerTestStress(e -> coordinador.irATestStress());
	}

	private void listenerSalir() {
		_iVista.listenerSalir(e -> System.exit(0));
	}

	private void listenerCargaJSON() {
		_iVista.listenerCargaDocumento(e -> coordinador.irACargaDesdeJSON());
	}

	private void listenerCargaManual() {
		_iVista.listenerCargaManual(e -> coordinador.irACargaManual());
	}
}
