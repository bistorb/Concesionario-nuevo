package Persistencia;

import java.util.ArrayList;

import Dominio.*;

abstract public class VehiculoDao {

	public VehiculoDao() {
		
	}
	
	abstract public boolean insertar(Vehiculo Vehiculos) throws ClassNotFoundException ;
	abstract public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException;
	abstract public Vehiculo leer(String matricula) throws ClassNotFoundException ;
	abstract public boolean actualizar(Vehiculo vehiculos, String matricula) throws ClassNotFoundException;
	abstract public boolean eliminar(Vehiculo vehiculos) throws ClassNotFoundException ;
	abstract public boolean eliminarTodo() throws ClassNotFoundException;
}
