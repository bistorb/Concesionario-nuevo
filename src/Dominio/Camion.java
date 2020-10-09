package Dominio;

import java.io.*;
import java.util.*;

import Persistencia.CamionDao;
import Persistencia.*;


public class Camion extends Vehiculo{
 	private int capacidadcarga;
 	private CamionDao camionesdao;
 	
	public Camion(String matricula, String marca, String modelo, String color, double precio, int capacidadcarga) {
		super(matricula, marca, modelo, color, precio);
		this.capacidadcarga = capacidadcarga;
		camionesdao = new CamionDao();
	} 
	
	public Camion() {
		this.camionesdao = new CamionDao();
	}
	
	public int getCapacidadcarga() {
		return capacidadcarga;
	}

	public void setCapacidadcarga(int capacidadcarga) {
		this.capacidadcarga = capacidadcarga;
	}

	public CamionDao getCamionesdao() {
		return camionesdao;
	}

	public void setCamionesdao(CamionDao camionesdao) {
		this.camionesdao = camionesdao;
	}

	@Override
	public String toString() {
		return "Camion  [Matricula: " + matricula + " | Marca: " + marca  + " | " + " Modelo: " + modelo + " | " + " Color: " + color + " | " + " Precio: " + precio + "€ | " + " Capacidad de carga:" + capacidadcarga + "KG]";
	}
	
	public ArrayList<Vehiculo> leer() {
		return camionesdao.leer();
	}
	public void escribir(ArrayList<Vehiculo> vehiculos){
		camionesdao.escribir(vehiculos);
	}

	public Vehiculo leerVehiculos(String matricula) throws ClassNotFoundException {
		return camionesdao.leer1(matricula);
	}


	
}
