package es.florida.EjerciciosT3;

public class RepartoKFC implements Runnable {

	static int alitasConsumidas = 0;
	
	int alitasDisponibles = 100;
	
	public static void main(String[] args) {
		
		RepartoKFC companero = new RepartoKFC();;
		Thread hiloCompanero;
		
		for(int i = 0; i < 30; i++)
		{
			hiloCompanero = new Thread(companero);
			hiloCompanero.setName("Amigo" + (i+1));
			hiloCompanero.start();
		}
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Total alitas consumidas: " + alitasConsumidas);
	}

	synchronized public void consumirAlita(String nombre, int alitas) {
//	public void consumirAlita(String nombre, int alitas) {
//		Mientras queden alitas permite consumir el número que pida cada 
//		compañero (entre 1 y 10 alitas, de forma aleatoria)
				
		if(alitas <= alitasDisponibles) {
			System.out.println(alitas + " alitas se come " + nombre);
			alitasDisponibles -= alitas;
			
			alitasConsumidas += alitas;
		}
		else {
			System.out.println(nombre + " quiere " + alitas + " alitas, pero no quedan suficientes.");
		}
	}
	
	@Override
	public void run() {
		String nombre = Thread.currentThread().getName();
		int alitas = (int) (Math.random() * 10 * 1);
		consumirAlita(nombre, alitas);
	}
}
