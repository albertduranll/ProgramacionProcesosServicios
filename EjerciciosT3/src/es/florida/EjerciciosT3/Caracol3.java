package es.florida.EjerciciosT3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Caracol3 implements Runnable{

	double distancia = 1;
	double velocidad;
	String nombre;
	
	static String nombreFicheroLlegada =  "ficheroLlegada.txt";
	
	Caracol3(String nombre, double velocidad)
	{
		this.nombre = nombre;
		this.velocidad = velocidad;
	}
		
	public static void main(String[] args) 
	{
		File ficheroLlegada = new File(nombreFicheroLlegada);
		ficheroLlegada.delete();
		
		String[] arrayNombres = {"Caracol_1", "Caracol_2", "Caracol_3", "Caracol_4", "Caracol_5"};
		double velocidad = 0.01;
		int[] arrayPrioridades = {1,2,3,4,10};
		
		Caracol3 objetoCaracol;
		Thread hiloCaracol;
		
		for(int i = 0; i < arrayNombres.length; i++) {
			objetoCaracol = new Caracol3(arrayNombres[i], velocidad);
			hiloCaracol = new Thread(objetoCaracol);
			hiloCaracol.setPriority(arrayPrioridades[i]);
			hiloCaracol.start();
		}
		
		boolean ficheroExiste = false;
		FileReader fr;
		while(!ficheroExiste) {
			try {
				fr = new FileReader(ficheroLlegada);
				BufferedReader br = new BufferedReader(fr);
				String nombre = br.readLine();
				System.err.println("CARRERA FINALIZADA: ha ganado " + nombre);
				
				br.close();
				fr.close();
				ficheroExiste = true;
			} catch(IOException e) {
//				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() 
	{
		File ficheroLlegada = new File(nombreFicheroLlegada);
		
		double avance = 0;
		double porcentaje = 0;
		
		System.out.println(nombre + " inicia la carrera.");
		
		while(avance < distancia)
		{
			if(ficheroLlegada.exists()) {
				System.out.println(nombre + " > Alguien ha ganado la carrera. Abandono en el " + String.format("%.0f", porcentaje) + "%");
				avance = distancia;
			}
			else {
	
				avance += velocidad * 1;
				porcentaje = 100 * avance / distancia;
				
				System.out.println(nombre + " > " + String.format("%.0f", porcentaje) + "%");
			}
			

		}
		
		if(!ficheroLlegada.exists()) {

			FileWriter fw;
			
			try {
				fw = new FileWriter(ficheroLlegada);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(nombre);
				bw.close();
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			System.err.println(nombre + " ha llegado a la meta!");
		}
	}

}
