package vista.pantalla;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;

import estructuraDatosAux.Par;
import estructuraDatosAux.VerticeVisual;
import vista.graficador.LibreriaMxGraphWrapper;
import vista.interfaz.ResultadoVista;

@SuppressWarnings("serial")
public class PantallaResultado extends VistaBaseCompartida implements ResultadoVista {

	private int solucionElegida;

	private LibreriaMxGraphWrapper graficadorDeGrafo;

	private JPanel panelFondo, panelGrafo;
	private JLabel lblTitulo, lblImagen;
	private JTextArea txtResultado;
	private JButton btnVolver, btnSalir, btnModificarDatos;
	private JComboBox<Object> comboSoluciones;
	private JComponent componenteDibujoDeGrafo;
	private JScrollPane areaResultado;

	private HashMap<String, Object> mapaDeVertices;

	public PantallaResultado() {

		panelFondo = configurarPantalla("Conjunto Dominante Mínimo — Resultado");
		crearElementos();
	}

	private void crearElementos() {
		crearLblTitulo();
		crearAreaResultado();
		crearAreaMostrarGrafo();
		crearComboSoluciones();
		crearBtnVolver();
		crearBtnSalir();
		crearBtnModificarDatos();
		crearImagenFondo();
	}

	private void crearLblTitulo() {
		lblTitulo = crearLblTitulo("RESULTADO DEL CONJUNTO DOMINANTE");
		lblTitulo.setBounds(100, 30, 700, 40);
		panelFondo.add(lblTitulo);
	}

	private void crearAreaResultado() {
		txtResultado = crearTxtResultado();
		
		areaResultado = crearScrollResultados(txtResultado);
		areaResultado.setBounds(39, 90, 411, 343);
		
		panelFondo.add(areaResultado);
	}

	private void crearAreaMostrarGrafo() {
		panelGrafo = new JPanel();
		panelGrafo.setBounds(460, 90, 395, 343);
		panelGrafo.setBackground(new Color(15, 23, 42));
		panelGrafo.setForeground(new Color(241, 245, 249));
		panelFondo.add(panelGrafo);
	}

	private void crearComboSoluciones() {
		comboSoluciones = new JComboBox<>();
		comboSoluciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboSoluciones.setBackground(new Color(128, 0, 128));
		comboSoluciones.setFont(new Font("Verdana", Font.BOLD, 14));
		comboSoluciones.setSize(330, 32);
		comboSoluciones.setLocation(470, 444);
		comboSoluciones.setForeground(new Color(240, 248, 255));

		getContentPane().add(comboSoluciones, BorderLayout.SOUTH);
	}

	private void crearBtnVolver() {
		btnVolver = crearBtn("Volver al Menú");
		btnVolver.setBackground(new Color(37, 99, 235));
		btnVolver.setBounds(150, 487, 280, 45);

		panelFondo.add(btnVolver);
	}

	private void crearBtnSalir() {
		btnSalir = crearBtn("Salir");
		btnSalir.setBackground(new Color(239, 68, 68));
		btnSalir.setBounds(470, 487, 280, 45);

		panelFondo.add(btnSalir);
	}

	private void crearBtnModificarDatos() {
		btnModificarDatos = crearBtn("Modificar datos");
		btnModificarDatos.setBackground(new Color(255, 128, 64));
		btnModificarDatos.setBounds(100, 444, 330, 32);

		panelFondo.add(btnModificarDatos);
	}
	
	private void crearImagenFondo() {
		lblImagen = cargarImagen();
		panelFondo.add(lblImagen);
	}

	@Override
	public void listenerMostrarSolucion(ActionListener e) {
		comboSoluciones.addActionListener(e);
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
	public void listenerModificarDatos(ActionListener e) {
		btnModificarDatos.addActionListener(e);
	}

	@Override
	public int solucionElegida() {
		solucionElegida = comboSoluciones.getSelectedIndex();
		return solucionElegida;
	}

	@Override
	public void mostrarResultado(String texto) {
		txtResultado.setText(texto);
		txtResultado.setCaretPosition(0);
	}

	@Override
	public void completarComboSoluciones(List<String> nombreHeuristica) {
		comboSoluciones.removeAllItems();

		for (String nombre : nombreHeuristica)
			comboSoluciones.addItem(nombre);
	}

	@Override
	public void mostrarGrafo(List<VerticeVisual> vertices, List<Par<String, String>> aristas) {

		graficadorDeGrafo = new LibreriaMxGraphWrapper();
		mapaDeVertices = new HashMap<>();

		try {
			graficadorDeGrafo.actualizar();
			agregarVerticesYAristasAlGrafico(vertices, aristas);

		} finally {
			graficadorDeGrafo.finActualizar();
		}

		configuracionFinal();
	}

	private void agregarVerticesYAristasAlGrafico(List<VerticeVisual> vertices, List<Par<String, String>> aristas) {
		for (VerticeVisual vertice : vertices) {
			agregarVerticeAlGrafico(vertice);
		}

		for (Par<String, String> arista : aristas) {
			agregarAristaAlGrafico(arista);
		}
	}

	private void agregarVerticeAlGrafico(VerticeVisual vertice) {
		String estiloVertice = vertice.esDominante() ? graficadorDeGrafo.estiloVerticeDominante()
				: graficadorDeGrafo.estiloVerticeNoDominante();

		String nombreV = vertice.getNombre();

		Object nodo = graficadorDeGrafo.insertarVertice(nombreV, estiloVertice);
		mapaDeVertices.put(nombreV, nodo);
	}

	private void agregarAristaAlGrafico(Par<String, String> arista) {
		Object objetoUno = mapaDeVertices.get(arista.getObjetoUno());
		Object objetoDos = mapaDeVertices.get(arista.getObjetoDos());
		
		graficadorDeGrafo.insertarArista(objetoUno, objetoDos);
	}

	private void configuracionFinal() {
		graficadorDeGrafo.configurarGrafico();
		graficadorDeGrafo.aplicarLayoutCircular();
		componenteDibujoDeGrafo = graficadorDeGrafo.crearComponente();

		panelGrafo.removeAll();
		panelGrafo.setLayout(new BorderLayout());
		panelGrafo.add(componenteDibujoDeGrafo, BorderLayout.CENTER);
		panelGrafo.revalidate();
		panelGrafo.repaint();
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
