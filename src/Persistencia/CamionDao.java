package Persistencia;

import Dominio.*;
import java.io.*;
import java.util.*;


public class CamionDao extends VehiculoDao  {
	
	public CamionDao() {
		
	}
	
	public ArrayList<Vehiculo> leer()  {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		try {
		Scanner in = new Scanner(new FileReader("Camiones.txt"));
		in.next();
		int contador = in.nextInt();
		// Leer profesor

		for (int i = 0; i < contador; i++) {
			in.next();
			String matricula = in.next();
			in.next();
			String marca = in.next();
			in.next();
			String modelo = in.next();
			in.next();
			String color = in.next();
			in.next();
			double precio=in.nextDouble();
			in.next();
			int capacidadcarga = in.nextInt();

			Vehiculo vehiculo = new Camion(matricula, marca, modelo, color, precio, capacidadcarga);
			vehiculos.add(vehiculo);
		}
		in.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero especificado no existe");
		} catch (IOException e) {
			System.out.println("Excepcion de entrada/salida:" + e.toString());
			System.out.println(e.getMessage());
		}
		return vehiculos;
	}

	public void escribir(ArrayList<Vehiculo> vehiculos)  {
		try {
		PrintWriter out = new PrintWriter(new FileWriter("Camiones.txt"));
		out.println("Camiones:");
		out.println(vehiculos.size());
		for (int i = 0; i < vehiculos.size(); i++) {
			out.println("Matricula:");
			out.println(vehiculos.get(i).getMatricula());
			out.println("Marca:");
			out.println(vehiculos.get(i).getMarca());
			out.println("Modelo:");
			out.println(vehiculos.get(i).getModelo());
			out.println("Color:");
			out.println(vehiculos.get(i).getColor());
			out.println("Precio:");
			out.println(vehiculos.get(i).getPrecio());
			out.println("Capacidad de carga:");
			String capacidadcarga=((Camion)vehiculos.get(i)).getCapacidadcarga()+"";
			out.println(capacidadcarga);

		}
		out.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero especificado no existe");
		} catch (IOException e) {
			System.out.println("Excepcion de entrada/salida:" + e.toString());
			System.out.println(e.getMessage());
		}
	}
	
	public Vehiculo leer1(String matricula) throws ClassNotFoundException {
        return null;
    }

}
