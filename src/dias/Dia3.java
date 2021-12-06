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

	public Dia3(Path rutaArchivo) {
		lector = new Lector(rutaArchivo);
		String[] codigos = lector.leerFichero();
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
		long decimal = 0; // será el equivalente en base decimal
		while (numero != 0) {
			// se toma la última cifra

			digito = (int) (numero % 10);
			// se multiplica por la potencia de 2 correspondiente y se suma al número
			decimal = decimal + digito * (int) Math.pow(2, exponente);
			// se aumenta el exponente
			exponente++;
			// se quita la última cifra para repetir el proceso
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

}
