package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.*;

public class TurismoDao extends VehiculoDao {
	public TurismoDao() {

	}

	public boolean insertar(Vehiculo turismos) throws ClassNotFoundException {
		boolean registrar = false;

		Statement stm = null;
		Connection con = null;

		String sql="INSERT INTO Vehiculos values ('" +turismos.getMatricula() + "','" + turismos.getMarca() + "','" + turismos.getModelo() + "','" + turismos.getColor() + "'," + turismos.getPrecio() + ")";
		String sql2="INSERT INTO Turismos values ('" +turismos.getMatricula() + "'," + ((Turismo)turismos).getNumpuertas() + ","+ ((Turismo)turismos).getExtras().getId() + ")";

		try {
			//con = Conexion.conectar();
			stm = con.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			registrar = true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase TurismosDaoImple, método registrar");
			e.printStackTrace();
		}

		
		return registrar;
	}

	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Turismos ORDER BY matricula";

		ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		Extra extras = new Extra(0, sql);
		ArrayList<Integer> auxBeca=new ArrayList<Integer>();
		try {
			//co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				auxBeca.add(rs.getInt(3));	
				listaVehiculos.add(new Turismo(rs.getString(1), "", "", sql, 0,rs.getInt(2), extras));
			}
			stm.close();
			rs.close();
			co.close();
			for (int i=0;i<auxBeca.size();i++) {
				extras= extras.leerExtras(auxBeca.get(i));
				((Turismo)listaVehiculos.get(i)).setExtras(extras);

			}

			for (int i = 0; i < listaVehiculos.size(); i++) {
				//co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculos WHERE Matricula='" + listaVehiculos.get(i).getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				listaVehiculos.get(i).setMarca(rs.getString(2));
				listaVehiculos.get(i).setModelo(rs.getString(3));
				listaVehiculos.get(i).setColor(rs.getString(4));
				listaVehiculos.get(i).setPrecio(rs.getDouble(5));

				stm.close();
				rs.close();
				co.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: Clase TurismoDaoImple, método obtener");
			e.printStackTrace();
		}

		return listaVehiculos;
	}

	public Vehiculo leer(String matricula) throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;

		Vehiculo leerVehiculos = null;
		String sql = "SELECT * FROM Turismos WHERE matricula='" + matricula + "'";
		Extra extras = new Extra(0, sql);
		int auxExtras=0;
	    boolean encontrado=false;
		try {
			//co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
			auxExtras=rs.getInt(2);
			encontrado=true;
			leerVehiculos =	 new Turismo(rs.getString(1), "", "", sql, 0,0, extras);

			}
			stm.close();
			rs.close();
			co.close();
			if(encontrado) {
			extras=extras.leerExtras(auxExtras);
			((Turismo)leerVehiculos).setExtras(extras);
			}
			if (leerVehiculos != null) {
				//co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculos WHERE matricula='" + leerVehiculos.getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				leerVehiculos.setMarca(rs.getString(2));
				leerVehiculos.setModelo(rs.getString(3));
				leerVehiculos.setColor(rs.getString(4));
				leerVehiculos.setPrecio(rs.getDouble(5));

			}
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}
		return leerVehiculos;
	}

	public boolean actualizar(Vehiculo turismos, String matricula) throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;

		boolean actualizar = false;

		if(turismos.getMatricula().equals(matricula)) {			
			String sql="UPDATE Vehiculos SET matricula ='"+turismos.getMatricula()+"', marca= '"+turismos.getMarca()+"', modelo= '"+turismos.getModelo()+"', color= '"+turismos.getColor()+"', precio= "+turismos.getPrecio()+" WHERE matricula='"+turismos.getMatricula()+"'";
			String sql2="UPDATE Turismos SET matricula = '"+turismos.getMatricula()+"', numero de puertas= "+((Turismo)turismos).getNumpuertas()+", extras= "+((Turismo)turismos).getExtras()+" WHERE matricula='"+turismos.getMatricula()+"'";
		try {
			//connect=Conexion.conectar();
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
			String sql="INSERT INTO Vehiculos values ('"+turismos.getMatricula()+"','"+turismos.getMarca()+"','"+turismos.getModelo()+"',"+ "'"+turismos.getColor()+"',"+turismos.getPrecio()+" )";
			String sql2="INSERT INTO Turismos values ('"+turismos.getMatricula()+"',"+((Turismo)turismos).getNumpuertas()+", "+((Turismo)turismos).getExtras().getId()+")";
			String sql3="DELETE FROM Turismos WHERE matricula='"+matricula+"'";
			String sql4="DELETE FROM Vehiculos WHERE matricula='"+matricula+"'";
		try {
			//connect=Conexion.conectar();
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

	public boolean eliminar(Vehiculo turismos) throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;

		boolean eliminar = false;

		String sql="DELETE FROM Turismos WHERE matricula='"+turismos.getMatricula()+"'";
		String sql2="DELETE FROM Vehiculos WHERE matricula='"+turismos.getMatricula()+"'";
		try {
			//connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			eliminar = true;
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}
		
		return eliminar;
	}

	public boolean eliminarTodo() throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;

		boolean eliminar = false;

		String sql = "DELETE FROM Turismos";
		try {
			//connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
			sql = "DELETE FROM Vehiculos";
			//connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
			eliminar = true;
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}
		return eliminar;
	}
}
