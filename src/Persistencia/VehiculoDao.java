package Persistencia;

import java.util.ArrayList;

import Dominio.*;

abstract public class VehiculoDao {

	public VehiculoDao() {
		
	}
	
	abstract public ArrayList<Vehiculo> leer();
	abstract public Vehiculo leerVehiculos(String matricula);
	abstract public void escribir(ArrayList<Vehiculo> vehiculos);	
}
