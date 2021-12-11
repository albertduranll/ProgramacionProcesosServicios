package es.florida.EjerciciosT4;

public class ServidorCalculo {
	
	public static int extraerNumero(String linea) {
		
		int numero;
		try {
			numero = Integer.parseInt(linea);
		} catch (NumberFormatException e) {
			numero = 0;
		}
		if (numero >= 100000000) { numero = 0; }
			return numero;
	}
	
	public static int calcular(String op,String n1,String n2) {
		int resultado = 0;
		char simbolo = op.charAt(0);
		int num1 = extraerNumero(n1);
		int num2 = extraerNumero(n2);
		if (simbolo == '+') {
			resultado = num1 + num2;
		}
		return resultado;
	}
}