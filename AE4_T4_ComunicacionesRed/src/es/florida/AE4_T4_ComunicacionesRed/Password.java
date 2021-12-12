package es.florida.AE4_T4_ComunicacionesRed;

import java.io.Serializable;

@SuppressWarnings ("serial")
public class Password implements Serializable {
	
	String password;
	String encryptedPassword;
		
	public Password() {
		super();
	}
	
	public Password(String password, String encryptedPassword) {
		this.password = password;
		this.encryptedPassword = encryptedPassword;
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
