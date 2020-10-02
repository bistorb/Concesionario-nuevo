package Persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Dominio.*;


public class EmpleadoDao {

	public EmpleadoDao() {

	}

	public boolean comprobar(String usuario, String contraseña) throws ClassNotFoundException {
		boolean verificar = false;
		Statement stm = null;
		Connection con = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM Empleados WHERE Usuario = '" + usuario + "' AND Contraseña = '" + contraseña + "'";

		try {
			con = Conexion.conectar();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			if (rs.next()) {
				verificar = false;
				System.out.println("\n¡Bienvenido al mejor concesionario de Europa!");
			} else {
				verificar = true;
				System.out.println("Se han introducido de forma incorrecta los datos. Vuélvalo a intentar.");
			}
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase EmpleadoDao, método verificar");
			System.err.println("Se han introducido mal los datos");
			e.printStackTrace();
			verificar = true;
		}
		return verificar;
	}

	// abstract ArrayList<Empleado> leerEmpleados() throws FileNotFoundException;
	// public abstract void escribirEmpleados(ArrayList<Empleado> empleados) throws
	// FileNotFoundException ,IOException;

	// solo tiene un metodo que es leer el nombre y el empleado
	// pasar usuario y contraseña
	// where usuario a where contraseña
}
