package Persistencia;

import java.io.*;
import java.util.*;

import Dominio.*;

public class TurismoDao extends VehiculoDao {
	public TurismoDao() {

	}

	public ArrayList<Vehiculo> leer() {
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		Extra ex= new Extra ();
		ArrayList<Extra> extras = ex.leer();
		try {
		Scanner in = new Scanner(new FileReader("Turismos.txt"));
		in.next();
		int contador = in.nextInt();
		// Leer alumnos

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
			double precio = in.nextDouble();
			in.next();
			int numpuertas = in.nextInt();
			in.next();
			int extra=in.nextInt();
			boolean encontrado=false;
			for(int j=0;j<extras.size();j++) {
				if(extras.get(j).getId()==extra){
					ex=extras.get(j);
					encontrado=true;
					break;
				}
			}
			if(encontrado==false) {
				ex= new Extra (0,"Sin extra");
			}
			Vehiculo vehiculo = new Turismo(matricula,marca,modelo,color,precio,numpuertas,ex);
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
		PrintWriter out = new PrintWriter(new FileWriter("Turismos.txt"));
		out.println("Turismos:");
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
			out.println("Numero de puertas:");
			out.println(((Turismo)vehiculos.get(i)).getNumpuertas());
			out.println("beca:");
			out.println(((Turismo)vehiculos.get(i)).getExtras().getId());

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
