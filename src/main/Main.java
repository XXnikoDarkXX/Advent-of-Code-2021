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
import dias.Dia2;

public class Main {

	public static void main(String[] args) {

		
		//Dia 1 
		
		Dia1 dia1=new Dia1();
		
		System.out.println(dia1.enviarSolucion());
		Dia1Parte2 dia1Parte2=new Dia1Parte2();
		
		System.out.println("- DIA 1 - Parte 2: \n"+"¿Cuántas sumas son mayores que la suma anterior?\n"+dia1Parte2.getTotalesMayores());
		//Dia 2 
		
		Dia2 dia2=new Dia2(Paths.get("txt\\subMarino.txt"));
		
		System.out.println("- DIA 1 - Parte 2: \\n\"+\"¿Qué obtienes si multiplicas tu posición horizontal final por tu profundidad final?\n"+dia2.solucionParte1());
		String []data=dia2.getData();
		
		
		dia2.setPosHorizontal(0);
		dia2.setPosProfundidad(0);
		
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
			//	posHorizontal=posHorizontal+Integer.parseInt(reemplazo.replace(" ", ""));
				dia2.setPosHorizontal(dia2.getPosHorizontal()+Integer.parseInt(reemplazo.replace(" ", "")));
				dia2.setPosProfundidad((Integer.parseInt(reemplazo.replace(" ", ""))*dia2.getObjetivo())+dia2.getPosProfundidad());
				break;
			}
			
			case "down":{
			//	posProfundidad=posProfundidad+Integer.parseInt(reemplazo.replace(" ", ""));
				dia2.setObjetivo(dia2.getObjetivo()+Integer.parseInt(reemplazo.replace(" ", "")));
				
				break;

			}
			
			case "up":{
				dia2.setObjetivo(dia2.getObjetivo()-Integer.parseInt(reemplazo.replace(" ", "")));
				break;
			}
			
			
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + data[i]);
				
			}
		}
		
		System.out.println(dia2.getPosHorizontal()*dia2.getPosProfundidad());
		
	
	}

}
