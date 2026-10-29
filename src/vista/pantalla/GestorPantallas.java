package vista.pantalla;

import javax.swing.JFrame;

import vista.interfaz.InterfazGestorPantalla;

public class GestorPantallas implements InterfazGestorPantalla {

	private JFrame pantallaActual;

	@Override
	public void mostrarPantalla(Object pantalla) {
		JFrame nuevaVentana = (JFrame) pantalla;

		if (pantallaActual != null) {
			pantallaActual.setVisible(false);
		}

		pantallaActual = nuevaVentana;
		nuevaVentana.setVisible(true);
	}
}