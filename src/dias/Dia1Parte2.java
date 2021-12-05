package dias;

import java.util.LinkedList;

public class Dia1Parte2 {
	private LinkedList<Integer> Sumas;// Coleccion donde guardaremos las sumas de tres en tres
	private int totalesMayores;// totales que hay incrementados

	public Dia1Parte2() {
		Dia1 dia1 = new Dia1();
		int[] numeros = dia1.getArrayNumeros();
		int controlSumas = -1;// Controlador de sumas

		this.Sumas = new LinkedList<Integer>();
		this.totalesMayores = 0;

		for (int i = 0; i < numeros.length; i++) {

			if (i >= 2) {
				int NumeroAgrupado = numeros[i] + numeros[i - 2] + numeros[i - 1];// sumamos los tres numeros
				Sumas.add(NumeroAgrupado);// Añadimos a la coleccion
				controlSumas++;// Aumentamos el controlador de sumas

				if (i >= 3) {
					// comprobamos si la suma actual es mas
					// grande que la anterior
					if (Sumas.get(controlSumas) > Sumas.get(controlSumas - 1)) {
						totalesMayores++;
					}

				}

			}

		}
	}

	public LinkedList<Integer> getSumas() {
		return Sumas;
	}

	public void setSumas(LinkedList<Integer> sumas) {
		Sumas = sumas;
	}

	public int getTotalesMayores() {
		return totalesMayores;
	}

	public void setTotalesMayores(int totalesMayores) {
		this.totalesMayores = totalesMayores;
	}

}
