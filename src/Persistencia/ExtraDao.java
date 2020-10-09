package Persistencia;

import java.io.*;
import java.util.*;

import Dominio.*;

public class ExtraDao {
	public ExtraDao() {

	}
	
	public ArrayList<Extra> leer()  {
		ArrayList<Extra> extras = new ArrayList<Extra>();
		try {
		Scanner in = new Scanner(new FileReader("Extras.txt"));
		in.next();
		int contador = in.nextInt();
		// Leer beca

		for (int i = 0; i < contador; i++) {
			in.next();
			int id = in.nextInt();
			in.next();
			String descripción = in.next();
			Extra extra = new Extra(id,descripción);
			extras.add(extra);
		}
		in.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero especificado no existe");
		} catch (IOException e) {
			System.out.println("Excepcion de entrada/salida:" + e.toString());
			System.out.println(e.getMessage());
		}
		return extras;
	}

	public void escribir(ArrayList<Extra> extras){
		try {
		PrintWriter out = new PrintWriter(new FileWriter("Extras.txt"));
		out.println("Extras:");
		out.println(extras.size());
		for (int i = 0; i < extras.size(); i++) {
			out.println("id:");
			out.println(extras.get(i).getId());
			out.println("descripción:");
			out.println(extras.get(i).getDescripcion());

			}
		out.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero especificado no existe");
		} catch (IOException e) {
			System.out.println("Excepcion de entrada/salida:" + e.toString());
			System.out.println(e.getMessage());
		}
	
}
	
	public Extra leer(int id) throws ClassNotFoundException {
        Extra leerExtras = null;

        return leerExtras;
    }
}
