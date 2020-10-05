package Persistencia;

import java.io.*;
import java.util.ArrayList;
import Dominio.*;


public class EmpleadoDao {

	public EmpleadoDao() {

	}

	public boolean comprobar(String usuario, String contraseña) throws ClassNotFoundException {
		boolean verificar = false;

		return verificar;
	}

	public ArrayList<Empleado> leer() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("src/Usuarios.txt");
			br = new BufferedReader(fr);
			System.out.println(br.readLine());
			br.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
