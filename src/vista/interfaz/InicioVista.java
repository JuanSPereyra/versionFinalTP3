package vista.interfaz;

import java.awt.event.ActionListener;

public interface InicioVista {

	void listenerCargaManual(ActionListener listener);

	void listenerCargaDocumento(ActionListener listener);

	void listenerTestStress(ActionListener listener);

	void listenerSalir(ActionListener listener);
}
