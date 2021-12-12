package es.florida.AE4_T4_ComunicacionesRed;

import java.io.Serializable;

@SuppressWarnings ("serial")
public class Password implements Serializable {
	
	String password;
	String encryptedPassword;
	String tipoEncriptado;
		
	public Password() {
		super();
	}
	
	public Password(String password, String encryptedPassword, String tipoEncriptado) {
		this.password = password;
		this.encryptedPassword = encryptedPassword;
		this.tipoEncriptado = tipoEncriptado;
	}

	public String getTipoEncriptado() {
		return tipoEncriptado;
	}

	public void setTipoEncriptado(String tipoEncriptado) {
		this.tipoEncriptado = tipoEncriptado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	@Override
	public String toString() {
		return "Password [password=" + password + ", encryptedPassword=" + encryptedPassword + "]";
	}	
}
