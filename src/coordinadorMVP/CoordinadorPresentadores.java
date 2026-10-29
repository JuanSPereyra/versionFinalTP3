package coordinadorMVP;

import vista.interfaz.InterfazGestorPantalla;
import vista.pantalla.*;
import presentador.*;
import modelo.Instancia;

public class CoordinadorPresentadores {
	private InterfazGestorPantalla gestorInterfaz;
	private Instancia instancia;

	private PantallaInicio pantallaInicio;
	private PantallaCargarDatosManual pantallaCargaManual;
	private PantallaCargarDatosDesdeJSON pantallaJson;
	private PantallaResultado pantallaResultado;
	private PantallaTestStress pantallaTestStress;

	private OrigenCarga origenCarga;

	public CoordinadorPresentadores() {
		this.gestorInterfaz = new GestorPantallas();
	}

	public void iniciarAplicacion() {

		pantallaInicio = new PantallaInicio();
		new BienvenidaPresentador(pantallaInicio, this);

		gestorInterfaz.mostrarPantalla(pantallaInicio);
	}

	public void irACargaManual() {

		this.origenCarga = OrigenCarga.MANUAL;
		this.instancia = new Instancia();

		pantallaCargaManual = new PantallaCargarDatosManual();
		new CargarDatosPresentador(pantallaCargaManual, instancia, this);

		gestorInterfaz.mostrarPantalla(pantallaCargaManual);
	}

	public void irAResultado() {

		pantallaResultado = new PantallaResultado();
		new ResultadoPresentador(pantallaResultado, instancia, this);

		gestorInterfaz.mostrarPantalla(pantallaResultado);
	}

	public void irACargaDesdeJSON() {

		this.origenCarga = OrigenCarga.JSON;
		this.instancia = new Instancia();

		pantallaJson = new PantallaCargarDatosDesdeJSON();
		new CargarJsonPresentador(pantallaJson, instancia, this);

		gestorInterfaz.mostrarPantalla(pantallaJson);
	}

	public void irATestStress() {

		pantallaTestStress = new PantallaTestStress();
		new TestStressPresentador(pantallaTestStress, this);

		gestorInterfaz.mostrarPantalla(pantallaTestStress);
	}

	public void irAModificarDatos() {
		if (this.origenCarga == null) {
			return;
		}

		switch (this.origenCarga) {
		case MANUAL:
			gestorInterfaz.mostrarPantalla(this.pantallaCargaManual);
			break;

		case JSON:
			this.pantallaCargaManual = new PantallaCargarDatosManual();

			CargarDatosPresentador presentadorManual = new CargarDatosPresentador(pantallaCargaManual, instancia, this);

			presentadorManual.cargarDatosDesdeModelo();

			gestorInterfaz.mostrarPantalla(pantallaCargaManual);
			break;
		}
	}
}
