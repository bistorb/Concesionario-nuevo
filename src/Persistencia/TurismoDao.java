package Persistencia;

import java.io.*;
import java.util.*;

import Dominio.*;

public class TurismoDao extends VehiculoDao {
	public TurismoDao() {

	}

	public TurismoDao (String matricula, String marca, String modelo, String color, double precio, int numpuertas,
			Extra extras) {
		super();
	}

	public ArrayList<Vehiculo> leerTodos() {
		ArrayList<Vehiculo> turismo = new ArrayList<Vehiculo>();
		ExtraDao extraDao = new ExtraDao();
		ArrayList<Extra> extras;
		Scanner out = null;
		extras = null;
		try {
			extras = extraDao.leerExtras();
			out = new Scanner(new FileReader("Turismos.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.next();
		int contador = out.nextInt();
		// Leer productos
		for (int i = 0; i < contador; i++) {
			out.next();
			String matricula = out.next();
			out.next();
			String marca = out.next();
			out.next();
			String modelo = out.next();
			out.next();
			String color = out.next();
			out.next();
			double precio = out.nextDouble();
			out.next();
			int numpuertas = out.nextInt();
			out.next();
			int idExtra = out.nextInt();
			Extra extra = new Extra();
			for (int j = 0; j < extras.size(); j++) {
				if (idExtra == extras.get(j).getId()) {
					extra = extras.get(j);
				}
			}
			Vehiculo turismos = new Turismo (matricula, marca, modelo, color, precio, numpuertas, extra);
			turismo.add(turismos);

		}

		return turismo;
	}

	public void escribirVehiculos(ArrayList<Vehiculo> vehiculos) throws IOException {
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
			String precio = vehiculos.get(i).getPrecio() + " ";
			precio = precio.replace(".", ",");
			out.println(precio);
			out.println("Numpuertas:");
			out.println(((Turismo) vehiculos.get(i)).getNumpuertas());
			out.println("Extra:");
			out.println(((Turismo) vehiculos.get(i)).getExtras().getId());

		}
		out.close();
	}

	@Override
	public boolean insertar(Vehiculo Vehiculos) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vehiculo leer(String matricula) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(Vehiculo vehiculos, String matricula) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Vehiculo vehiculos) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarTodo() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}


}
