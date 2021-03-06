package dias;

import java.nio.file.Path;
import java.util.Arrays;

import main.Lector;

public class Dia3 {
	private long totalGamma;
	private long totalEpsilon;
	private Lector lector;
	private int matrizCodigos[][];
	private long consumoEnergia;

	private String[] codigos;
	private long clasCO2;
	private long  clasOxigeno;
	private long clasSoporteVital;//clasCO2*clasOxigeno

	public Dia3(Path rutaArchivo) {
		lector = new Lector(rutaArchivo);
		codigos = lector.leerFichero();
		matrizCodigos = devolverMatriz(codigos);

	}

	

	public long getTotalGamma() {
		return totalGamma;
	}

	public void setTotalGamma(long totalGamma) {
		this.totalGamma = totalGamma;
	}

	public long getTotalEpsilon() {
		return totalEpsilon;
	}

	public void setTotalEpsilon(long totalEpsilon) {
		this.totalEpsilon = totalEpsilon;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

	public int[][] getMatrizCodigos() {
		return matrizCodigos;
	}

	public void setMatrizCodigos(int[][] matrizCodigos) {
		this.matrizCodigos = matrizCodigos;
	}

	public long getConsumoEnergia() {
		return consumoEnergia;
	}

	public void setConsumoEnergia(long consumoEnergia) {
		this.consumoEnergia = consumoEnergia;
	}

	public long convertirBinarioAdecimal(String numeros) {
		long numero = Long.parseLong(numeros);
		int exponente = 0;
		int digito;
		long decimal = 0; // ser? el equivalente en base decimal
		while (numero != 0) {
			// se toma la ?ltima cifra

			digito = (int) (numero % 10);
			// se multiplica por la potencia de 2 correspondiente y se suma al n?mero
			decimal = decimal + digito * (int) Math.pow(2, exponente);
			// se aumenta el exponente
			exponente++;
			// se quita la ?ltima cifra para repetir el proceso
			numero = numero / 10;
		}

		return decimal;

	}

	public String darVueltaBinario(int[] array) {
		String resultado = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 0) {
				resultado += "1";
			} else {
				resultado += "0";
			}
		}
		return resultado;
	}

	public int[][] devolverMatriz(String[] codigos) {
		int[][] matrizCodigos = new int[codigos.length][12];
		// Pasamos nuestro txt a matriz
		for (int i = 0; i < codigos.length; i++) {
			for (int j = 0; j < codigos[i].length(); j++) {
				String numero = "" + codigos[i].charAt(j);

				matrizCodigos[i][j] = Integer.parseInt(numero);
			}
		}
		return matrizCodigos;
	}

	public int[] devolverBinario(int[][] matrizCodigos) {
		int posicion = 0;
		int[] resultado = new int[matrizCodigos[0].length];

		for (int i = 0; i < resultado.length; i++) {

			int matriz[][] = new int[1][2];

			for (int j = 0; j < matrizCodigos.length; j++) {
				if (matrizCodigos[j][i] == 0) {
					matriz[0][0] += 1;

				} else {
					matriz[0][1] += 1;
				}
			}
			posicion++;
			if (matriz[0][0] > matriz[0][1]) {
				resultado[i] = 0;
			} else {
				resultado[i] = 1;

			}
		}

		return resultado;
	}

	public long solucionParte1() {
		String tasaGamma = "";
		String tasaEpsilon = "";
		// creamos un array para devolver gamma desde la matriz codigos
		int[] gamma = devolverBinario(matrizCodigos);
		tasaGamma = Arrays.toString(gamma);// Pasamos el array a String para el gamma

		tasaEpsilon = darVueltaBinario(gamma);// damos la vuelta a la gamma para conseguir el epsilon lo pasamos
		// directamente a String

		// reemplazamos los caracteres del array para dejar solo el numero
		tasaGamma = tasaGamma.replace("[", "").replace("]", "").replace(",", "").replace(" ", "");

		long totalGamma = convertirBinarioAdecimal(tasaGamma);// Convertimos el numero gamma a decimal
		long totalEpsilon = convertirBinarioAdecimal(tasaEpsilon);// convertimos el numero epsilon a decimal
		consumoEnergia = totalGamma * totalEpsilon;// multiplicamos

		return consumoEnergia;
	}
	
	
	
	public long solucionParte2() {
		this.matrizCodigos=this.devolverGeneradorOxigeno();
		int[] arrayOxigeno = devolverBinario(matrizCodigos);
		String totalOxigeno = Arrays.toString(arrayOxigeno);
		totalOxigeno = totalOxigeno.replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
		
		this.clasOxigeno = convertirBinarioAdecimal(totalOxigeno);// Convertimos el numero gamma a decimal
		
		
		this.matrizCodigos=this.devolverDepuradorCO2();
		int[] arrayCo2 = devolverBinario(matrizCodigos);
		
		String totalCo2 = Arrays.toString(arrayCo2);
		totalCo2 = totalCo2.replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
		this.clasCO2 = convertirBinarioAdecimal(totalCo2);// Convertimos el numero gamma a decimal
		this.clasSoporteVital=this.clasCO2*this.clasOxigeno;
		return clasSoporteVital;
		
	}

	
	public int[][] devolverGeneradorOxigeno() {
	  this.matrizCodigos=this.devolverMatriz(codigos);
		int posicion = 0;
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
		return matrizCodigos;
		
		
	}
	
	
	public int[][] devolverDepuradorCO2(){
		  this.matrizCodigos=this.devolverMatriz(codigos);

		int posicion=0;
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
		return this.matrizCodigos;
		
	}

	public int[][] ObtenerMatrizPosicion(int[][] matrizCodigos, int posicion, int numero) {
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
