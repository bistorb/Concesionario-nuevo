package Dominio;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.CamionDao;
import Persistencia.TurismoDao;
import Persistencia.VehiculoDao;

public class Turismo extends Vehiculo{
	private int numpuertas;
	private Extra extras;
	private TurismoDao turismosdao;
	
	public Turismo(String matricula, String marca, String modelo, String color, double precio, int numpuertas,
			Extra extras) {
		super(matricula, marca, modelo, color, precio);
		this.numpuertas = numpuertas;
		this.extras = extras;
		this.turismosdao = new TurismoDao();
	}
	
	public Turismo() {
		this.turismosdao = new TurismoDao();
	}
	
	public int getNumpuertas() {
		return numpuertas;
	}

	public void setNumpuertas(int numpuertas) {
		this.numpuertas = numpuertas;
	}

	public Extra getExtras() {
		return extras;
	}

	public void setExtras(Extra extras) {
		this.extras = extras;
	}

	public TurismoDao getTurismosdao() {
		return turismosdao;
	}


	public void setTurismosdao(TurismoDao turismosdao) {
		this.turismosdao = turismosdao;
	}
	
	@Override
	public String toString() {
		return "Turismo [Matricula: " + matricula + " | Marca: " + marca + " | " + " Modelo: " + modelo + " | " + " Color: " + color + " | " + " Precio: " + precio + "€ | " + " Número de puertas:" + numpuertas + " | " + " Extras:" + extras + "]";
	}

//	public void escribirVehiculos() throws ClassNotFoundException {
//		turismosdao.escribirVehiculos(this);
//	}

	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		return turismosdao.leerTodos();
	}

	@Override
	public void insertar() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vehiculo leerVehiculos(String matricula) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(String matricula) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarTodo() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}



	
}
