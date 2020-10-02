package Dominio;
import java.util.ArrayList;

import Persistencia.ExtraDao;

public class Extra {
	private int id;
	private String descripcion;
	private ExtraDao extrasdao;
	
	public Extra(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
		extrasdao = new ExtraDao();
	}

	public Extra() {
		extrasdao = new ExtraDao();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ExtraDao getExtrasdao() {
		return extrasdao;
	}

	public void setExtrasdao(ExtraDao extrasdao) {
		this.extrasdao = extrasdao;
	}

	@Override
	public String toString() {
		return "Extras [ID: " + id + " | Descricion: " + descripcion + "]";
	}
	
	public void insertar() throws ClassNotFoundException {
		extrasdao.insertar(this);
	}

	public ArrayList<Extra> leerTodos() throws ClassNotFoundException {
		return extrasdao.leerTodos();

	}
	public Extra leerExtras(int id) throws ClassNotFoundException {
		return extrasdao.leer(id);

	}

	public void actualizar(int id) throws ClassNotFoundException {
		extrasdao.actualizar(this, id);

	}

	public void eliminar() throws ClassNotFoundException {
		extrasdao.eliminar(this);
	}

	public void eliminarTodo() throws ClassNotFoundException {
		extrasdao.eliminarTodo();

	}

	
}
