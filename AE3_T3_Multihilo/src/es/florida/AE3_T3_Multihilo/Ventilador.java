package es.florida.AE3_T3_Multihilo;


public class Ventilador {
	
	boolean estadoVentilador = false;
	int tiempoFuncionamiento = 5000;
	
	Ventilador(boolean estadoVentilador, int tiempoFuncionamiento){
		this.estadoVentilador = estadoVentilador;
		this.tiempoFuncionamiento = tiempoFuncionamiento;
	}
	
	public void encenderVentilador() {
		while(true) {
			synchronized (this) {
				try {
					
					while(estadoVentilador) wait();
					
					System.err.println("Ventilador apagada durante " + tiempoFuncionamiento / 1000 + " segundos.");
					
					Thread.sleep(tiempoFuncionamiento);
					
					estadoVentilador = true;
					notify();
					
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void apagarVentilador() {
		while(true) {
			synchronized (this) {
				try {
					
					while(!estadoVentilador) wait();
					
					System.err.println("Ventilador encendido durante " + tiempoFuncionamiento / 1000 + " segundos.");
					
					Thread.sleep(tiempoFuncionamiento);
					
					estadoVentilador = false;
					notify();
					
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}	
}
