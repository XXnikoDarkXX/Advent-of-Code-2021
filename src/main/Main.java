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

		Lector lector = new Lector(Paths.get("txt\\diagnosticoBinario.txt"));
		String[] codigos = lector.leerFichero();
		int matrizCodigos[][] = dia3.devolverMatriz(codigos);

		int posicion = 0;
		// int[] resultado = new int[matrizCodigos[0].length];
		LinkedList<Integer> resultado = new LinkedList<Integer>();
		for (int i = 0; i < matrizCodigos[0].length; i++) {

			int matriz[][] = new int[1][2];

			for (int j = 0; j < matrizCodigos.length; j++) {
				if (matrizCodigos[j][i] == 0) {
					matriz[0][0] += 1;

				} else {
					matriz[0][1] += 1;
				}
			}

			if (matriz[0][0] > matriz[0][1]) {
				// resultado[i] = 0;
				// Buscamos los resultados con posicion 0

				matrizCodigos = ObtenerMatrizPosicion(matrizCodigos, posicion, 0);
			} else if (matriz[0][0] < matriz[0][1]) {
				// resultado[i] = 1;
				matrizCodigos = ObtenerMatrizPosicion(matrizCodigos, posicion, 1);

			} else if (matriz[0][0] == matriz[0][1]) {
				matrizCodigos = ObtenerMatrizPosicion(matrizCodigos, posicion, 1);

			}

			posicion++;

		}
		posicion = 0;
		int[] clasiOxigeno = dia3.devolverBinario(matrizCodigos);
		String totalOxigeno = Arrays.toString(clasiOxigeno);
		totalOxigeno = totalOxigeno.replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
		
		
		matrizCodigos = dia3.devolverMatriz(codigos);

		for (int i = 0; i < matrizCodigos[0].length; i++) {
			if (matrizCodigos.length == 1) {
				break;
			}
			int matriz[][] = new int[1][2];

			for (int j = 0; j < matrizCodigos.length; j++) {
				if (matrizCodigos[j][i] == 0) {
					matriz[0][0] += 1;

				} else {
					matriz[0][1] += 1;
				}
			}

			if (matriz[0][0] < matriz[0][1]) {
				// resultado[i] = 0;
				// Buscamos los resultados con posicion 0

				matrizCodigos = ObtenerMatrizPosicion(matrizCodigos, posicion, 0);
			} else if (matriz[0][0] > matriz[0][1]) {
				// resultado[i] = 1;
				matrizCodigos = ObtenerMatrizPosicion(matrizCodigos, posicion, 1);

			} else if (matriz[0][0] == matriz[0][1]) {
				matrizCodigos = ObtenerMatrizPosicion(matrizCodigos, posicion, 0);

			}

			posicion++;

		}

	
	int[] clasCo2=	dia3.devolverBinario(matrizCodigos);
	String totalCo2 = Arrays.toString(clasCo2);
	totalCo2 = totalCo2.replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
	
	
	long puntoCo2 = dia3.convertirBinarioAdecimal(totalCo2);// Convertimos el numero gamma a decimal
	long puntoOxigeno = dia3.convertirBinarioAdecimal(totalOxigeno);// convertimos el numero epsilon a decimal
	System.out.println(puntoCo2*puntoOxigeno);
	
	
	}

	// Clasificacion de soporte vital = calsificacion del generador de oxigeno *
	// clasiDepurador
	public static int[][] ObtenerMatrizPosicion(int[][] matrizCodigos, int posicion, int numero) {
		int[][] resultado = null;
		int contadorTotal = 0;
		int contador = 0;
		for (int i = 0; i < matrizCodigos.length; i++) {
			if (matrizCodigos[i][posicion] == numero) {
				contadorTotal++;

			}

		}
		resultado = new int[contadorTotal][12];

		for (int i = 0; i < matrizCodigos.length; i++) {

			if (matrizCodigos[i][posicion] == numero) {

				for (int e = 0; e < resultado[0].length; e++) {
					resultado[contador][e] = matrizCodigos[i][e];
				}
				contador++;
			}

		}

		return resultado;

	}

}
