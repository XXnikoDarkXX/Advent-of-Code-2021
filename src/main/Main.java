package main;

import java.nio.file.Paths;
import java.util.Iterator;

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

		System.out.println("- DIA 3 - Parte 2: \n  ¿Cuál es la clasificación de soporte vital del submarino?\n"
				+ dia3.solucionParte2());

		// Dia 4

		Lector lector = new Lector(Paths.get("txt\\bingoCalamar.txt"));
		String[] numeros = lector.leerNumerosBingo();

		int[] numerosBingo = new int[numeros.length];

		// Conversión en un array los numeros lanzados del bingo debido a que está en
		// String
		for (int i = 0; i < numerosBingo.length; i++) {
			int numero = Integer.parseInt(numeros[i]);
			numerosBingo[i] = numero;
		}

		String[] tableros = lector.leerTableroBingo();

		int[][] numerosCartonesBingos = new int[tableros.length / 25][2];// Totales de cartones en el juego
		int numeroTableros = tableros.length / 25;

		for (int i = 0; i < numeroTableros; i++) {// Este bucle se ejecutará las veces que de cartones de bingo que haya

			int[][] cartonComprobacion = new int[5][5];// Creamos el carton de comprobacion

			int[][] carton = sacarCarton(tableros, cartonComprobacion);// Sacamos el primer carton del bingo en una
																		// matriz 5 5

			int[][] auxiliar = jugarCarton(numerosBingo, carton, cartonComprobacion, i, numerosCartonesBingos);

			numerosCartonesBingos[i][0] = auxiliar[0][0];
			numerosCartonesBingos[i][1] = auxiliar[0][1];
			tableros = devolverTablero(tableros);

		}
		int indice = numerosCartonesBingos[0][0];
		int resultado = numerosCartonesBingos[0][0];

		for (int i = 0; i < numerosCartonesBingos.length; i++) {
			for (int j = 0; j < 1; j++) {
				if (numerosCartonesBingos[i][0] > resultado) {
					indice = i;
					resultado = numerosCartonesBingos[i][j];
				}
			}
		}
		
		Dia4 dia4 = new Dia4(Paths.get("txt\\bingoCalamar.txt"));
		System.out.println(
				"- DIA 4 - Parte 1: \n ¿Cuál será tu puntuación final si eliges esa tabla?\n" + dia4.solucionParte1());
		System.out.println(numerosCartonesBingos[indice][1]);

	}

	public static int[][] sacarCarton(String[] tableros, int[][] cartonComprobacion) {
		int[][] carton = new int[5][5];
		int contador = 0;
		String[] nuevoTablero;
		for (int i = 0; i < carton.length; i++) {
			for (int j = 0; j < carton[0].length; j++) {
				int numero = Integer.parseInt(tableros[contador]);
				carton[i][j] = numero;
				contador++;

				cartonComprobacion[i][j] = -1;
			}
		}
		nuevoTablero = new String[tableros.length - 25];
		contador = 0;
		for (int i = 25; i < tableros.length; i++) {
			nuevoTablero[contador] = tableros[i];
			contador++;
		}
		tableros = nuevoTablero;
		return carton;
	}

	public static String[] devolverTablero(String[] tableros) {
		String[] nuevoTablero = new String[tableros.length - 25];
		int contador = 0;
		for (int i = 25; i < tableros.length; i++) {
			nuevoTablero[contador] = tableros[i];
			contador++;
		}
		return nuevoTablero;
	}

	public static boolean comprobarBingo(int[][] cartonComprobacion, int post1, int post2) {

		for (int i = 0; i < 2; i++) {
			int ganas = 5;// si el contador llega a 0 has ganado
			for (int j = 0; j < cartonComprobacion.length; j++) {
				if (i == 0) {
					if (cartonComprobacion[post1][j] >=0) {
						ganas--;
					}
				} else {
					if (cartonComprobacion[j][post2] >= 0) {
						ganas--;
					}
				}
			}
			if (ganas == 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Funcion para comenzar a jugar un carton del bingo
	 * 
	 * @param numerosBingo       arrays con los numeros del bingo
	 * @param carton             del bingo
	 * @param cartonComprobacion que se usara para saber si ha ganado o no
	 * @param i
	 * @return
	 */
	public static int[][] jugarCarton(int[] numerosBingo, int[][] carton, int[][] cartonComprobacion, int i,
			int[][] numerosCartonesBingos) {
		boolean hasGanado;// Booleano que usaremos de control para saber si un carton ha ganado y para su
							// bucle
		int[][] matrizTotal = new int[1][2];

		for (int h = 0; h < numerosBingo.length; h++) {
			
			for (int j = 0; j < carton.length; j++) {
				for (int e = 0; e < carton.length; e++) {
					
					if (numerosBingo[h] == carton[j][e]) {
						
						
						cartonComprobacion[j][e] = numerosBingo[h];
						hasGanado = comprobarBingo(cartonComprobacion, j, e);

						if (hasGanado) {
							// h devuelve la posicion del array de los numeros del bingo lanzados

							matrizTotal[0][0] = h;

							matrizTotal[0][1] = sumaNumerosCartonBingo(cartonComprobacion, numerosCartonesBingos,
									carton) * numerosBingo[h];
							return matrizTotal;
						}

					}

				}
			}

		}
		return null;
	}

	public static int sumaNumerosCartonBingo(int[][] cartonComprobacion, int[][] numerosCartonesBingos,
			int[][] cartonBingo) {

		int suma = 0;
		for (int i = 0; i < cartonComprobacion.length; i++) {
			for (int j = 0; j < cartonComprobacion.length; j++) {
				if (cartonComprobacion[i][j] != cartonBingo[i][j]) {
					suma += cartonBingo[i][j];
				}
			}
		}

		return suma;
	}

}
