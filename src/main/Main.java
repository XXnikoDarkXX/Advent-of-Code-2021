package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dias.Dia1;
import dias.Dia1Parte2;
import dias.Dia2;
import dias.Dia3;

public class Main {

	public static void main(String[] args) {

		// Dia 1

		Dia1 dia1 = new Dia1();

		System.out.println(dia1.enviarSolucion());
		Dia1Parte2 dia1Parte2 = new Dia1Parte2();

		System.out.println("- DIA 1 - Parte 2: \n" + "¿Cuántas sumas son mayores que la suma anterior?\n"
				+ dia1Parte2.getTotalesMayores());
		// Dia 2

		Dia2 dia2 = new Dia2(Paths.get("txt\\subMarino.txt"));

		System.out.println("- DIA 2 - Parte 1: \n"
				+ "¿Qué obtienes si multiplicas tu posición horizontal final por tu profundidad final?\n"
				+ dia2.solucionParte1());

		System.out.println("- DIA 2 - Parte 2: \n "
				+ "¿Qué obtienes si multiplicas tu posición horizontal final por tu profundidad final?\n"
				+ dia2.solucionParte2());

		// Dia 3

		Dia3 dia3 = new Dia3(Paths.get("txt\\diagnosticoBinario.txt"));
		System.out.println(
				"- DIA 3 - Parte 1: \n  ¿Cuál es el consumo de energía del submarino?\n" + dia3.solucionParte1());
		
		System.out.println(
				"- DIA 3 - Parte 1: \n  ¿Cuál es la clasificación de soporte vital del submarino?\n" + dia3.solucionParte2());
		

		
		//Dia 4 
		Lector lector=new Lector(Paths.get("txt\\ejemploBingoCalamar.txt"));
		String []numeros=lector.leerNumerosBingo();
		
		String[]tableros=lector.leerTableroBingo();
		
		System.out.println();
		
		
		
		//Sacamos el primer carton del bingo en una matriz 5 5 
		
		//byte numerosCartonesBingos=
		
		
	}

}
