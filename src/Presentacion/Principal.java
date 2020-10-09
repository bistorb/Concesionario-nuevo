package Presentacion;

import java.io.*;
import java.util.*;

import Dominio.*;

class excepcionUsuario extends Exception {
}

class excepcionPassword extends Exception {
}

class Principal {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// Cargar los datos

		Empleado emp = new Empleado();
		ArrayList<Empleado> empleados = emp.leerEmpleado();
		emp = login(empleados);
		
		Vehiculo camion = new Camion();
		Vehiculo turismo = new Turismo();
		ArrayList<Vehiculo> camiones = camion.leer();
		ArrayList<Vehiculo> turismos = turismo.leer();
		Extra extra = new Extra();
		ArrayList<Extra> extras= extra.leer();
		Scanner sc = new Scanner(System.in);

		int opcion = 0;
		// Menú principal
		do {
			try {
				//Se imprime el siguiente menú en cuanto el usuario inicie sesión correctamente 
				System.out.println("\n¿Qué desea hacer?");
				System.out.println("01| Mostrar todas los vehiculos");
				System.out.println("02| Buscar un vehiculo");
				System.out.println("03| Insertar un vehiculo");
				System.out.println("04| Modificar un vehiculo");
				System.out.println("05| Eliminar un vehiculo");
				System.out.println("06| Mostrar todos los extras");
				System.out.println("07| Insertar un extra");
				System.out.println("08| Salir");
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					mostrarTodos(camiones, turismos);
					break;
				case 2:
					buscarVehiculo(camiones, turismos, sc);
					break;
				case 3:
					insertarVehiculo(camiones, turismos, extras ,sc);
					break;
				case 4:
					modificarVehiculo(camiones, turismos, extras ,sc);
					break;
				case 5:
					eliminarVehiculo(camiones, turismos, sc);
					break;
				case 6:
					mostrarTodosLosExtras(extras);
					break;
				case 7:
					insertarExtra(extras);
					break;
				case 8:
					System.out.println("¡Hasta la próxima!");
					break;
				default:
					System.out.println("Por favor, introduzca un número entre el 1 y el 13.");
				}

			} catch (InputMismatchException e) {
				System.err.println("Introduzca un número. ");
				sc.nextLine();
			}
		} while (opcion != 13);
	}

	public static void insertarExtra(ArrayList<Extra>extras) throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		boolean seguir = false;
		int id = 0;
		do {
			//Se pide por teclado que introduzca el id del nuevo extra
			seguir = false;
			System.out.println("Introduzca el id");
			id = sc.nextInt();
			Extra existeExtra = new Extra();
			existeExtra = existeExtra.leerExtras(id);

			if (existeExtra != null) {
				//En caso de que el id ya esté en uso 
				if (existeExtra.getId() == id) {
					System.out.println("Id repetido");
					seguir = true;
				}
			}

		} while (seguir);
		do {
			seguir = false;
		} while (seguir);
		//Se pide por teclado la descripcion del nuevo extra
		System.out.println("Introduzca la descripcion");
		sc.nextLine();
		String descripcion = sc.nextLine();
		Extra newExtra = new Extra(id, descripcion);
		//Llama al método insertar en la clase ExtraDao
		newExtra.escribir(extras);
	}

	public static void mostrarTodosLosExtras(ArrayList<Extra>extras) throws ClassNotFoundException {
		System.out.println("Extras");
		for (int i = 0; i < extras.size(); i++) {
			System.out.println(extras.get(i).toString());

		}
	}


	public static void modificarVehiculo(ArrayList<Vehiculo> camiones, ArrayList<Vehiculo> turismos, ArrayList<Extra>extras,Scanner sc) throws IOException, ClassNotFoundException {
		Vehiculo modTurismo = new Turismo();
		Vehiculo modCamion = new Camion();
		//Se identifica al vehiculo por su matricula
		System.out.println("Indica la matricula");
		String Matricula = sc.next();
		modTurismo = modTurismo.leerVehiculos(Matricula);
		modCamion = modCamion.leerVehiculos(Matricula);
		//En caso de que exista el vehículo con la matrícula:
		if (modTurismo != null || modCamion != null) {
			System.out.println("¿Qué deseas modificar?");
			System.out.println("1. Matricula");
			System.out.println("2. Marca");
			System.out.println("3. Modelo");
			System.out.println("4. Color");
			System.out.println("5. Precio");
			if (modTurismo != null) {
				System.out.println("6. Extra");
			}
			if (modCamion != null) {
				System.out.println("6. Capacidad de carga");
			}
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				boolean repetido = false;
				do {
					//Se pide la nueva matrícula por teclado
					repetido = false;
					System.out.println("Introduce la nueva matricula");
					String matricula = sc.next();
					Vehiculo existeTurismo = new Turismo();
					existeTurismo = existeTurismo.leerVehiculos(matricula);
					Vehiculo existeCamion = new Camion();
					existeCamion = existeCamion.leerVehiculos(matricula);
					if (existeTurismo != null) {
						//En caso de que exista la matrícula en el algún turismo, le imprimirá el siguiente mensaje:
						if (existeTurismo.getMatricula().equals(matricula)) {
							System.out.println("Matricula repetida");
							repetido = true;
						}
					} else if (existeCamion != null) {
						//En caso de que exista la matrícula en algún camion, le imprimirá el siguiente mensaje:
						if (existeCamion.getMatricula().equals(matricula)) {
							System.out.println("Matricula repetida");
							repetido = true;
						}
					} else {

						if (modTurismo != null) {
							//En caso de que no exista la matrícula, se modificará la matrícula y le imprimirá el siguiente mensaje: 
							modTurismo.setMatricula(matricula);
							System.out.println("¡Se ha modificado la matrícula con éxito!");
						}
						if (modCamion != null) {
							//En caso de que no exista la matrícula, se modificará la matrícula y le imprimirá el siguente mensaje:
							modCamion.setMatricula(matricula);
							System.out.println("¡Se ha modificado la matrícula con éxito!");
						}
					}
				} while (repetido);
				break;
			case 2:
				//Se pide la nueva marca por teclado 
				System.out.println("Introduce la nueva marca");
				sc.nextLine();
				String marca = sc.nextLine();
				if (modTurismo != null) {
					//LLama a setMarca de Turismo y modifica la marca
					modTurismo.setMarca(marca);
					System.out.println("¡Se ha modificado la marca con éxito!");
				}
				if (modCamion != null) {
					//LLama a setMarca de Camion y modifica la marca
					modCamion.setMarca(marca);
					System.out.println("¡Se ha modificado la marca con éxito!");
				}
				break;
			case 3:
				//Se pide el nuevo modelo por teclado
				System.out.println("Introduce el nuevo modelo");
				sc.nextLine();
				String modelo = sc.nextLine();
				if (modTurismo != null) {
					//Llama a setModelo de Turismo y modifica el modelo
					modTurismo.setModelo(modelo);
					System.out.println("¡Se ha modificado el modelo con éxito!");
				}
				if (modCamion != null) {
					//Llama a setModelo de Camion y modifica el modelo
					modCamion.setModelo(modelo);
					System.out.println("¡Se ha modificado el modelo con éxito!");
				}
				break;
			case 4:
				//Se pide el nuevo color por teclao
				System.out.println("Introduce el nuevo color");
				String color = sc.next();
				if (modTurismo != null) {
					//LLama a setColor de Turismo y modifica el color
					modTurismo.setColor(color);
					System.out.println("¡Se ha modificado el color con éxito!");
				}
				if (modCamion != null) {
					//Llama a setColor de Camion y modifica el color
					modCamion.setColor(color);
					System.out.println("¡Se ha modificado el color con éxito!");
				}
				break;

			case 5:
				boolean seguir = false;
				do {
					seguir = false;
					try {
						//Se pide el nuevo precio por teclado
						System.out.println("Introduce el nuevo precio");
						double precio = sc.nextDouble();
						if (modTurismo != null) {
							//LLama a setPrecio y modifica el precio
							modTurismo.setPrecio(precio);
							System.out.println("¡Se ha modificado el precio con éxito!");
						}
						if (modCamion != null) {
							//Llama a setPrecio y modifica el precio
							modCamion.setPrecio(precio);
							System.out.println("¡Se ha modificado el precio con éxito!");
						}

					} catch (InputMismatchException e) {
						//En caso de introducir algo distinto a números, le imprimirá el siguiente mensaje:
						System.err.println("Introduzce solo números");
						sc.nextLine();
						seguir = true;
					}
					//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
				} while (seguir);
				break;

			case 6:
				//En caso de ser un Turismo a modificar:
				if (modTurismo != null) {
					seguir = false;
					do {
						seguir = false;
						try {
							//Se pide el nuevo extra por teclado
							//mostrarTodosLosExtras();
							System.out.println("Introduce el nuevo extra");
							int extra = sc.nextInt();
							Extra modExtra = new Extra(extra, Matricula);
							modExtra = modExtra.leerExtras(extra);
							if (modExtra == null) {
								//En caso de que el extra no exista, se impimirá el siguiente mensaje:
								seguir = true;
								System.out.println("El extra no existe");

							} else {
								//En caso de que el extra si exista, se modificará y se imprimirá el siguiente mensaje:
								((Turismo) modTurismo).setExtras(modExtra);
								System.out.println("¡Se ha modificado el extra con éxito!");
							}
						} catch (InputMismatchException e) {
							//En caso de introducir algo distinto a números, le imprimirá el siguiente mensaje:
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
						//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
					} while (seguir);
				}
				//En caso de ser un Camion a modificar:
				if (modCamion != null) {
					seguir = false;
					do {
						seguir = false;
						try {
							//Se pide la nueva capacidad de carga por teclado
							System.out.println("Introduce la nueva capacidad de carga");
							int capacidadcarga = sc.nextInt();
							//LLama al método setCapacidadcarga, modifica la carga y le imprimirá el siguiente mensaje:
							((Camion) modCamion).setCapacidadcarga(capacidadcarga);
							System.out.println("¡Se ha modificado la capacidad de carga con éxito!");

						} catch (InputMismatchException e) {
							//En caso de introducir algo distinto a números, le imprimirá el siguiente mensaje:
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
						//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
					} while (seguir);
				}
				break;

			}
			if (modTurismo != null) {
				//LLama al método actualizar de TurismoDao y actualiza la BBDD con las nuevas modificaciones
				modTurismo.escribir(turismos);

			} else if (modCamion != null) {
				//LLama al método actualizar de CamionDao y actualiza la BBDD con las nuevas modificaciones
				modCamion.escribir(camiones);

			}
			//En caso de no encontrar ningún vehículo con dicha matrícula, le imprimirá el siguiente mensaje:
		} else {
			System.out.printf("No existe el vehiculo con la matricula %s\n", Matricula);

		}

	}

	public static void eliminarVehiculo(ArrayList<Vehiculo> camiones, ArrayList<Vehiculo> turismos, Scanner sc) throws IOException, ClassNotFoundException {
		System.out.println("Indica la matricula");
		String matricula = sc.next();
		boolean encontrado = false;
		for (int i = 0; i < camiones.size(); i++) {
			if (camiones.get(i).getMatricula().equals(matricula)) {
				camiones.remove(i);
				Vehiculo delVehiculo = new Camion();
				delVehiculo.escribir(camiones);
				encontrado = true;
			}
		}
		for (int i = 0; i < turismos.size(); i++) {
			if (turismos.get(i).getMatricula().equals(matricula)) {
				turismos.remove(i);
				Vehiculo delVehiculo = new Turismo();
				delVehiculo.escribir(turismos);
				encontrado = true;
			}
		}
		if (encontrado == false) {
			System.out.printf("No existe el vehiculo con matricula %s\n", matricula);
		}
	}

	public static void insertarVehiculo(ArrayList<Vehiculo> camiones, ArrayList<Vehiculo> turismos, ArrayList<Extra>extras,Scanner sc) throws IOException, ClassNotFoundException {
		boolean seguir = false;
		String matricula = "";
		Extra newExtra = new Extra(0, matricula);
		do {
			//Se introducirá la nueva matrícula por teclado
			seguir = false;
			System.out.println("Introduzca el matricula");
			matricula = sc.next();
			//Llama al método leerVehículos para ver si existe la matrícula introducida por teclado
			Vehiculo existeTurismo = new Turismo();
			existeTurismo = existeTurismo.leerVehiculos(matricula);
			Vehiculo existeCamion = new Camion();
			existeCamion = existeCamion.leerVehiculos(matricula);
			if (existeTurismo != null) {
				//En caso de que la matrícula ya exista en Turismo, le imprimirá el siguiente mensaje
				if (existeTurismo.getMatricula().equals(matricula)) {
					System.out.println("Matricula repetida");
					seguir = true;
				}
			}

			if (existeCamion != null) {
				//En caso de que la matrícula ya exista en Camion, le imprimirá el siguiente mensaje:
				if (existeCamion.getMatricula().equals(matricula)) {
					System.out.println("Matricula repetida");
					seguir = true;
				}
			}
			//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
		} while (seguir);
		//Se pide la marca por teclado
		System.out.println("Introduzca la marca");
		sc.nextLine();
		String marca = sc.nextLine();
		//Se pide el modelo por teclado
		System.out.println("Introduzca el modelo");
		String modelo = sc.nextLine();
		//Se pide el color por teclado
		System.out.println("Introduzca el color");
		String color = sc.nextLine();
		double precio = 0;
		do {
			seguir = false;
			try {
				//Se pide el precio por teclado
				System.out.println("Introduzca el precio");
				precio = sc.nextDouble();
			} catch (InputMismatchException e) {
				//En caso de introducir algo distinto a números, le imprimirá el siguiente mensaje:
				System.err.println("Introduzce solo números");
				seguir = true;
				sc.nextLine();
			}
			//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
		} while (seguir);
		int opcion = 0;
		do {
			try {
				//Preguntará al usuario si es Turismo o Camion
				System.out.println("¿Es turismo o camión?\n1. Turismo\n2. Camión");
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				//En caso de introducir algo distinto a números, le imprimirá el siguiente mensaje:
				System.err.println("Introduzce solo números");
				sc.nextLine();
			}
			//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
		} while (opcion != 1 && opcion != 2);
		int extra = 0;
		if (opcion == 1) {
			//En caso de que el usuario introduzca 1:
			do {
				seguir = false;
				try {
					//Se llamará al método mostrarTodosLosExtras para imprimir todos los extras disponibles
					//mostrarTodosLosExtras();
					//Se pedirá el extra por teclado
					System.out.println("Introduzca el extra");
					extra = sc.nextInt();
					newExtra = new Extra();
					newExtra = newExtra.leerExtras(extra);
					if (newExtra == null) {
						//En caso de que el extra no exista le imprimirá el siguiente mensaje: 
						seguir = true;
					}
				} catch (InputMismatchException e) {
					//En caso de introducir algo distinto a números, le imprimirá el siguiente mensaje:
					System.err.println("Introduzce solo números");
					seguir = true;
					sc.nextLine();
				}
				//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
			} while (seguir);
			//Llama al método insertar de la clase Turismo para insertar el nuevo vehiculo
			Vehiculo newVehiculo = new Turismo(matricula, marca, modelo, color, precio, extra, newExtra);
			turismos.add(extra, newVehiculo);
			newVehiculo.escribir(turismos);

		}
		int capacidad = 0;
		if (opcion == 2) {

			do {
				seguir = false;
				try {
					//Se pedirá la capacidad de carga por teclado
					System.out.println("Introduzca la capacidad de carga");
					capacidad = sc.nextInt();
				} catch (InputMismatchException e) {
					//En caso de introducir algo distinto a números, le imprimirá el siguiente mensaje:
					System.err.println("Introduzce solo números");
					seguir = true;
					sc.nextLine();
				}
				//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
			} while (seguir);
			//Llama al método insertar de la clase Camion para insertar el nuevo vehiculo
			Vehiculo newVehiculo = new Camion(matricula, marca, modelo, color, precio, capacidad);
			camiones.add(capacidad, newVehiculo);
			newVehiculo.escribir(camiones);
		}

	}

	public static void buscarVehiculo(ArrayList<Vehiculo> camiones,ArrayList<Vehiculo> turismo, Scanner sc) {
		System.out.println("Indica la matricula");
		String matricula = sc.next();
		boolean encontrado = false;
		for (int i = 0; i < turismo.size(); i++) {
			if (turismo.get(i).getMatricula().equals(matricula)) {
				System.out.println(turismo.get(i).toString());
				encontrado = true;
			}
		}
		for (int i = 0; i < camiones.size(); i++) {
			if (camiones.get(i).getMatricula().equals(matricula)) {
				System.out.println(camiones.get(i).toString());
				encontrado = true;
			}
		}
		if (encontrado == false) {
			System.out.printf("No existe el vehiculo con esa matricula %s\n", matricula);

		}
	}

	public static void mostrarTodos(ArrayList<Vehiculo> camiones, ArrayList<Vehiculo> turismos) {
		System.out.println("Camoines");
		for (int i = 0; i < camiones.size(); i++) {
			System.out.println(camiones.get(i).toString());
		}
		System.out.println("Turismos");
		for (int i = 0; i < turismos.size(); i++) {
			System.out.println(turismos.get(i).toString());
		}
	}

	private static Empleado login(ArrayList<Empleado> empleados) throws ClassNotFoundException {
		boolean login = false;
		String usuario;
		String contraseña;
		Empleado emp = null;
		do {
			//Se pedirá el usuario por teclado
			System.out.println("Indíque el usuario:");
			usuario = sc.next();

			System.out.println("Indíque la contraseña:");
			contraseña = sc.next();
			for(int i=0; i<empleados.size();i++) {
				if(empleados.get(i).getUsuario().equals(usuario)&&contraseña.equals(empleados.get(i).getContraseña())) {
					login=false;
					emp= new Empleado(usuario,contraseña);
					
				}
			}
			//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
		} while (login == true);
		return emp;
	}

}
