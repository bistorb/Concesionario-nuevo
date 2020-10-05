package Dominio;

import java.io.*;
import java.util.*;

import Persistencia.CamionDao;
import Persistencia.EmpleadoDao;

public class Empleado {
	private String usuario;
	private String contraseña;
	private EmpleadoDao empleadodao;
	
	public Empleado(String usuario, String contraseña) {
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.empleadodao = new EmpleadoDao();

	}
	
	public Empleado() {
		this.empleadodao = new EmpleadoDao();

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public EmpleadoDao getEmpleadodao() {
		return empleadodao;
	}

	public void setEmpleadodao(EmpleadoDao empleadodao) {
		this.empleadodao = empleadodao;
	}

	@Override
	public String toString() {
		return "Empleado [usuario=" + usuario + ", contraseña=" + contraseña +"]";
	}
	
	public ArrayList<Empleado>leerEmpleado()throws IOException{
		return empleadodao.leerEmpleado();
	}


	public void escribirEmpleado(ArrayList<Empleado>empleado) throws IOException{
		empleadodao.escribirEmpleados(empleado);
	}
	

	
}
