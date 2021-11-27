package es.florida.EjerciciosT3;

public class ControlSemaforos{

    int estadoSemaforo = 1;
	int tiempoFuncionamiento = 5000;
	
	public static void main(String[] args) {
		 ControlSemaforos semaforos = new ControlSemaforos();
		 
		 Thread s1 = new Thread(new Runnable() {
			 @Override
			 public void run() {
				 semaforos.encenderSemaforo1();
			 }
		 });
		 Thread s2 = new Thread(new Runnable() {
			 @Override
			 public void run() {
				 semaforos.encenderSemaforo2();
			 }
		 });
		 
		 s1.start();
		 s2.start();

	}
	
	public void encenderSemaforo1() {
		while(true) {
			synchronized (this) {
				try {
					
					while(estadoSemaforo == 2) wait();
					
					System.err.print("Semaforo 2 Rojo");
					System.out.println("-> Semaforo 1 verde durante " + tiempoFuncionamiento / 1000 + " segundos.");
					
					Thread.sleep(tiempoFuncionamiento);
					
					estadoSemaforo = 2;
					notify();
					
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void encenderSemaforo2() {
		while(true) {
			synchronized (this) {
				try {
					
					while(estadoSemaforo == 1) wait();
					
					System.err.print("Semaforo 1 Rojo");
					System.out.println("-> Semaforo 2 verde durante " + tiempoFuncionamiento / 1000 + " segundos.");
					
					Thread.sleep(tiempoFuncionamiento);
					
					estadoSemaforo = 1;
					notify();
					
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}	
}
