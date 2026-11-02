package vista.pantalla;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import estructuraDatosAux.Par;
import vista.interfaz.StressVista;

@SuppressWarnings("serial")
public class PantallaTestStress extends VistaBaseCompartida implements StressVista {

	private JButton btnDetenerTest, btnSalir, btnVolver, btnEmpezarTest;
	private Container panelFondo;
	private JComboBox<Object> comboDensidades;
	private JLabel lblTitulo, lblElegirDensidad, lblCantidadVertices, lblImagen;
	private JTextArea txtResultado;
	private JProgressBar barraProgreso;
	private JTextField textCanVertices;
	private DefaultListCellRenderer renderer;
	private JScrollPane areaResultado;

	public PantallaTestStress() {

		panelFondo = configurarPantalla("Conjunto Dominante Mínimo — Resultado");
		crearElementos();
	}

	private void crearElementos() {
		crearLblTitulo();
		crearLblElegirDensidad();
		crearLblCantidadVertices();
		crearTxtIngreseCantidadVertices();
		crearAreaResultado();
		crearBarraProgreso();
		crearBtnVolver();
		crearBtnSalir();
		crearBtnEmpezarTest();
		crearComboSoluciones();
		crearBtnDetenerTest();
		crearImagenFondo();
	}

	private void crearLblTitulo() {
		lblTitulo = crearLblTitulo("TEST DE STRESS");
		lblTitulo.setBounds(100, 30, 700, 40);
		panelFondo.add(lblTitulo);
	}

	private void crearLblElegirDensidad() {
		lblElegirDensidad = new JLabel("Elegir Densidad", SwingConstants.CENTER);
		lblElegirDensidad.setForeground(new Color(248, 250, 252));
		lblElegirDensidad.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblElegirDensidad.setBounds(334, 354, 219, 40);
		panelFondo.add(lblElegirDensidad);
	}

	private void crearLblCantidadVertices() {
		lblCantidadVertices = new JLabel("Cantidad \r\nVertices", SwingConstants.CENTER);
		lblCantidadVertices.setForeground(new Color(248, 250, 252));
		lblCantidadVertices.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblCantidadVertices.setBounds(100, 354, 219, 40);
		panelFondo.add(lblCantidadVertices);
	}

	private void crearTxtIngreseCantidadVertices() {
		textCanVertices = new JTextField();
		textCanVertices.setHorizontalAlignment(JTextField.CENTER);
		textCanVertices.setFont(new Font("Tahoma", Font.BOLD, 15));
		textCanVertices.setBounds(150, 405, 145, 32);
		textCanVertices.setColumns(10);
		panelFondo.add(textCanVertices);
	}

	private void crearAreaResultado() {
		txtResultado = crearTxtResultado();
		
		areaResultado = crearScrollResultados(txtResultado);
		panelFondo.add(areaResultado);
	}

	private void crearBarraProgreso() {
		barraProgreso = new JProgressBar(0, 100);
		barraProgreso.setStringPainted(true);
		barraProgreso.setForeground(new Color(0, 128, 128));
		barraProgreso.setBackground(new Color(15, 23, 42));
		barraProgreso.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
		barraProgreso.setBounds(73, 453, 742, 32);
		panelFondo.add(barraProgreso);
	}

	private void crearBtnVolver() {
		btnVolver = crearBtn("Volver al Menú");
		btnVolver.setBackground(new Color(37, 99, 235));
		btnVolver.setBounds(150, 496, 280, 45);

		panelFondo.add(btnVolver);
	}

	private void crearBtnSalir() {
		btnSalir = crearBtn("Salir");
		btnSalir.setBackground(new Color(239, 68, 68));
		btnSalir.setBounds(470, 496, 280, 45);

		panelFondo.add(btnSalir);
	}

	private void crearBtnEmpezarTest() {
		btnEmpezarTest = crearBtn("Comenzar Test");
		btnEmpezarTest.setBackground(new Color(0, 128, 128));
		btnEmpezarTest.setBounds(587, 354, 193, 40);

		panelFondo.add(btnEmpezarTest);
	}

	private void crearComboSoluciones() {
		comboDensidades = new JComboBox<>();
		comboDensidades.setModel(new DefaultComboBoxModel<>(
				new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));

		renderer = new DefaultListCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		comboDensidades.setRenderer(renderer);
		comboDensidades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboDensidades.setBackground(new Color(128, 0, 128));
		comboDensidades.setFont(new Font("Verdana", Font.BOLD, 15));
		comboDensidades.setSize(145, 32);
		comboDensidades.setLocation(377, 405);
		comboDensidades.setForeground(new Color(240, 248, 255));

		getContentPane().add(comboDensidades, BorderLayout.CENTER);
	}

	private void crearBtnDetenerTest() {
		btnDetenerTest = crearBtn("Detener Test");
		btnDetenerTest.setBackground(new Color(255, 128, 64));
		btnDetenerTest.setBounds(587, 405, 193, 40);
		btnDetenerTest.setEnabled(false);

		panelFondo.add(btnDetenerTest);
	}
	
	private void crearImagenFondo() {
		lblImagen = cargarImagen();
		panelFondo.add(lblImagen);
	}

	@Override
	public void listenerVolverAlMenu(ActionListener e) {
		btnVolver.addActionListener(e);
	}

	@Override
	public void listenerSalir(ActionListener e) {
		btnSalir.addActionListener(e);
	}

	@Override
	public void listenerEmpezarTest(ActionListener e) {
		btnEmpezarTest.addActionListener(e);
	}

	@Override
	public void listenerDetenerTest(ActionListener e) {
		btnDetenerTest.addActionListener(e);
	}

	@Override
	public Par<String, Integer> datosParaTest() {
		String cantVertices = textCanVertices.getText();
		int porcentajeDensidad = comboDensidades.getSelectedIndex() + 1;

		return new Par<String, Integer>(cantVertices, porcentajeDensidad);
	}

	@Override
	public void borrarResultadoAnterior() {
		txtResultado.setText("");
		barraProgreso.setValue(0);
	}

	@Override
	public void habilitarBotonesTestStress(boolean corriendo) {
		comboDensidades.setEnabled(!corriendo);
		btnEmpezarTest.setEnabled(!corriendo);
		btnDetenerTest.setEnabled(corriendo);
		btnVolver.setEnabled(!corriendo);
		btnSalir.setEnabled(!corriendo);
		textCanVertices.setEnabled(!corriendo);
	}

	@Override
	public void mostrarResultado(String texto) {
		txtResultado.append(texto + "\n");
	}

	@Override
	public void actualizarProgreso(int porcentaje) {
		barraProgreso.setValue(porcentaje);
		barraProgreso.setStringPainted(true);
		barraProgreso.setForeground(new Color(125, 15, 156));
		barraProgreso.setFont(new Font("Verdana", Font.BOLD, 16));
		barraProgreso.setStringPainted(true);
		barraProgreso.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
