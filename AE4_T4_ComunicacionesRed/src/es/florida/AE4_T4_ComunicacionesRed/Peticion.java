package es.florida.AE4_T4_ComunicacionesRed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Peticion implements Runnable {
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;
	
	public Peticion(Socket socket){
		this.socket= socket;
	}
	
	/**
	 * Método que nos devuelve la contraseña encriptada.
	 * @param password
	 * @return
	 */
	public String passwordEncriptada(Password password) {
		String resultado;
		char[] encyptedChars = new char[password.password.length()];
 		//Recorremos la password para identificar los char.
		for (int n = 0; n < password.password.length(); n++) 
		{
			char c = password.password.charAt(n); 
		
			//Obtenemos el char posterior para la encriptación.
			char currentChar = (char)((int)c + 1);
			encyptedChars[n] = currentChar;
		}
		
		resultado = new String(encyptedChars);
		
		return resultado;
	}
	
	/**
	 * Método que devuelve la contraseña encryptada mediante algoritmo MD5.
	 * @param pass
	 * @return
	 */
	public static String getMD5EncryptedValue(Password pass) {
        final byte[] defaultBytes = pass.password.getBytes();
        String result = "";
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();

            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            result = hexString + "";
        } catch (final NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return result;
    }

	
	public void run() {
		try{
			ObjectOutputStream outObjeto= new ObjectOutputStream(socket.getOutputStream());
			Password p= new Password("","");
			outObjeto.writeObject(p);
			System.err.println("SERVIDOR >> Enviando objeto a cliente");
			ObjectInputStream inObjeto= new ObjectInputStream(socket.getInputStream());
			Password pMod= (Password) inObjeto.readObject();
			System.err.println("SERVIDOR >> Recibiendo objeto del cliente");
			
			System.err.println("SERVIDOR >> Encriptando contraseña...");
			String encryptedPassword = passwordEncriptada(pMod);
			String encryptedPassword2 = getMD5EncryptedValue(pMod);
			pMod.setEncryptedPassword(encryptedPassword2);
			
			System.out.println("Password = " + pMod.getPassword());
			System.out.println("EncryptedPassword = " + pMod.getEncryptedPassword());
			
			outObjeto.writeObject(pMod);
			System.err.println("SERVIDOR >> Enviando objeto a cliente");
			
			outObjeto.close();
			inObjeto.close();
//			socket.close();
//			servidor.close();

		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR >>> Error.");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("SERVIDOR >>> Classe Password no correcta");
		}
	}
}






