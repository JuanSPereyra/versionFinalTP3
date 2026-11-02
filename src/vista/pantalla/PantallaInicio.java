package vista.pantalla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import vista.interfaz.InicioVista;

@SuppressWarnings("serial")
public class PantallaInicio extends VistaBaseCompartida implements InicioVista {

	private JPanel panelFondo;
	private JLabel lblTitulo, lblSubtitulo, lblImagen;
	private JButton btnCargaManual, btnCargaDocumento, btnSalir, btnTestStress;

	public PantallaInicio() {

		panelFondo = configurarPantalla("Conjunto Dominante Mínimo — Bienvenida");	
		crearElementos();
	}

	private void crearElementos() {
		crearLblTitulo();
		crearLblSubtitulo();
		crearBtnCargaManual();
		crearBtnCargaDocumento();
		crearBtnTestStress();
		crearBtnSalir();
		crearImagenFondo();
	}

	private void crearLblTitulo() {
		lblTitulo = crearLblTitulo("Conjunto Dominante Mínimo");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
		panelFondo.add(lblTitulo);
	}

	private void crearLblSubtitulo() {
		lblSubtitulo = crearLblTitulo("¿Cómo querés cargar los datos del grafo?");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblSubtitulo.setBounds(100, 160, 700, 30);
		panelFondo.add(lblSubtitulo);
	}

	private void crearBtnCargaManual() {
		btnCargaManual = crearBtn("Carga manual");
		btnCargaManual.setBackground(new Color(16, 185, 129));
		btnCargaManual.setBounds(300, 240, 300, 50);

		panelFondo.add(btnCargaManual);
	}

	private void crearBtnCargaDocumento() {
		btnCargaDocumento = crearBtn("Cargar con documento");
		btnCargaDocumento.setBackground(new Color(37, 99, 235));
		btnCargaDocumento.setBounds(300, 320, 300, 50);

		panelFondo.add(btnCargaDocumento);
	}

	private void crearBtnTestStress() {
		btnTestStress = crearBtn("Stress Test");
		btnTestStress.setBackground(new Color(190, 123, 123));
		btnTestStress.setBounds(300, 399, 300, 50);
		
		panelFondo.add(btnTestStress);
	}

	private void crearBtnSalir() {
		btnSalir = crearBtn("Salir");
		btnSalir.setBackground(new Color(239, 68, 68));
		btnSalir.setBounds(300, 477, 300, 50);

		panelFondo.add(btnSalir);
	}

	private void crearImagenFondo() {
		lblImagen = cargarImagen();
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
