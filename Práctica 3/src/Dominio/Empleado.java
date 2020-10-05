package Dominio;
import java.util.ArrayList;

import Persistencia.CamionDao;
import Persistencia.EmpleadoDao;

public class Empleado {
	private String usuario;
	private String contrase�a;
	private EmpleadoDao empleadodao;
	
	public Empleado(String usuario, String contrase�a) {
		this.usuario = usuario;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public EmpleadoDao getEmpleadodao() {
		return empleadodao;
	}

	public void setEmpleadodao(EmpleadoDao empleadodao) {
		this.empleadodao = empleadodao;
	}

	@Override
	public String toString() {
		return "Empleado [usuario=" + usuario + ", contrase�a=" + contrase�a +"]";
	}
	
	public boolean comprobar(String usuario, String contrase�a) throws ClassNotFoundException {
		return empleadodao.comprobar(usuario, contrase�a);

	}

	public ArrayList<Empleado> leer() {
		return empleadodao.leer();

	}
	
}
