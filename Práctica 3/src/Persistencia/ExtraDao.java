package Persistencia;

import java.io.*;
import java.util.*;

import Dominio.*;

public class ExtraDao {
	public ExtraDao() {

	}
	
	public ArrayList<Extra> leerExtras() throws IOException {
		ArrayList<Extra> extras = new ArrayList<Extra>();
		Scanner in = new Scanner(new FileReader("Extras.txt"));
		in.next();

		int contador = in.nextInt();

		for (int i = 0; i < contador; i++) {
			in.next();
			int id = in.nextInt();
			in.next();
			String descripcion = in.nextLine();
			Extra ex = new Extra(id, descripcion);
			extras.add(ex);
		}

		return extras;
	
}
	
	public void escribirExtras(ArrayList<Extra> extra) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("Extras.txt"));
		out.println("Extra: ");
		out.println(extra.size());
		
		for (int i = 0; i < extra.size(); i++) {
			out.println("ID: ");
			out.println(extra.get(i).getId());
			out.println("Descripcion: ");
			out.println(extra.get(i).getDescripcion());
		}
		out.close();
		
	}

	public boolean insertar(Extra extras) throws ClassNotFoundException {
		boolean registrar = false;

		return registrar;
	}

	public ArrayList<Extra> leerTodos() throws ClassNotFoundException {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("Extras.txt");
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
