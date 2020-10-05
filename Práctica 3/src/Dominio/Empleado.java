package Dominio;
import java.util.ArrayList;

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
	
	public boolean comprobar(String usuario, String contraseña) throws ClassNotFoundException {
		return empleadodao.comprobar(usuario, contraseña);

	}

	public ArrayList<Empleado> leer() {
		return empleadodao.leer();

	}
	
}
