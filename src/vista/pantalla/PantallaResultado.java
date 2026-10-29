package vista.pantalla;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;

import estructuraDatosAux.Par;
import estructuraDatosAux.VerticeVisual;

import vista.interfaz.ResultadoVista;
import vista.grafico.LibreriaMxGraphWrapper;

@SuppressWarnings("serial")
public class PantallaResultado extends JFrame implements ResultadoVista {

	private int solucionElegida;

	private LibreriaMxGraphWrapper graficadorDeGrafo;

	private JPanel panelFondo, panelGrafo;
	private JLabel lblTitulo;
	private JTextArea txtResultado;
	private JButton btnVolver, btnSalir, btnModificarDatos;
	private JComboBox<Object> comboSoluciones;
	private JComponent componenteDibujoDeGrafo;
	private JScrollPane scrollPane;

	public PantallaResultado() {

		configurarPantalla();
		crearLblTitulo();
		crearAreaResultado();
		crearAreaMostrarGrafo();
		crearComboSoluciones();
		crearBtnVolver();
		crearBtnSalir();
		crearBtnModificarDatos();
	}

	private void configurarPantalla() {
		setTitle("Conjunto Dominante Mínimo — Resultado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		panelFondo = new JPanel();
		panelFondo.setBackground(new Color(30, 41, 59));
		setContentPane(panelFondo);
		panelFondo.setLayout(null);
	}

	private void crearLblTitulo() {
		lblTitulo = new JLabel("RESULTADO DEL CONJUNTO DOMINANTE", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(248, 250, 252));
		lblTitulo.setBounds(100, 30, 700, 40);
		panelFondo.add(lblTitulo);
	}

	private void crearAreaResultado() {
		txtResultado = new JTextArea();
		txtResultado.setFont(new Font("Consolas", Font.PLAIN, 15));
		txtResultado.setBackground(new Color(15, 23, 42));
		txtResultado.setForeground(new Color(241, 245, 249));
		txtResultado.setEditable(false);
		txtResultado.setMargin(new Insets(15, 15, 15, 15));

		crearScrollResultados();
	}

	private void crearScrollResultados() {
		scrollPane = new JScrollPane(txtResultado);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
		scrollPane.setBounds(39, 90, 411, 343);
		panelFondo.add(scrollPane);
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
		btnVolver = new JButton("Volver al Menú");
		btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(37, 99, 235));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVolver.setBounds(150, 487, 280, 45);

		panelFondo.add(btnVolver);
	}

	private void crearBtnSalir() {
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(239, 68, 68));
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSalir.setBounds(470, 487, 280, 45);

		panelFondo.add(btnSalir);
	}

	private void crearBtnModificarDatos() {
		btnModificarDatos = new JButton("Modificar datos");
		btnModificarDatos.setOpaque(true);
		btnModificarDatos.setForeground(Color.WHITE);
		btnModificarDatos.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnModificarDatos.setFocusPainted(false);
		btnModificarDatos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModificarDatos.setBorderPainted(false);
		btnModificarDatos.setBackground(new Color(255, 128, 64));
		btnModificarDatos.setBounds(100, 444, 330, 32);

		panelFondo.add(btnModificarDatos);
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
		Object parent = graficadorDeGrafo.getParent();
		Map<String, Object> mapaDeVertices = new HashMap<>();

		try {

			graficadorDeGrafo.actualizar();

			for (VerticeVisual vertice : vertices) {

				String estiloVertice = vertice.esDominante() ? graficadorDeGrafo.estiloVerticeDominante()
						: graficadorDeGrafo.estiloVerticeNoDominante();

				String nombreV = vertice.getNombre();

				Object nodo = graficadorDeGrafo.insertarVertice(parent, null, nombreV, 0, 0, 50, 50, estiloVertice);
				mapaDeVertices.put(nombreV, nodo);
			}

			String estiloArista = graficadorDeGrafo.estiloArista();

			for (Par<String, String> arista : aristas) {
				graficadorDeGrafo.insertarArista(parent, null, "", mapaDeVertices.get(arista.getIzquierda()),
						mapaDeVertices.get(arista.getDerecha()), estiloArista);
			}

		} finally {
			graficadorDeGrafo.finActualizar();
		}

		configuracionFinal();
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
