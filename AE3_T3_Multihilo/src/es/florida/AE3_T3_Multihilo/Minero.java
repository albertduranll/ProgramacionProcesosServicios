package es.florida.AE3_T3_Multihilo;

public class Minero {

	int bolsa; //Donde guarda los recursos que recolecta.
	int tiempoExtraccion = 1000; //Tiempo de trabajo necesario para extraer un recurso.

	Minero(int bolsa, int tiempoExtraccion){
		this.bolsa = bolsa;
		this.tiempoExtraccion = tiempoExtraccion;
	}
	
	public void initialize() {
		bolsa = 0;
	}
	
	synchronized public void extraerRecurso(Mina mina, int totalExtraido) {			
		try {
			if(mina.stock > 0) {
				
				bolsa ++;
				
				mina.stock -= bolsa;		
			}
			else {
				System.err.println("Recursos agotados en esta mina. TOTAL RECOLECTADO => " + bolsa);
				
				totalExtraido += bolsa;
			}
			
//			System.out.println(Thread.currentThread().getName() + " extrayendo recursos...");
			
			Thread.sleep(tiempoExtraccion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
