package es.florida.PruebaServidor;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PruebaServidor {

	public static void main(String[] args) throws IOException {
		
		String host = "localhost";
		int puerto = 5000;
		InetSocketAddress direccionTCPIP = new InetSocketAddress(host,puerto);
		int backlog = 0;
		HttpServer servidor = HttpServer.create(direccionTCPIP, backlog);
		
		GestorHTTP gestorHTTP = new GestorHTTP();
		String rutaRespuesta = "/test";
		servidor.createContext(rutaRespuesta, gestorHTTP);
		
		//Opción 1 de ejecución: no multihilo
		servidor.setExecutor(null);
		
		//Opción 2 de ejecución: multihilo con ThreadPoolExecutor
		ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		servidor.setExecutor(threadPoolExecutor);
		
		servidor.start();
		System.out.println("Servidor HTTP arranca en el puerto "+ puerto);
	}

}
