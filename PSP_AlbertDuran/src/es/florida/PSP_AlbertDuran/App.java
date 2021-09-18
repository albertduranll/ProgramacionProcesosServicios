package es.florida.PSP_AlbertDuran;

import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) {
		
//		sayHello();
//		imprimirArrayAlumnos();
		imprimirListaAlumnos();
	}
	
	public static void sayHello() {
		System.out.println("Hola Mundo");
	}
	
	public static void imprimirArrayAlumnos() {
		
		String[] arrayAlumnos = {
		"Albert",
		"Laura",
		"Alvaro",
		"Clara",
		"Xavi",
		"Cristina"
		};

		for(int i = 0; i < arrayAlumnos.length; i++) {
			System.out.println(arrayAlumnos[i]);
		}
	}
	
	public static void imprimirListaAlumnos() {
		
		 List<String> listAlumnos = new ArrayList<String>(); 
		 
		 listAlumnos.add("Albert");
		 listAlumnos.add("Laura");
		 listAlumnos.add("Alvaro");
		 listAlumnos.add("Clara");
		 listAlumnos.add("Xavi");
		 listAlumnos.add("Cristina");
		 
		 for(String alumno:listAlumnos)  
			  System.out.println(alumno);  
	}
}
