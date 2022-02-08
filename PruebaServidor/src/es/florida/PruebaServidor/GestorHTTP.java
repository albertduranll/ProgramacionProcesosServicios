package es.florida.PruebaServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler{

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		
		String requestParamValue = null;
		if("GET".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handleGetRequest(httpExchange);
			handleGETResponse(httpExchange,requestParamValue);
		} else if("POST".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handlePostRequest(httpExchange);
			handlePOSTResponse(httpExchange,requestParamValue);
		}
	}
	
	private String handleGetRequest(HttpExchange httpExchange) {
		System.out.println("Recibida URI tipo GET: " + httpExchange.getRequestURI().toString());
		return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
	}
	
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
		//return httpExchange.getRequestBody().toString()
		return sb.toString();
	}
	
	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse= "<html><body>Hola "+requestParamValue+"</body></html>";
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		System.out.println("Devuelve respuesta HTML: " + htmlResponse);
	}
	
	private void handlePOSTResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse= "Parametro/s POST: " + requestParamValue + " -> Se procesara por parte del servidor";
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		System.out.println("Devuelve respuesta HTML: " + htmlResponse);
	}
}
