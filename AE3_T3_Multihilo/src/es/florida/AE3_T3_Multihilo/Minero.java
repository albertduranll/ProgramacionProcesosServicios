package es.florida.AE3_T3_Multihilo;

public class Minero{

	int bolsa = 0; //Donde guarda los recursos que recolecta.
	int tiempoExtraccion; //Tiempo de trabajo necesario para extraer un recurso.
	
	static int totalRecursosRecogidos = 0;

	Minero(int bolsa, int tiempoExtraccion){
		this.bolsa = bolsa;
		this.tiempoExtraccion = tiempoExtraccion;
	}
	
	public void initialize() {
		bolsa = 0;
	}
	
	synchronized public void extraerRecurso(Mina mina) {		
			
		try {			
			while(totalRecursosRecogidos < 1000) {
//				if(mina.stock > 0) {	
						bolsa += 10;
						totalRecursosRecogidos += 10;
						mina.stock = mina.stock - 10;
						System.out.println(Thread.currentThread().getName() + " => +1 recurso! (" + bolsa + ")");
//						System.err.println("STOCK DE MINA => " + mina.stock);
						
						Thread.sleep(1000);
//				}
			}
			System.err.println(Thread.currentThread().getName() + ": Recursos agotados en esta mina. Bolsa => " + bolsa);			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
