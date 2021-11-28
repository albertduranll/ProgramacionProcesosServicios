package es.florida.AE3_T3_Multihilo;

public class App{
	
	static int totalRecursosRecogidos = 0;
	
	public static void main(String[] args) {
		
		
		Mina mina = new Mina(1000);
		int numeroMineros = 20;
		
		for(int i = 0; i < numeroMineros; i++)
		{
			Minero minero = new Minero(0, 1000);
			minero.initialize();			
			int index = i;
			
			Thread hiloMinero = new Thread(new Runnable() {
				 @Override
				 public void run() {					 
					 while(mina.stock > 0) {						 
						synchronized (this) {
								System.out.println("MINA" + " => " +  mina.stock);
								minero.extraerRecurso(mina, totalRecursosRecogidos);	
								System.out.println("Bolsa minero" + (index+1) + " => " +  minero.bolsa);
						}
						
					 }
				 }
			 });
			hiloMinero.setName("Minero" + (i+1));
			hiloMinero.start();
									
			
		}
		
		if(mina.stock <= 0)
			System.err.println("Total recursos recolectados: " + totalRecursosRecogidos);
	}
}
