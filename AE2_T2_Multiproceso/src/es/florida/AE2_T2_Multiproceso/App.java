package es.florida.AE2_T2_Multiproceso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		
		String ficheroNEO = "NEOs.txt";
			
		int cores = Runtime.getRuntime().availableProcessors();
//		System.out.println(cores);
		
		String clase = "es.florida.AE2_T2_Multiproceso.LecturaNEOs";
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = clase;
		
		ArrayList<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(ficheroNEO);	
		
		System.out.println("Comando que se pasa a ProcessBuilder: " + command);
		System.out.println("Comando a ejecutar en cmd.exe: " + command.toString().replace(",",""));
		
		ProcessBuilder builder = new ProcessBuilder(command);
		
		try {
			builder.inheritIO().start();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
