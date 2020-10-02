package Persistencia;

import Dominio.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CamionDao extends VehiculoDao  {
	
	public CamionDao() {
		
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
