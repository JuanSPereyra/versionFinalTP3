package vista.pantalla;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import vista.interfaz.CargarJsonVista;

@SuppressWarnings("serial")
public class PantallaCargarDatosDesdeJSON extends VistaBaseCompartida implements CargarJsonVista {

	private JPanel panelFondo;
	private JLabel lblTitulo, lblRutaArchivo, lblImagen;
	private JButton btnVolver, btnIrResultado, btnSeleccionarArchivo;

	public PantallaCargarDatosDesdeJSON() {
		panelFondo = configurarPantalla("Carga con Documento");
		crearElementos();
	}

	private void crearElementos() {
		crearLblTitulo();
		crearBtnSeleccionarArchivo();
		crearLblRutaArchivo();
		crearBtnResultado();
		crearBtnVolverAMenu();
		crearImagenFondo();
	}

	private void crearLblTitulo() {
		lblTitulo = crearLblTitulo("CARGA DE DATOS DESDE JSON");
		lblTitulo.setBounds(100, 60, 700, 50);
		panelFondo.add(lblTitulo);
	}

	private void crearBtnSeleccionarArchivo() {
		btnSeleccionarArchivo = crearBtn("Seleccionar Archivo JSON");
		btnSeleccionarArchivo.setBackground(new Color(37, 99, 235));
		btnSeleccionarArchivo.setBounds(300, 160, 300, 50);
		
		panelFondo.add(btnSeleccionarArchivo);
	}

	private void crearLblRutaArchivo() {
		lblRutaArchivo = crearLblTitulo("Ningún archivo seleccionado");
		lblRutaArchivo.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		lblRutaArchivo.setBounds(100, 230, 700, 30);
		panelFondo.add(lblRutaArchivo);
	}

	private void crearBtnResultado() {
		btnIrResultado = crearBtn("Ver Resultado");
		btnIrResultado.setBackground(new Color(16, 185, 129));
		btnIrResultado.setBounds(300, 300, 300, 50);
		btnIrResultado.setEnabled(false);
		
		panelFondo.add(btnIrResultado);
	}

	private void crearBtnVolverAMenu() {
		btnVolver = crearBtn("Volver al Menú");
		btnVolver.setBackground(new Color(239, 68, 68));
		btnVolver.setBounds(300, 380, 300, 50);
		
		panelFondo.add(btnVolver);
	}
	
	private void crearImagenFondo() {
		lblImagen = cargarImagen();
		panelFondo.add(lblImagen);
	}

	@Override
	public void listenerSeleccionarArchivo(ActionListener e) {
		btnSeleccionarArchivo.addActionListener(e);
	}

	@Override
	public void listenerVolverAlMenu(ActionListener e) {
		btnVolver.addActionListener(e);
	}

	@Override
	public void listenerVerResultados(ActionListener e) {
		btnIrResultado.addActionListener(e);
	}

	@Override
	public void mostrarRutaArchivo(String ruta) {
		lblRutaArchivo.setText(ruta);
	}

	@Override
	public void habilitarBotonResultado(boolean b) {
		btnIrResultado.setEnabled(b);
	}

	@Override
	public void mostrarMensajeError(String m) {
		JOptionPane.showMessageDialog(this, m, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
