package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;

public class Lector {

	private Path rutaFichero;
	
	
	
	public Lector(Path rutaFichero) {
		this.rutaFichero=rutaFichero;
	}
	

	public Path getRutaFichero() {
		return rutaFichero;
	}

	public void setRutaFichero(Path rutaFichero) {
		this.rutaFichero = rutaFichero;
	}
	
	
	public String[] leerFichero(){
		// Declarar una variable BufferedReader
				BufferedReader br = null;
				String texto="";
				try {
					// Crear un objeto BufferedReader al que se le pasa
					// un objeto FileReader con el nombre del fichero
					br = new BufferedReader(new FileReader(rutaFichero.toString()));
					// Leer la primera línea, guardando en un String
					String linea = br.readLine();
					 texto+=linea+"\n";
					// Repetir mientras no se llegue al final del fichero
					while (linea != null) {
						// Hacer lo que sea con la línea leída
						// Leer la siguiente línea
						//System.out.println(linea);
						texto+=linea+"\n";

						linea = br.readLine();
					}
				} catch (FileNotFoundException e) {
					System.out.println("Error: Fichero no encontrado");
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println("Error de lectura del fichero");
					System.out.println(e.getMessage());
				} 
				
				return texto.split("\n");
	}
	
}
