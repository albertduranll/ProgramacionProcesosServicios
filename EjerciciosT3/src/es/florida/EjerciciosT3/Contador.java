package es.florida.EjerciciosT3;

public class Contador implements Runnable {

	String nombreHilo;
	int inicioContador;
	int limiteContador;

	Contador(String nombreHilo, int inicioContador, int limiteContador){
		this.nombreHilo = nombreHilo;
		this.inicioContador = inicioContador;
		this.limiteContador = limiteContador;
	}
	
	public static void main(String[] args) {
				
		String [] nombresHilos = {"Contador1", "Contador2", "Contador3", "Contador4", "Contador5"};
		int[] arrayInicios = {1, 11, 21, 31, 41};
		int[] arrayLimites = {10, 20, 30, 40, 50};
		
		for(int i = 0; i < nombresHilos.length; i++) {
			Contador contador = new Contador(nombresHilos[i], arrayInicios[i], arrayLimites[i]);
			Thread hiloContador = new Thread(contador);
			hiloContador.start();
		}	
	}

	@Override
	public void run() 
	{
		for(int i = inicioContador; i <= limiteContador; i++)
			System.out.println("Contador " + nombreHilo + " (de " + inicioContador + " a " + limiteContador + "): " + i);
		}
}
