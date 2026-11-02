package vista.pantalla;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public abstract class VistaBaseCompartida extends JFrame {
	
	private JPanel panelFondo;
	private JLabel lblTitulo;
	private JButton btnNuevo;
	private JLabel lblImagen;
	private JTextArea txtResultado;
	private JScrollPane areaResultado;
	
	protected JPanel configurarPantalla(String titulo) {
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		panelFondo = new JPanel();
		panelFondo.setBackground(new Color(30, 41, 59));
		setContentPane(panelFondo);
		panelFondo.setLayout(null);
		
		return panelFondo;
	}
	
	protected JLabel crearLblTitulo(String titulo) {
		lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(100, 100, 700, 50);
		panelFondo.add(lblTitulo);
		
		return lblTitulo;
	}
	
	protected JButton crearBtn(String nombre) {
		btnNuevo = new JButton(nombre);
		btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBorderPainted(true);
		btnNuevo.setOpaque(true);
		btnNuevo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		return btnNuevo;
	}
	
	protected JLabel cargarImagen() {
		URL url = getClass().getResource("/imagenes/CDM.jpg");
		if (url == null) {
			return null;
		}
		lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

		Image escalada = new ImageIcon(url).getImage().getScaledInstance(900, 570, Image.SCALE_SMOOTH);
		lblImagen.setIcon(new ImageIcon(escalada));
		lblImagen.setBounds(0, 0, 900, 570);
		
		return lblImagen;
	}
	
	protected JTextArea crearTxtResultado() {
		txtResultado = new JTextArea();
		txtResultado.setFont(new Font("Consolas", Font.PLAIN, 15));
		txtResultado.setBackground(new Color(15, 23, 42));
		txtResultado.setForeground(new Color(241, 245, 249));
		txtResultado.setEditable(false);
		txtResultado.setMargin(new Insets(15, 15, 15, 15));
		
		return txtResultado;
	}
	protected JScrollPane crearScrollResultados(JTextArea txtResultado) {
		areaResultado = new JScrollPane(txtResultado);
		areaResultado.setBorder(BorderFactory.createLineBorder(new Color(51, 65, 85)));
		areaResultado.setBounds(78, 69, 742, 274);
		
		return areaResultado;
	}
}
