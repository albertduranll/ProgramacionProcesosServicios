package es.florida.EjerciciosT4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculoHilos{
	public static void main(String[] args) throws IOException{
		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");
		ServerSocket socketEscucha= null;
		try{
			socketEscucha= new ServerSocket(9876);
		} catch(IOException e) {
			System.err.println("SERVIDOR >>> Error");
			return;
		}
		while(true) {
			Socket conexion= socketEscucha.accept();
			System.err.println("SERVIDOR >>> Conexion recibida --> Lanza hilo clase Peticion");
			Peticion p= new Peticion(conexion);
			Thread hilo= new Thread(p);
			hilo.start();
		}
	}
}