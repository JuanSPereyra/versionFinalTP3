package vista.graficador;

import java.awt.Color;

import javax.swing.JComponent;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class LibreriaMxGraphWrapper {
	private mxGraph graficoGrafo;
	private Object parent;

	public LibreriaMxGraphWrapper() {
		this.graficoGrafo = new mxGraph();
		this.parent = getParent();
	}

	public Object getParent() {
		return graficoGrafo.getDefaultParent();
	}

	public Object getModel() {
		return graficoGrafo.getModel();
	}

	public Object insertarVertice(String nombreV, String estilo) {
		return graficoGrafo.insertVertex(parent, null, nombreV, 0, 0, 50, 50, estilo);
	}

	public void actualizar() {
		graficoGrafo.getModel().beginUpdate();
	}

	public Object insertarArista(Object verticeUno, Object verticeDos) {
		String estiloArista = estiloArista();
		return graficoGrafo.insertEdge(parent, null, "", verticeUno, verticeDos, estiloArista);
	}

	public void finActualizar() {
		graficoGrafo.getModel().endUpdate();
	}

	public void configurarGrafico() {
		graficoGrafo.setCellsMovable(true);
		graficoGrafo.setCellsEditable(false);
		graficoGrafo.setCellsResizable(false);
		graficoGrafo.setCellsDisconnectable(false);
		graficoGrafo.setConnectableEdges(false);
		graficoGrafo.setAllowDanglingEdges(false);
		graficoGrafo.setCellsBendable(false);
		graficoGrafo.setDropEnabled(false);
	}

	public String estiloVerticeNoDominante() {
		return "shape=ellipse;" + "strokeColor=white;" + "fillColor=blue;" + "fontColor=white;" + "fontSize=14";
	}

	public String estiloVerticeDominante() {
		return "shape=rectangle;" + "strokeColor=white;" + "fillColor=red;" + "fontColor=white;" + "fontSize=14";
	}

	public String estiloArista() {
		return "strokeColor=blue;" + "endArrow=none";
	}

	public void aplicarLayoutCircular() {
		mxCircleLayout layout = new mxCircleLayout(graficoGrafo);
		layout.execute(graficoGrafo.getDefaultParent());
	}

	public JComponent crearComponente() {
		mxGraphComponent graphComponent = new mxGraphComponent(graficoGrafo);

		configurarComponente(graphComponent);
		configurarZoom(graphComponent);

		return graphComponent;
	}

	private void configurarComponente(mxGraphComponent graphComponent) {
		graphComponent.setCenterZoom(true);
		graphComponent.getGraphHandler().setCloneEnabled(false);
		graphComponent.getGraphHandler().setImagePreview(false);
		graphComponent.setConnectable(false);
		graphComponent.getViewport().setBackground(Color.black);
	}

	private void configurarZoom(mxGraphComponent graphComponent) {
		graphComponent.addMouseWheelListener(e -> {
			if (e.getWheelRotation() < 0) {
				graphComponent.zoomIn();
			} else {
				graphComponent.zoomOut();
			}
		});
	}
}
