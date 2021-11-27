package es.florida.EjerciciosT3;

public class Caracol2 implements Runnable {

	double distancia = 1;
	double velocidad;
	String nombre;
	
	Caracol2(String nombre, double velocidad)
	{
		this.nombre = nombre;
		this.velocidad = velocidad;
	}
		
	public static void main(String[] args) 
	{
		String[] arrayNombres = {"Caracol_1", "Caracol_2", "Caracol_3", "Caracol_4", "Caracol_5"};
		double velocidad = 0.01;
		int[] arrayPrioridades = {1,2,3,4,10};
		
		Caracol2 objetoCaracol;
		Thread hiloCaracol;
		
		for(int i = 0; i < arrayNombres.length; i++) {
			objetoCaracol = new Caracol2(arrayNombres[i], velocidad);
			hiloCaracol = new Thread(objetoCaracol);
			hiloCaracol.setPriority(arrayPrioridades[i]);
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
		}
		
		System.out.println(nombre + " ha llegado a la meta!");
	}

}
