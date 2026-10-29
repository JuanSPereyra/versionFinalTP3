package datos;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;
import estructuraDatosAux.GrafoJson;

public class CargadorJson {

	public static GrafoJson cargarGrafo(String rutaArchivo) {
		Gson gson = new Gson();
		try (Reader reader = new FileReader(rutaArchivo)) {
			return gson.fromJson(reader, GrafoJson.class);
		} catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo JSON:" + e.getMessage(), e);
		}
	}
}
