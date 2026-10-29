package vista.pantalla;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import vista.interfaz.InicioVista;

@SuppressWarnings("serial")
public class PantallaInicio extends JFrame implements InicioVista {

	private JPanel panelFondo;
	private JLabel lblTitulo, lblSubtitulo, lblImagen;
	private JButton btnCargaManual, btnCargaDocumento, btnSalir, btnTestStress;

	public PantallaInicio() {

		configurarPantalla();
		crearLblTitulo();
		crearLblSubtitulo();
		crearBtnCargaManual();
		crearBtnCargaDocumento();
		crearBtnTestStress();
		crearBtnSalir();
		crearImagenFondo();
	}

	private void configurarPantalla() {
		setTitle("Conjunto Dominante Mínimo — Bienvenida");
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
		lblTitulo = new JLabel("Conjunto Dominante Mínimo", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(100, 100, 700, 50);
		panelFondo.add(lblTitulo);
	}

	private void crearLblSubtitulo() {
		lblSubtitulo = new JLabel("¿Cómo vas a cargar los datos del grafo?", SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSubtitulo.setForeground(new Color(222, 233, 221));
		lblSubtitulo.setBounds(100, 160, 700, 30);
		panelFondo.add(lblSubtitulo);
	}

	private void crearBtnCargaManual() {
		btnCargaManual = new JButton("Carga manual");
		btnCargaManual.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnCargaManual.setForeground(Color.WHITE);
		btnCargaManual.setBackground(new Color(16, 185, 129));
		btnCargaManual.setFocusPainted(false);
		btnCargaManual.setBorderPainted(false);
		btnCargaManual.setOpaque(true);
		btnCargaManual.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCargaManual.setBounds(300, 240, 300, 50);

		panelFondo.add(btnCargaManual);
	}

	private void crearBtnCargaDocumento() {
		btnCargaDocumento = new JButton("Cargar con documento");
		btnCargaDocumento.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnCargaDocumento.setForeground(Color.WHITE);
		btnCargaDocumento.setBackground(new Color(37, 99, 235));
		btnCargaDocumento.setFocusPainted(false);
		btnCargaDocumento.setBorderPainted(false);
		btnCargaDocumento.setOpaque(true);
		btnCargaDocumento.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCargaDocumento.setBounds(300, 320, 300, 50);

		panelFondo.add(btnCargaDocumento);
	}

	private void crearBtnTestStress() {
		btnTestStress = new JButton("Stress Test");

		btnTestStress.setOpaque(true);
		btnTestStress.setForeground(Color.WHITE);
		btnTestStress.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnTestStress.setFocusPainted(false);
		btnTestStress.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTestStress.setBorderPainted(false);
		btnTestStress.setBackground(new Color(190, 123, 123));
		btnTestStress.setBounds(300, 399, 300, 50);
		panelFondo.add(btnTestStress);
	}

	private void crearBtnSalir() {
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(239, 68, 68));
		btnSalir.setFocusPainted(false);
		btnSalir.setBorderPainted(false);
		btnSalir.setOpaque(true);
		btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSalir.setBounds(300, 477, 300, 50);

		panelFondo.add(btnSalir);
	}

	private void crearImagenFondo() {
		URL url = getClass().getResource("/imagenes/CDM.jpg");
		if (url == null) {
			return;
		}
		lblImagen = new JLabel();
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

		Image escalada = new ImageIcon(url).getImage().getScaledInstance(900, 570, Image.SCALE_SMOOTH);
		lblImagen.setIcon(new ImageIcon(escalada));
		lblImagen.setBounds(0, 0, 900, 570);
		panelFondo.add(lblImagen);
	}

	@Override
	public void listenerCargaManual(ActionListener e) {
		btnCargaManual.addActionListener(e);
	}

	@Override
	public void listenerCargaDocumento(ActionListener e) {
		btnCargaDocumento.addActionListener(e);
	}

	@Override
	public void listenerTestStress(ActionListener e) {
		btnTestStress.addActionListener(e);
	}

	@Override
	public void listenerSalir(ActionListener e) {
		btnSalir.addActionListener(e);
	}
}
