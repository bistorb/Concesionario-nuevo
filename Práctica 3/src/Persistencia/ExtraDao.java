package Persistencia;

import java.io.*;
import java.util.*;

import Dominio.*;

public class ExtraDao {
	public ExtraDao() {

	}

	public boolean insertar(Extra extras) throws ClassNotFoundException {
		boolean registrar = false;

		return registrar;
	}

	public ArrayList<Extra> leerTodos() throws ClassNotFoundException {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("Extra.txt");
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

	public Extra leer(int id) throws ClassNotFoundException {
		Extra leerExtras = null;

		return leerExtras;
	}

	public boolean actualizar(Extra extras, int id) throws ClassNotFoundException {

		boolean actualizar = false;
		return actualizar;
	}

	public boolean eliminar(Extra extras) throws ClassNotFoundException {
		boolean actualizar = false;
		boolean eliminar = false;

		return eliminar;
	}

	public boolean eliminarTodo() throws ClassNotFoundException {
		boolean eliminar = false;
		boolean actualizar = false;

		return eliminar;
	}
}
