package main;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Dia4 {

	private Lector lector;// Lector que usaremos para leer archivos
	String[] tableros;// array que usaremos para obtener del lector los numeros del bingo y los
						// cartones
	int[] numerosBingo;// Numeros lanzados del bingo
	int[][] numerosCartonesBingos; // Matriz donde usaremos para ver el resultado de cada carton
	int numeroTableros;// Numeros totales tableros de bingo

	public Dia4(Path rutaArchivo) {

		Lector lector = new Lector(rutaArchivo);

		String[] numeros = lector.leerNumerosBingo();
		// Conversión en un array los numeros lanzados del bingo debido a que está en
		// String
		
		 numerosBingo = new int[numeros.length];

		for (int i = 0; i < numerosBingo.length; i++) {
			int numero = Integer.parseInt(numeros[i]);
			numerosBingo[i] = numero;
		}
		tableros = lector.leerTableroBingo();

		numerosCartonesBingos = new int[tableros.length / 25][2];

		numeroTableros = tableros.length / 25;

	}

	/**
	 * Funcion para devolver un carton del bingo
	 * 
	 * @param tableros           arrays con todos los cartones del bingo
	 * @param cartonComprobacion matriz donde usaremos para ir poniendo todos los
	 *                           numeros que acierte el carton
	 * @return int [][]un nuevo carton del tablero
	 */
	public int[][] sacarCarton(String[] tableros, int[][] cartonComprobacion) {
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

	/**
	 * Funcion para eliminar un carton del tablero ya lanzado
	 * 
	 * @param tableros total de cartones del bingo
	 * @return String [][]el tablero menos un carton
	 */
	public String[] devolverTablero(String[] tableros) {
		String[] nuevoTablero = new String[tableros.length - 25];
		int contador = 0;
		for (int i = 25; i < tableros.length; i++) {
			nuevoTablero[contador] = tableros[i];
			contador++;
		}
		return nuevoTablero;
	}

	/**
	 * Funcion para comprobar de un carton cuantos numeros son elegidos
	 * 
	 * @param cartonComprobacion matriz donde se tiene los numeros acertados en
	 *                           posicion exacta de un carton
	 * @param post1              se usara para comprobar la posicion 1
	 * @param post2              se usara para comprobar la posicion 2
	 * @return true si ha acertade, false si no ha acertado
	 */
	public boolean comprobarBingo(int[][] cartonComprobacion, int post1, int post2) {

		for (int i = 0; i < 2; i++) {
			int ganas = 5;// si el contador llega a 0 has ganado
			for (int j = 0; j < cartonComprobacion.length; j++) {
				if (i == 0) {
					if (cartonComprobacion[post1][j] > 0) {
						ganas--;
					}
				} else {
					if (cartonComprobacion[post2][j] > 0) {
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
	public int[][] jugarCarton(int[] numerosBingo, int[][] carton, int[][] cartonComprobacion, int i,
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

	/**
	 * Funcion para sumar los numeros del carton que no hayan sido acertados
	 * 
	 * @param cartonComprobacion    carton comprobador
	 * @param numeroCarton          numero del carton
	 * @param numerosCartonesBingos
	 * @param cartonBingo
	 * @return
	 */
	public int sumaNumerosCartonBingo(int[][] cartonComprobacion, int[][] numerosCartonesBingos, int[][] cartonBingo) {

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

	public int solucionParte1() {
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
				if (numerosCartonesBingos[i][0] < resultado) {
					indice = i;
					resultado = numerosCartonesBingos[i][j];
				}
			}
		}
		return numerosCartonesBingos[indice][1];
	}

	
	


}
