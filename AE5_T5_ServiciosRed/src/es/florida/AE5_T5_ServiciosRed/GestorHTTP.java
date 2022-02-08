package es.florida.AE5_T5_ServiciosRed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler{

	public static Integer temperaturaActual = 15;
	public static Integer temperaturaTermostato = 15;
			
	
	/**
	 * Método para actuar según el tipo de petición HTTP que le llega al servidor (GET o POST).
	 */
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		
		String requestParamValue = null;
		//Comprobamos si se trata de una petición GET o POST para procesar como toque.
		if("GET".equals(httpExchange.getRequestMethod())) {
			
			//Compruebo si el el resultado del handleGetRequest devuelve el nombre de la variable temperaturaActual.
			//Sino lo hace devuelve un strinc "err" que nos servira para gestionar la respuesta.
			if("temperaturaActual".equals(handleGetRequest(httpExchange)))
				requestParamValue = temperaturaActual.toString();
			else {
				requestParamValue = "err";
				System.err.println("La respuesta no coincide con el nombre de la variable esperada");
			}
			
			//Respuesta de la petición GET en base a los parametros que le pasamos.
			handleGETResponse(httpExchange, requestParamValue, temperaturaTermostato.toString());
			
		} else if("POST".equals(httpExchange.getRequestMethod())) {
			//Obtenemos el parametro que nos llega desde la petición POST.
			requestParamValue = handlePostRequest(httpExchange);
			//Gestionamos la respuesta de la petición POST en base a al parámetro indicado.
			handlePOSTResponse(httpExchange,requestParamValue);
		}
	}
	
	
	/**
	 * Método que nos devuelve un la información necesaria a partir de la instrucción que se indica después de ? en la URI.
	 * @param httpExchange
	 * @return
	 */
	private String handleGetRequest(HttpExchange httpExchange) {
		System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestURI().toString());
		return httpExchange.getRequestURI().toString().split("\\?")[1];
	}
	
	/**
	 * Método que gestiona la impresión en HTML de la información que recibe por parametros.
	 * @param httpExchange
	 * @param requestParamValue
	 * @param requestParamValue2
	 * @throws IOException
	 */
	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue, String requestParamValue2) throws IOException {
		
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse;
		if(!requestParamValue.equals("err")) {
			htmlResponse= "<html><body><h1>Temperatura actual: "+ requestParamValue +"</h1><h1>Temperatura termostato: " + requestParamValue2 + "</h1></body></html>";			
		}
		else {
			htmlResponse= "<html><body><h1>El parametro introducido en la URL no coincide con el nombre de ninguna variable. </br>Comprueba que lo hayas escrito bien</h1></body></html>";				
		}
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		System.out.println("Devuelve respuesta HTML: " + htmlResponse);
	}
	
	/**
	 * Método que nos devuelve la información que nos llega desde la petición POST.
	 * @param httpExchange
	 * @return
	 */
	private String handlePostRequest(HttpExchange httpExchange) {
		
		System.out.println("Recibida URI tipo POST: " + httpExchange.getRequestBody().toString());
		InputStream is= httpExchange.getRequestBody();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String line;
		
		try {
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	
	/**
	 * Método que gestiona la respuesta de la información recibida por el parametro POST para que el servidor pueda gestionarla.
	 * @param httpExchange
	 * @param requestParamValue
	 * @throws IOException
	 */
	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse= "Parametro/s POST: " + requestParamValue + " -> Se procesara por parte del servidor";
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		System.out.println("Devuelve respuesta HTML: " + htmlResponse);
		
		try {
			temperaturaTermostato = Integer.parseInt(requestParamValue.split("=")[1]);
			regularTemperatura(temperaturaTermostato);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para regular la temperaturaActual para que sea igual a la temperaturaTermostato.
	 * @param tempActual
	 * @param tempTermo
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	private static void regularTemperatura(int tempTermo) throws InterruptedException, IOException {
		
		while(temperaturaActual != tempTermo) {
			
			if(temperaturaActual < tempTermo)
				temperaturaActual++;
			else
				temperaturaActual--;
			
			System.out.println("Regulando temperatura: " + temperaturaActual + " (objetivo => " + tempTermo + ")");
			
			Thread.sleep(5000);
		}
	}
}