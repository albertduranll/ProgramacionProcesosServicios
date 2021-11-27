package es.florida.EjerciciosT3;

public class MostrarASCII implements Runnable{

	int tipo;
	
	MostrarASCII(int tipo) 
	{
		this.tipo = tipo;
	}
	
	public static void main(String[] args) 
	{
		MostrarASCII object1 = new MostrarASCII(1);
		MostrarASCII object2 = new MostrarASCII(2);
		
		Thread thread1 = new Thread(object1);
		Thread thread2 = new Thread(object2);
		
		thread1.start();
		thread2.start();
	}
	
	@Override
	public void run() 
	{
		if(tipo == 1) {
			for(int i = 1; i < 256; i += 2)
				System.err.println("Caracter impar nº" + i + ": " + (char)i);
		}
		else if(tipo == 2) {
			for(int i = 2; i < 256; i += 2)
				System.out.println("Caracter par nº" + i + ": " + (char)i);
		}
	}

}
