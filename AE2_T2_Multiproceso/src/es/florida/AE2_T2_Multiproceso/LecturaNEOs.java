package es.florida.AE2_T2_Multiproceso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class LecturaNEOs {

//    long inicio = System.currentTimeMillis();
//    
//    Thread.sleep(2000);
//     
//    long fin = System.currentTimeMillis();
//     
//    double tiempo = (double) ((fin - inicio)/1000);
//     
//    System.out.println(tiempo +" segundos");
	
	
	//Creamos un formato para solo dos decimales.
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	public static void main(String[] args) {
		
		mostrarFichero(args[0]);
	}

public static ArrayList<String> leerInfoNEO(String fileName) throws IOException {
		
		ArrayList<String> leerInfoNEO = new ArrayList<String>();
		
		File f = new File(fileName);
		FileReader fr;
		BufferedReader br;
		
		try {
		
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			String linea = br.readLine();
			
			while(linea != null) {
				leerInfoNEO.add(linea);
				
				//Generamos array con la información de cada NEO separada.
				String[] asteroidInfo = linea.split(",");
				
				//Proceso de escritura de los archivos NEO
				File neoFile = new File(asteroidInfo[0]);
				FileWriter fw = new FileWriter(neoFile);
				BufferedWriter bw = new BufferedWriter(fw);
				
				double probabilidadColision = ProbabilidadImpacto(Double.parseDouble(asteroidInfo[1]), Double.parseDouble(asteroidInfo[2]));
				
				String impactProb = asteroidInfo[0] + " => " + probabilidadColision;
				bw.write(impactProb);
				
				bw.close();
				fw.close();
				
				System.out.println("Probabilidad de colisión de " + asteroidInfo[0] + ": " + df.format(probabilidadColision));
				
				if(probabilidadColision > 10) {
					System.err.println("ALERTA MUNDIAL: Alta probabilidad de colisión con " + asteroidInfo[0] + "(" + df.format(probabilidadColision) + ")");
				}
				else {
					System.out.println("Situación estable con " + asteroidInfo[0] + ". Podemos respirar tranquilos...");
				}
				
				
				
				linea = br.readLine();
			}
			
			br.close();
			fr.close();			
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return leerInfoNEO;
	}
	
	public static void mostrarFichero(String fichero) {
		
		ArrayList<String> arrayLineas;
		try {
			arrayLineas = leerInfoNEO(fichero);
//			for(String linea : arrayLineas) {
//				System.out.println(linea);
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static double ProbabilidadImpacto(double posicionNEO, double velocidadNEO) {
		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
		posicionNEO = posicionNEO + velocidadNEO * i;
		posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
		Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
		
		return resultado;
	}
}
