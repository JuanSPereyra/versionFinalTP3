package swingWorker;

import java.util.*;
import javax.swing.SwingWorker;

import generadorRandomWrapper.GeneradorAleatorio;
import modelo.backtracking.BackTracking;
import modelo.backtracking.GeneradorGrafo;
import modelo.grafo.Grafo;
import modelo.grafo.Vertice;

import java.math.*;

public class TestStressWorker extends SwingWorker<String, String> {

	private InterfazTestStressPresentador _interfazPresentador;
	private volatile BackTracking backTrackingActual;

	private int solucionesMaximas;
	private int densidad;
	private int recibidos = 0;
	private int iteracion;
	private int cantidadTotalAristas;
	private int cantidadAristasAUsar;
	private Set<Vertice> resultado;
	private double tiempo;
	private GeneradorGrafo generadorGrafoRnd;

	public TestStressWorker(InterfazTestStressPresentador InterfazPresentador, int cantVertices, int densidad) {
		this._interfazPresentador = InterfazPresentador;
		this.solucionesMaximas = cantVertices;
		this.densidad = densidad;
		this.generadorGrafoRnd = new GeneradorGrafo();
	}

	public void detener() {
		cancel(true);
		BackTracking bt = backTrackingActual;
		if (bt != null) {
			bt.cancelar();
		}
	}

	@Override
	protected String doInBackground() {

		double total = 0;

		for (int i = 1; i <= solucionesMaximas && !isCancelled(); i++) {

			long inicio = System.currentTimeMillis();

			calcularCantidadAristas(i, densidad);
			Set<Vertice> resultado = resolverGrafo(i, cantidadAristasAUsar);

			if (!isCancelled()) {
				this.tiempo = (System.currentTimeMillis() - inicio) / 1000.0;
				total += tiempo;

				this.iteracion = i;
				this.resultado = resultado;

				publish(armarTexto());
				pausar(250);
			}
		}
		BigDecimal redondeado = new BigDecimal(total).setScale(3, RoundingMode.HALF_UP);
		return "TestStress Finalizado." + "\n" + "Tiempo total de la ejecucion: " + redondeado + " segundos";
	}

	private void calcularCantidadAristas(int numVertice, int densidad) {

		this.cantidadTotalAristas = (numVertice * (numVertice - 1) / 2);
		this.cantidadAristasAUsar = cantidadTotalAristas * densidad / 10;
	}

	private Set<Vertice> resolverGrafo(int vertices, int aristas) {

		GeneradorGrafo.setGenerador(new GeneradorAleatorio());
		Grafo g = generadorGrafoRnd.grafoRandom(vertices, aristas);

		backTrackingActual = new BackTracking();
		return backTrackingActual.resolverGrafo(g);
	}

	private String armarTexto() {

		StringBuilder sb = new StringBuilder();
		sb.append("Solucion: " + this.iteracion + " de " + solucionesMaximas + " con densidad del " + this.densidad * 10
				+ "%\n");
		sb.append("Cantidad de Vertices: " + this.iteracion + "\n");
		sb.append("Cantidad máxima de Aristas: " + this.cantidadTotalAristas + "\n");
		sb.append("Cantidad de Aristas utilizadas: " + this.cantidadAristasAUsar + "\n");
		sb.append(conjuntoEntreLLaves(this.resultado) + "\n");
		sb.append("Tiempo utilizado: " + this.tiempo + "\n");
		return sb.toString();
	}

	private String conjuntoEntreLLaves(Set<Vertice> conjunto) {
		StringBuilder sb = new StringBuilder("{");
		boolean primero = true;
		for (Vertice v : conjunto) {
			if (!primero) {
				sb.append(", ");
			}
			sb.append(v);
			primero = false;
		}
		sb.append("}");
		return sb.toString();
	}

	private void pausar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
		}
	}

	@Override
	protected void process(List<String> textos) {
		for (String texto : textos) {
			_interfazPresentador.mostrarResultado(texto);
		}
		recibidos += textos.size();
		_interfazPresentador.actualizarProgreso(recibidos * 100 / solucionesMaximas);
	}

	@Override
	protected void done() {
		if (isCancelled()) {
			_interfazPresentador.mostrarResultado("TestStress detenido.");
		} else {
			try {
				_interfazPresentador.mostrarResultado(get());
			} catch (Exception e) {
				e.printStackTrace();
				_interfazPresentador.mostrarResultado("Error en el TestStress.");
			}
		}
		_interfazPresentador.habilitarBotonesTestStress(false);
	}
}
