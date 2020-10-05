package Persistencia;

import Dominio.*;
import java.io.*;
import java.util.*;


public class CamionDao extends VehiculoDao  {
	
	public CamionDao() {
		
	}
	
	public ArrayList<Camion> leerCamiones() throws IOException {
		ArrayList<Camion> camiones = new ArrayList<Camion>();
		Scanner in = new Scanner(new FileReader("Camiones.txt"));
		in.next();

		int contador = in.nextInt();

		for (int i = 0; i < contador; i++) {
			in.next();
			String matricula = in.nextLine();
			in.next();
			String marca = in.nextLine();
			in.next();
			String modelo = in.next();
			in.next();
			String color = in.next();
			in.next();
			double precio = in.nextDouble();
			in.next();
			int capacidadcarga = in.nextInt();
			Camion ca = new Camion(matricula, marca, modelo, color, precio, capacidadcarga);
			camiones.add(ca);
		}

		return camiones;
	
}
	
	public void escribirCamiones(ArrayList<Camion> camiones) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("Camiones.txt"));
		out.println("Camion: ");
		out.println(camiones.size());
		
		for (int i = 0; i < camiones.size(); i++) {
			out.println("Matricula: ");
			out.println(camiones.get(i).getMatricula());
			out.println("Marca: ");
			out.println(camiones.get(i).getMarca());
			out.println("Modelo: ");
			out.println(camiones.get(i).getModelo());
			out.println("Color: ");
			out.println(camiones.get(i).getColor());
			out.println("Precio: ");
			out.println(camiones.get(i).getPrecio());
			out.println("Capacidad de carga: ");
			out.println(camiones.get(i).getCapacidadcarga());
		}
		out.close();
		
	}
	
	public boolean insertar(Vehiculo camiones) throws ClassNotFoundException {
		boolean registrar = false;
		return registrar;
	}
 
	
	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		FileReader fr = null;
		try {
			fr = new FileReader("Camiones.txt");
			int letra = fr.read();
		while(letra!=-1) {
			System.out.print((char)letra);
			letra = fr.read();
		}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	
	public Vehiculo leer(String matricula) throws ClassNotFoundException {
		return null;
	}
	
	public boolean actualizar(Vehiculo camiones, String matricula) throws ClassNotFoundException {
		return false;
	}
	
	public boolean eliminar(Vehiculo camiones) throws ClassNotFoundException {
		return false;	
	}

	public boolean eliminarTodo() throws ClassNotFoundException {
		return false;
	}

}
