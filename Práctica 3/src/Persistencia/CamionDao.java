package Persistencia;

import Dominio.*;
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
		
		Statement stm= null;
		Connection con=null;
		
		String sql="INSERT INTO Vehiculos values ('" + camiones.getMatricula() + "','" + camiones.getMarca() + "','" + camiones.getModelo() + "','" + camiones.getColor() + "'," + camiones.getPrecio() + ")";
		String sql2="INSERT INTO Camiones values ('" + camiones.getMatricula() + "'," + ((Camion)camiones).getCapacidadcarga() + ")";

		try {			
			con=Conexion.conectar();
			stm= con.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			registrar=true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase CamionesDaoImple, método registrar");
			e.printStackTrace();
		}
		
		
		return registrar;
	}
 
	
	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Camiones ORDER BY matricula";

		ArrayList<Vehiculo> listavehiculos = new ArrayList<Vehiculo>();

		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				listavehiculos.add(new Camion(rs.getString(1), "" , "" , "", 0 , rs.getInt(2)));
			}
			stm.close();
			rs.close();
			co.close();
			for (int i = 0; i < listavehiculos.size(); i++) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculos WHERE matricula='" + listavehiculos.get(i).getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				listavehiculos.get(i).setMarca(rs.getString(2));
				listavehiculos.get(i).setModelo(rs.getString(3));
				listavehiculos.get(i).setColor(rs.getString(4));
				listavehiculos.get(i).setPrecio(rs.getDouble(5));

				stm.close();
				rs.close();
				co.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: Clase CamionesDaoImple, método obtener");
			e.printStackTrace();
		}

		return listavehiculos;
	}
 

	
	public Vehiculo leer(String matricula) throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;

		Vehiculo leerVehiculo = null;
		String sql = "SELECT * FROM Camiones WHERE matricula='" + matricula + "'";
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				leerVehiculo =	new Camion(rs.getString(1), "" , "" , "" , rs.getInt(2), 0);

			}
			stm.close();
			rs.close();
			co.close();
			if(leerVehiculo!=null) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculos WHERE matricula='" + leerVehiculo.getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				leerVehiculo.setMarca(rs.getString(2));
				leerVehiculo.setModelo(rs.getString(3));
				leerVehiculo.setColor(rs.getString(4));
				leerVehiculo.setPrecio(rs.getDouble(5));

				}

		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}
		return leerVehiculo;
	}
	public boolean actualizar(Vehiculo camiones, String matricula) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
		if(camiones.getMatricula().equals(matricula)) {			
			String sql="UPDATE Vehiculos SET matricula ='" + camiones.getMatricula() + "', marca= '"+camiones.getMarca()+"', modelo= '"+camiones.getModelo()+"', color= '"+camiones.getColor()+"', precio= "+camiones.getPrecio()+" WHERE matricula='"+camiones.getMatricula()+"'";
			String sql2="UPDATE Camiones SET matricula ='" + camiones.getMatricula() + "', capacidadcarga= "+((Camion)camiones).getCapacidadcarga()+" WHERE matricula='"+camiones.getMatricula()+"'";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			actualizar=true;
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}		
		}else {
		String sql="INSERT INTO Vehiculos values ('" + camiones.getMatricula() + "','" + camiones.getMarca() + "','" + camiones.getModelo() + "','"+camiones.getColor() + "'," + camiones.getPrecio() + ")";
		String sql2="INSERT INTO Camiones values ('" + camiones.getMatricula() + "'," + ((Camion)camiones).getCapacidadcarga() + ")";
		String sql3="DELETE FROM Camiones WHERE matricula='"+matricula+"'";
		String sql4="DELETE FROM Vehiculos WHERE matricula='"+matricula+"'";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			stm.execute(sql3);
			stm.execute(sql4);

			actualizar=true;
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}	
		}
			
		
		return actualizar;
	}
 
	
	public boolean eliminar(Vehiculo camiones) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean eliminar=false;
		
		String sql="DELETE FROM Camiones WHERE matricula='"+camiones.getMatricula()+"'";
		String sql2="DELETE FROM Vehiculos WHERE matricula='"+camiones.getMatricula()+"'";

		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}				
			
		return eliminar;
	}

	public boolean eliminarTodo() throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean eliminar=false;
				
		String sql="DELETE FROM Camiones";
		
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}		
		return eliminar;		
	}

}
