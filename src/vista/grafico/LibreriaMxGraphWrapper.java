package vista.grafico;

import java.awt.Color;

import javax.swing.JComponent;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class LibreriaMxGraphWrapper {
	private mxGraph graficoGrafo;

	public LibreriaMxGraphWrapper() {
		this.graficoGrafo = new mxGraph();
	}

	public Object getParent() {
		return graficoGrafo.getDefaultParent();
	}

	public Object getModel() {
		return graficoGrafo.getModel();
	}

	public Object insertarVertice(Object parent, Object object, String v, int i, int j, int k, int l, String estilo) {
		return graficoGrafo.insertVertex(parent, null, v, i, j, k, l, estilo);
	}

	public void actualizar() {
		graficoGrafo.getModel().beginUpdate();
	}

	public Object insertarArista(Object parent, Object object, String string, Object object2, Object object3,
			String estilo) {
		return graficoGrafo.insertEdge(object, null, string, object2, object3, estilo);
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
