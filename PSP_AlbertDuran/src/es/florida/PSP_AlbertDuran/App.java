package es.florida.PSP_AlbertDuran;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) throws IOException {
		
//		sayHello();
//		imprimirArrayAlumnos();
//		imprimirListaAlumnos();
//		System.out.print(sumaNumerosPares(10));
//		System.out.print(calculoFactorial(15));
//		int[] intArray = {10, 15, 8, 13};	
//		System.out.print(compruebaNumeroMayor(intArray));
//		System.out.print(sumaCincoNumeros(1,2,3,4,5));
		mostrarNivelSalarial();
	}
	
	public static void sayHello() {
		System.out.println("Hola Mundo");
	}
	
	public static void imprimirArrayAlumnos() {
		
		System.out.println("Ejercicio 2.a");
		
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
		
		System.out.println("Ejercicio 2.b");
		
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
	
	public static int sumaNumerosPares(int limitNum) {
		
		int suma = 0;
		
		for(int i = 0; i < limitNum; i++) {
			if (i%2==0)
			    suma += i;
		}
		
		return suma;
	}
	
	public static float calculoFactorial(float num) {
		 if (num==0)
			 return 1;
		 else
			 return num * calculoFactorial(num-1);
	}
	
	public static int compruebaNumeroMayor(int[] array)
	{
		int numMayor = 0;
		
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] > numMayor)
			{
				numMayor = array[i];
			}
		}
		return numMayor;
	}
	
	public static int sumaCincoNumeros(int num1, int num2, int num3, int num4, int num5)
	{
		int suma = 0;
		int[] numArray = { num1, num2, num3, num4, num5 };
		
		for(int i = 5; i > 0; i--) {
			System.out.println(numArray[i-1]);
			suma += numArray[i-1];
		}
		
		return suma;
	}
	
public static void mostrarNivelSalarial() throws IOException {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		System.out.print("Introduce el nombre del trabajador: ");
		String nombre = br.readLine();
		System.out.print("Introduce los años de experiencia: ");
		int anosExp = Integer.parseInt(br.readLine());
		
		if(anosExp < 1)
			System.out.print(nombre + ": Desarrollador Junior L1 – 15000-18000”");
		else if(anosExp >= 1 && anosExp <= 2)
			System.out.print(nombre + ": Desarrollador Junior L2 – 18000-22000");
		else if(anosExp >= 3 && anosExp < 5)
			System.out.print(nombre + ": Desarrollador Senior L1 – 22000-28000");
		else if(anosExp >= 5 && anosExp <= 8)
			System.out.print(nombre + ": Desarrollador Senior L2 – 28000-36000");
		else if(anosExp > 8)
			System.out.print(nombre + ": Analista / Arquitecto. Salario a convenir en base a rol");		
		
		isr.close();
		br.close();
	}
}
