package es.florida.EjerciciosT3;

public class Caracol implements Runnable {

	double distancia = 1;
	double velocidad;
	String nombre;
	
	Caracol(String nombre, double velocidad)
	{
		this.nombre = nombre;
		this.velocidad = velocidad;
	}
		
	public static void main(String[] args) 
	{
		String[] arrayyNombres = {"Caracol_1", "Caracol_2", "Caracol_3", "Caracol_4", "Caracol_5"};
		double[] arrayVelocidades = { 0.01, 0.011, 0.0099, 0.00999, 0.105 };
		Caracol objetoCaracol;
		Thread hiloCaracol;
		
		for(int i = 0; i < arrayyNombres.length; i++) {
			objetoCaracol = new Caracol(arrayyNombres[i], arrayVelocidades[i]);
			hiloCaracol = new Thread(objetoCaracol);
			hiloCaracol.start();
		}
	}
	
	@Override
	public void run() 
	{
		double avance = 0;
		double porcentaje = 0;
		
		System.out.println(nombre + " inicia la carrera.");
		
		while(avance < distancia)
		{
			avance += velocidad * 1;
			porcentaje = 100 * avance / distancia;
			
			System.out.println(nombre + " > " + String.format("%.0f", porcentaje) + "%");
			
			try
			{
				Thread.sleep(200);
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println(nombre + " ha llegado a la meta!");
	}

}
