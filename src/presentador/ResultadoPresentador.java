package presentador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import coordinadorMVP.CoordinadorPresentadores;
import estructuraDatosAux.Par;
import estructuraDatosAux.VerticeVisual;
import modelo.Instancia;
import modelo.Solucion;
import modelo.backtracking.BackTracking;
import modelo.grafo.Vertice;
import modelo.grafogenetico.PoblacionGrafoGenetico;
import modelo.heuristicas.SolverGoloso;
import vista.interfaz.ResultadoVista;

public class ResultadoPresentador {

	private ResultadoVista _rVista;
	private Instancia instancia;
	private CoordinadorPresentadores coordinador;
	private List<Solucion> soluciones;
	private static final List<String> NOMBRES_ESTRATEGIAS = List.of
			(
				"Óptima (Backtracking)",
				"Solución Genética",
				"Mayor Cobertura",
				"Menor Cobertura",
				"Mayor Grado",
				"Menor Grado",
				"Nombre A-Z",
				"Nombre Z-A",
				"Peso por vecino"
			);

	public ResultadoPresentador(ResultadoVista resultadoVista, Instancia instancia,
			CoordinadorPresentadores coordinadorPresentadores) {
		this._rVista = resultadoVista;
		this.coordinador = coordinadorPresentadores;
		this.instancia = instancia;

		calcularSolucionDominante();
		iniciarListeners();
	}

	private void iniciarListeners() {
		listenerMostrarSolucion();
		listenerVolverMenu();
		listenerSalir();
		listenerModificarDatos();
	}

	private void listenerMostrarSolucion() {
		this._rVista.listenerMostrarSolucion(e -> {
			int solucionAMostrar = this._rVista.solucionElegida();
			mostrarSolucion(solucionAMostrar);
		});
	}

	private void listenerVolverMenu() {
		this._rVista.listenerVolverAlMenu(e -> manejarClickVolverAlMenu());
	}

	private void listenerSalir() {
		this._rVista.listenerSalir(e -> manejarClickBtnSalir());
	}

	private void listenerModificarDatos() {
		this._rVista.listenerModificarDatos(e -> manejarClickModificarDatos());
	}

	private void calcularSolucionDominante() {

		SolverGoloso solverGoloso = new SolverGoloso(instancia);

		soluciones = new ArrayList<>();

		soluciones.add(resolverBacktracking());
		soluciones.add(resolverGrafoGenetico());
		soluciones.addAll(solverGoloso.resolver());

		completarComboSoluciones();
		construirStringDeRespuestas();
		mostrarSolucion(0);
	}

	private Solucion resolverGrafoGenetico() {

		PoblacionGrafoGenetico poblacionGenetica = new PoblacionGrafoGenetico(instancia);

		Set<Vertice> mejorIndividuo = poblacionGenetica.resolver();

		return transformarSetASolucion(mejorIndividuo);
	}

	private Solucion resolverBacktracking() {

		BackTracking bt = new BackTracking();

		Set<Vertice> verticesOptimos = bt.resolverGrafo(instancia.getGrafo());

		return transformarSetASolucion(verticesOptimos);
	}

	private Solucion transformarSetASolucion(Set<Vertice> setSolucion) {
		Solucion sol = new Solucion(instancia);

		for (Vertice v : setSolucion) {
			sol.agregar(v);
		}
		return sol;
	}

	private void completarComboSoluciones() {
		_rVista.completarComboSoluciones(NOMBRES_ESTRATEGIAS);
	}

	private void construirStringDeRespuestas() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < soluciones.size(); i++) {

			Solucion sol = soluciones.get(i);

			sb.append(NOMBRES_ESTRATEGIAS.get(i)).append("\n");
			sb.append("Total de vértices en la solución: ").append(sol.cardinal()).append("\n");
			sb.append("Vértices seleccionados:\n{");

			for (Vertice v : sol.getVertices()) {
				sb.append(v.getNombre()).append(", ");
			}

			if (!sol.getVertices().isEmpty()) {
				sb.delete(sb.length() - 2, sb.length());
			}

			sb.append("}\n\n");
		}
		_rVista.mostrarResultado(sb.toString());
	}

	private void mostrarSolucion(int indiceEstrategiaElegida) {

		Solucion sol = soluciones.get(indiceEstrategiaElegida);

		List<VerticeVisual> vertices = instancia.getVertices().stream()
				.map(v -> new VerticeVisual(v.getNombre(), sol.getVertices().contains(v))).toList();

		List<Par<String, String>> aristas = instancia.getAristasComoNombres();

		_rVista.mostrarGrafo(vertices, aristas);
	}

	private void manejarClickVolverAlMenu() {
		coordinador.iniciarAplicacion();
	}

	private void manejarClickBtnSalir() {
		System.exit(0);
	}

	private void manejarClickModificarDatos() {
		coordinador.irAModificarDatos();
	}
}
