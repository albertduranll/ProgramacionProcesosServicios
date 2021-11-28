package es.florida.AE3_T3_Multihilo;

public class App{
	
	static Mina mina;
	
	public static void main(String[] args) {
		
		int numeroMineros = 20;
			
		Thread hiloMinero;
		
		mina = new Mina(1000);
		
		for(int i = 0; i < numeroMineros; i++)
		{
			Minero minero = new Minero(0, 1000);
			hiloMinero = new Thread(new Runnable() {
				 @Override
				 public void run() {
//					 System.err.println("STOCK DE MINA => " + mina.stock);
					 minero.extraerRecurso(mina);
				 }
			});
			hiloMinero.setName("Minero" + (i+1));
			hiloMinero.start();
		}
		
		
		//Generamos un ventilador para poder controlar su estado mediante distintos threads.
		Ventilador ventilador = new Ventilador(true, 5000);
		 
		 Thread v1 = new Thread(new Runnable() {
			 @Override
			 public void run() {
				 ventilador.encenderVentilador();
			 }
		 });
		 Thread v2 = new Thread(new Runnable() {
			 @Override
			 public void run() {
				 ventilador.apagarVentilador();
			 }
		 });
		 
		 v1.start();
		 v2.start();
		
		 
		 //Damos un tiempo para asegurar que terminan toso los procesos.
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Total recursos recogidos => " + Minero.totalRecursosRecogidos);
	}
}