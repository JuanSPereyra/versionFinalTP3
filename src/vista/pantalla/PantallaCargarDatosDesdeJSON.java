package vista.pantalla;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import vista.interfaz.CargarJsonVista;

@SuppressWarnings("serial")
public class PantallaCargarDatosDesdeJSON extends JFrame implements CargarJsonVista {

	private JPanel panelFondo;
	private JLabel lblTitulo, lblRutaArchivo;
	private JButton btnVolver, btnIrResultado, btnSeleccionarArchivo;

	public PantallaCargarDatosDesdeJSON() {
		configurarPantalla();
		crearElementos();
	}

	private void configurarPantalla() {
		setTitle("Carga con Documento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		panelFondo = new JPanel();
		panelFondo.setBackground(new Color(30, 41, 59));
		setContentPane(panelFondo);
		panelFondo.setLayout(null);
	}

	private void crearElementos() {
		crearLblTitulo();

		crearBtnSeleccionarArchivo();
		panelFondo.add(btnSeleccionarArchivo);

		crearLblRutaArchivo();

		crearBtnResultado();
		panelFondo.add(btnIrResultado);

		crearBtnVolverAMenu();
		panelFondo.add(btnVolver);
	}

	private void crearLblTitulo() {
		lblTitulo = new JLabel("CARGA DE DATOS DESDE JSON", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(100, 60, 700, 50);
		panelFondo.add(lblTitulo);
	}

	private void crearBtnSeleccionarArchivo() {
		btnSeleccionarArchivo = new JButton("Seleccionar Archivo JSON");
		btnSeleccionarArchivo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSeleccionarArchivo.setForeground(Color.WHITE);
		btnSeleccionarArchivo.setBackground(new Color(37, 99, 235));
		btnSeleccionarArchivo.setFocusPainted(false);
		btnSeleccionarArchivo.setBorderPainted(false);
		btnSeleccionarArchivo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSeleccionarArchivo.setBounds(300, 160, 300, 50);
	}

	private void crearLblRutaArchivo() {
		lblRutaArchivo = new JLabel("Ningún archivo seleccionado", SwingConstants.CENTER);
		lblRutaArchivo.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblRutaArchivo.setForeground(new Color(203, 213, 225));
		lblRutaArchivo.setBounds(100, 230, 700, 30);
		panelFondo.add(lblRutaArchivo);
	}

	private void crearBtnResultado() {
		btnIrResultado = new JButton("Ver Resultado");
		btnIrResultado.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnIrResultado.setForeground(Color.WHITE);
		btnIrResultado.setBackground(new Color(16, 185, 129));
		btnIrResultado.setFocusPainted(false);
		btnIrResultado.setBorderPainted(false);
		btnIrResultado.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIrResultado.setBounds(300, 300, 300, 50);
		btnIrResultado.setEnabled(false);
	}

	private void crearBtnVolverAMenu() {
		btnVolver = new JButton("Volver al Menú");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(239, 68, 68));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(300, 380, 300, 50);
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
