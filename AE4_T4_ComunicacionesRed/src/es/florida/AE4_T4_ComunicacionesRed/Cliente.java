package es.florida.AE4_T4_ComunicacionesRed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] arg) throws UnknownHostException, IOException, ClassNotFoundException{
		String host= "localhost";
		int puerto= 1234;
		System.out.println("CLIENTE >> Arranca cliente");
		Socket cliente= new Socket(host,puerto);
		ObjectInputStream inObjeto= new ObjectInputStream(cliente.getInputStream());
		Password p= (Password) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Escribe una contraseña: ");
		String pass = input.next();
		p.setPassword(pass);
		p.setEncryptedPassword("");
		ObjectOutputStream pMod= new ObjectOutputStream(cliente.getOutputStream());
		pMod.writeObject(p);
		System.out.println("CLIENTE >> Envio al servidor: "+p.getPassword());
		
		p = (Password)inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor");
		System.out.println("Contraseña: "+ p.getPassword());
		System.out.println("Contraseña encriptada: " + p.getEncryptedPassword());
		
		inObjeto.close();
		pMod.close();
		cliente.close();
		}
}