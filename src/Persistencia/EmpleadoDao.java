package Persistencia;

import java.io.*;
import java.util.*;
import Dominio.*;

public class EmpleadoDao {

	public EmpleadoDao() {

	}


	public ArrayList<Empleado> leerEmpleado() throws IOException {
		ArrayList<Empleado> empleado = new ArrayList<Empleado>();
		Scanner in = new Scanner(new FileReader("Usuarios.txt"));
		in.next();

		int contador = in.nextInt();

		for (int i = 0; i < contador; i++) {
			in.next();
			String usuario = in.next();
			in.next();
			String contraseña = in.nextLine();
			Empleado emp = new Empleado(usuario, contraseña);
			empleado.add(emp);
		}

		return empleado;

	}
	

	public void escribirEmpleados(ArrayList<Empleado> empleados) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("Usuarios.txt"));
		out.println("Empleado: ");
		out.println(empleados.size());
		
		for (int i = 0; i < empleados.size(); i++) {
			out.println("Nombre: ");
			out.println(empleados.get(i).getUsuario());
			out.println("Contraseña: ");
			out.println(empleados.get(i).getContraseña());
		}
		out.close();
		
	}
	
	// abstract ArrayList<Empleado> leerEmpleados() throws FileNotFoundException;
	// public abstract void escribirEmpleados(ArrayList<Empleado> empleados) throws
	// FileNotFoundException ,IOException;

	// solo tiene un metodo que es leer el nombre y el empleado
	// pasar usuario y contraseña
	// where usuario a where contraseña
}