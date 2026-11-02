package vista.pantalla;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import estructuraDatosAux.Par;
import vista.interfaz.CargarDatosVista;

@SuppressWarnings("serial")
public class PantallaCargarDatosManual extends VistaBaseCompartida implements CargarDatosVista {

	private String verticeAEliminarTemp, aristaOrigenTemp, aristaDestinoTemp;

	private JPanel panelFondo;
	private JButton btnVolver, btnIrResultado, btnAgregarVertice, btnAgregarArista;
	private JLabel lblTitulo, lblNombreVertice, lblOrigen, lblDestino, lblImagen;
	private JTextField textNombreVertice;
	private JTable tablaVertices, tablaAristas;
	private JComboBox<String> comboVerticeUno, comboVerticeDos;
	private DefaultTableModel modeloVertices, modeloAristas;

	public PantallaCargarDatosManual() {
		
		panelFondo = configurarPantalla("Carga Manual");
		crearTitulo();
		crearSeccionVertices();
		crearSeccionAristas();
		crearElementos();
		crearImagenFondo();
	}

	private void crearTitulo() {
		lblTitulo = crearLblTitulo("CARGA MANUAL DE DATOS");
		panelFondo.add(lblTitulo);

		estiloEtiqueta(lblTitulo);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setBounds(50, 20, 800, 35);
		panelFondo.add(lblTitulo);
	}

	private void crearSeccionVertices() {
		lblNombreVertice = new JLabel("NOMBRE DEL VÉRTICE:");
		estiloEtiqueta(lblNombreVertice);
		lblNombreVertice.setBounds(40, 80, 360, 22);
		panelFondo.add(lblNombreVertice);

		crearTextNombreVertice();
		crearBtnAgregarVertice();
		crearTablaVertices();
	}

	private void crearTextNombreVertice() {
		textNombreVertice = new JTextField();
		textNombreVertice.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textNombreVertice.setForeground(new Color(15, 23, 42));
		textNombreVertice.setBackground(new Color(241, 245, 249));
		textNombreVertice.setBounds(40, 108, 220, 34);
		panelFondo.add(textNombreVertice);
	}

	private void crearBtnAgregarVertice() {
		btnAgregarVertice = crearBtn("Agregar vértice");
		btnAgregarVertice.setBounds(270, 108, 130, 34);	
		btnAgregarVertice.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAgregarVertice.setBackground(new Color(37, 99, 235));

		panelFondo.add(btnAgregarVertice);
	}

	private void crearTablaVertices() {
		modeloVertices = crearModeloNoEditable(new String[] { "Vértice", "" });
		tablaVertices = new JTable(modeloVertices);
		estiloTabla(tablaVertices);
		configurarColumnaEliminar(tablaVertices);

		panelFondo.add(crearScroll(tablaVertices, 40, 160, 360, 290));
	}

	private void crearSeccionAristas() {
		crearSeccionOrigen();
		crearSeccionDestino();
		crearBtnAgregarArista();
		crearTablaAristas();
	}

	private void crearSeccionOrigen() {
		lblOrigen = new JLabel("ORIGEN:");
		estiloEtiqueta(lblOrigen);
		lblOrigen.setBounds(460, 80, 180, 22);
		panelFondo.add(lblOrigen);

		comboVerticeUno = new JComboBox<String>(new DefaultComboBoxModel<String>());
		estiloCombo(comboVerticeUno);
		comboVerticeUno.setBounds(460, 108, 180, 34);
		panelFondo.add(comboVerticeUno);
	}

	private void crearSeccionDestino() {
		lblDestino = new JLabel("DESTINO:");
		estiloEtiqueta(lblDestino);
		lblDestino.setBounds(660, 80, 180, 22);
		panelFondo.add(lblDestino);

		comboVerticeDos = new JComboBox<String>(new DefaultComboBoxModel<String>());
		estiloCombo(comboVerticeDos);
		comboVerticeDos.setBounds(660, 108, 180, 34);
		panelFondo.add(comboVerticeDos);
	}

	private void crearBtnAgregarArista() {
		btnAgregarArista = crearBtn("Agregar arista");
		btnAgregarArista.setBounds(460, 160, 380, 34);
		btnAgregarArista.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAgregarArista.setBackground(new Color(37, 99, 235));

		panelFondo.add(btnAgregarArista);
	}

	private void crearTablaAristas() {
		modeloAristas = crearModeloNoEditable(new String[] { "Vertice Uno", "Vertice Dos", "" });
		tablaAristas = new JTable(modeloAristas);
		estiloTabla(tablaAristas);
		configurarColumnaEliminar(tablaAristas);

		panelFondo.add(crearScroll(tablaAristas, 460, 210, 380, 240));
	}

	private void crearElementos() {
		crearBtnResultado();
		crearBtnVolverMenu();
	}

	private void crearBtnResultado() {
		btnIrResultado = crearBtn("Ver Resultado");
		btnIrResultado.setBackground(new Color(16, 185, 129));
		btnIrResultado.setBounds(660, 470, 180, 42);

		panelFondo.add(btnIrResultado);
	}

	private void crearBtnVolverMenu() {
		btnVolver = crearBtn("Volver al Menú");
		btnVolver.setBackground(new Color(239, 68, 68));
		btnVolver.setBounds(470, 470, 170, 42);
		
		panelFondo.add(btnVolver);
	}

