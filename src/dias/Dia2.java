package dias;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Lector;

public class Dia2 {
	private Lector lector ;//clase privada lector que usaremos para leer el archivo
	private int posHorizontal;//posicion Horizontal
	private int posProfundidad;//posición de profundidad
	private int objetivo;
	
	
	private String[] data;//Array donde guardamos la información del txt
	
	public Dia2(Path rutaArchivo) {
		lector = new Lector(rutaArchivo);
		this.posHorizontal=0;
		this.posProfundidad=0;
		 data=lector.leerFichero();
		
		
	}
	
	public int getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(int objetivo) {
		this.objetivo = objetivo;
	}

	public Lector getLector() {
		return lector;
	}
	public void setLector(Lector lector) {
		this.lector = lector;
	}
	public int getPosHorizontal() {
		return posHorizontal;
	}
	public void setPosHorizontal(int posHorizontal) {
		this.posHorizontal = posHorizontal;
	}
	public int getPosProfundidad() {
		return posProfundidad;
	}
	public void setPosProfundidad(int posProfundidad) {
		this.posProfundidad = posProfundidad;
	}
	
	
	
	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	public int solucionParte1() {
		
		for (int i = 0; i < data.length; i++) {
			//Mediante el siguiente uso de regex separamos los comandos con su valor al separar el valor contiene un espacio
			Pattern pattern = Pattern.compile("^[^\\s]+(\\s+[^\\s]+)*$");
			Matcher matcher = pattern.matcher(data[i]);
			String reemplazo="";
			
			if (matcher.find()){
				  reemplazo=  matcher.group(1);
				}
			String opcion=data[i].replace(reemplazo, "");
			switch (opcion) {
			case "forward": {
				posHorizontal=posHorizontal+Integer.parseInt(reemplazo.replace(" ", ""));
				
				break;
			}
			
			case "down":{
				posProfundidad=posProfundidad+Integer.parseInt(reemplazo.replace(" ", ""));
				break;

			}
			
			case "up":{
				posProfundidad=posProfundidad-Integer.parseInt(reemplazo.replace(" ", ""));
				break;
			}
			
			
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + data[i]);
				
			}
		}
		
		return this.posHorizontal*this.posProfundidad;
	}
	
	
	
	
	
	
	
}
