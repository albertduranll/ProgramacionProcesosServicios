package es.florida.EjerciciosT4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Lanzador {

	public static void main(String[] args) throws IOException {
		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");
		ServerSocket socketEscucha = null;
		
		try {
			socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Error");
			return;
		}
		
		while (true) {
			Socket conexion = socketEscucha.accept();
			System.err.println("SERVIDOR >>> Conexion recibida!");
			InputStream is = conexion.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(isr);
			System.err.println("SERVIDOR >>> Lee datos para la operacion");
			String linea = bf.readLine();
			String num1 = bf.readLine();
			String num2 = bf.readLine();
			System.err.println("SERVIDOR >>> Realiza la operacion");
//			System.out.println(linea + " | " + num1 + " | " + num2);
			Integer result = ServidorCalculo.calcular(linea, num1, num2);
			System.err.println("SERVIDOR >>> Devuelve resultado");
			OutputStream os = conexion.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(result.toString() + "\n");
			pw.flush();
			System.err.println("SERVIDOR >>> Espera nueva peticion");
		}
	}
}