	private JScrollPane crearScroll(JTable tabla, int x, int y, int ancho, int alto) {
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.getViewport().setBackground(new Color(15, 23, 42));
		scroll.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
		scroll.setBounds(x, y, ancho, alto);
		return scroll;
	}

	private DefaultTableModel crearModeloNoEditable(String[] columnas) {
		return new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
	}

	private void estiloTabla(JTable tabla) {
		tabla.setShowVerticalLines(false);
		tabla.setGridColor(new Color(51, 65, 85));
		tabla.setBackground(new Color(15, 23, 42));
		tabla.setForeground(new Color(241, 245, 249));
		tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tabla.setRowHeight(28);
		tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		tabla.getTableHeader().setBackground(new Color(51, 65, 85));
		tabla.getTableHeader().setForeground(new Color(248, 250, 252));
	}

	private void configurarColumnaEliminar(JTable tabla) {
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		render.setBackground(new Color(15, 23, 42));
		render.setForeground(new Color(239, 68, 68));
		render.setFont(new Font("Segoe UI", Font.BOLD, 15));

		int ultima = tabla.getColumnCount() - 1;
		tabla.getColumnModel().getColumn(ultima).setCellRenderer(render);
		tabla.getColumnModel().getColumn(ultima).setMinWidth(45);
		tabla.getColumnModel().getColumn(ultima).setMaxWidth(45);
	}

	private void estiloEtiqueta(JLabel label) {
		label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		label.setForeground(new Color(203, 213, 225));
	}

	private void estiloCombo(JComboBox<String> combo) {
		combo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		combo.setForeground(new Color(15, 23, 42));
		combo.setBackground(new Color(241, 245, 249));
	}
	
	private void crearImagenFondo() {
		lblImagen = cargarImagen();
		panelFondo.add(lblImagen);
	}

	@Override
	public void listenerAgregarVertice(ActionListener e) {
		btnAgregarVertice.addActionListener(e);
	}

	@Override
	public void listenerAgregarArista(ActionListener e) {
		btnAgregarArista.addActionListener(e);
	}

	@Override
	public void listenerEliminarVertice(ActionListener listener) {
		tablaVertices.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaVertices.rowAtPoint(e.getPoint());
				int columna = tablaVertices.columnAtPoint(e.getPoint());
				if (fila >= 0 && columna == tablaVertices.getColumnCount() - 1) {

					verticeAEliminarTemp = (String) modeloVertices.getValueAt(fila, 0);
					listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "eliminar"));
				}
			}
		});
	}

	@Override
	public void listenerEliminarArista(ActionListener listener) {
		tablaAristas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaAristas.rowAtPoint(e.getPoint());
				int columna = tablaAristas.columnAtPoint(e.getPoint());
				if (fila >= 0 && columna == tablaAristas.getColumnCount() - 1) {
					aristaOrigenTemp = (String) modeloAristas.getValueAt(fila, 0);
					aristaDestinoTemp = (String) modeloAristas.getValueAt(fila, 1);

					listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "eliminar"));
				}
			}
		});
	}

	@Override
	public void listenerCalcularDominante(ActionListener e) {
		btnIrResultado.addActionListener(e);
	}

	@Override
	public void listenerVolverAlMenu(ActionListener e) {
		btnVolver.addActionListener(e);
	}

	@Override
	public String obtenerTextoVertice() {
		return textNombreVertice.getText();
	}

	@Override
	public Par<String, String> obtenerOrigenDestinoDeArista() {
		String origen = (String) comboVerticeUno.getSelectedItem();
		String destino = (String) comboVerticeDos.getSelectedItem();

		Par<String, String> aristaNueva = new Par<String, String>(origen, destino);
		return aristaNueva;
	}

	@Override
	public String obtenerVerticeAEliminar() {
		return verticeAEliminarTemp;
	}

	@Override
	public Par<String, String> obtenerAristaAEliminar() {
		return new Par<String, String>(aristaOrigenTemp, aristaDestinoTemp);
	}

	@Override
	public void agregarVertice(String nombre) {
		modeloVertices.addRow(new Object[] { nombre, "X" });
		comboVerticeUno.addItem(nombre);
		comboVerticeDos.addItem(nombre);
	}

	@Override
	public void agregarArista(String origen, String destino, String peso) {
		modeloAristas.addRow(new Object[] { origen, destino, "X" });
	}

	@Override
	public void eliminarVertice(String nombre) {
		for (int i = 0; i < modeloVertices.getRowCount(); i++) {
			if (modeloVertices.getValueAt(i, 0).equals(nombre)) {
				modeloVertices.removeRow(i);
				break;
			}
		}
		comboVerticeUno.removeItem(nombre);
		comboVerticeDos.removeItem(nombre);
	}

	@Override
	public void eliminarArista(String origen, String destino) {
		for (int i = 0; i < modeloAristas.getRowCount(); i++) {
			String orig = (String) modeloAristas.getValueAt(i, 0);
			String dest = (String) modeloAristas.getValueAt(i, 1);
			if ((orig.equals(origen) && dest.equals(destino)) || (orig.equals(destino) && dest.equals(origen))) {
				modeloAristas.removeRow(i);
				break;
			}
		}
	}

	@Override
	public void limpiarCampoVertice() {
		textNombreVertice.setText("");
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
