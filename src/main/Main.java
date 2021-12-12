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

		System.out.println("- DIA 1 - Parte 2: \n" + "�Cu�ntas sumas son mayores que la suma anterior?\n"
				+ dia1Parte2.getTotalesMayores());
		// Dia 2

		Dia2 dia2 = new Dia2(Paths.get("txt\\subMarino.txt"));

		System.out.println("- DIA 2 - Parte 1: \n"
				+ "�Qu� obtienes si multiplicas tu posici�n horizontal final por tu profundidad final?\n"
				+ dia2.solucionParte1());

		System.out.println("- DIA 2 - Parte 2: \n "
				+ "�Qu� obtienes si multiplicas tu posici�n horizontal final por tu profundidad final?\n"
				+ dia2.solucionParte2());

		// Dia 3

		Dia3 dia3 = new Dia3(Paths.get("txt\\diagnosticoBinario.txt"));
		System.out.println(
				"- DIA 3 - Parte 1: \n  �Cu�l es el consumo de energ�a del submarino?\n" + dia3.solucionParte1());

		System.out.println("- DIA 3 - Parte 2: \n  �Cu�l es la clasificaci�n de soporte vital del submarino?\n"
				+ dia3.solucionParte2());

		// Dia 4

		
		Dia4 dia4 = new Dia4(Paths.get("txt\\bingoCalamar.txt"));
		System.out.println(
				"- DIA 4 - Parte 1: \n �Cu�l ser� tu puntuaci�n final si eliges esa tabla?\n" + dia4.solucionParte1());
	Dia4 dia4Parte2 = new Dia4(Paths.get("txt\\bingoCalamar.txt"));
System.out.println("- DIA 4 - Parte 2: \n �Cu�l ser� tu puntuaci�n final si es la ultima tabla la que gana?\n"+dia4Parte2.solucionParte2());
	}

}