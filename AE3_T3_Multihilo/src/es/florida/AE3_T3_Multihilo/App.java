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
					 minero.extraerRecurso(mina);
				 }
			});
			hiloMinero.setName("Minero" + (i+1));
			hiloMinero.start();
		}
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Total recursos recogidos => " + Minero.totalRecursosRecogidos);
	}
}