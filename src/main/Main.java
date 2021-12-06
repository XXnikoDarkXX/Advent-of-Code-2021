package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dias.Dia1;
import dias.Dia1Parte2;

public class Main {

	public static void main(String[] args) {

		
		//Dia 2 
		Lector lector = new Lector(Paths.get("txt\\subMarino.txt"));
		
		String[] data=lector.leerFichero();
		
		System.out.println(data.length);

		int posHorizontal=0;
		int posProfundidad=0;
		
		
			
		
		for (int i = 0; i < data.length; i++) {
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
		
		int multiplicacion=posHorizontal*posProfundidad;
		System.out.println(multiplicacion);
		
	}

}
