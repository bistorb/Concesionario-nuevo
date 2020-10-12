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
		// Men� principal
		do {
			try {
				//Se imprime el siguiente men� en cuanto el usuario inicie sesi�n correctamente 
				System.out.println("\n�Qu� desea hacer?");
				System.out.println("01| Mostrar todas los vehiculos");
				System.out.println("02| Buscar un vehiculo");
				System.out.println("03| Insertar un vehiculo");
				System.out.println("04| Modificar un vehiculo");
				System.out.println("05| Eliminar un vehiculo");
				System.out.println("06| Mostrar todos los extras");
				System.out.println("07| Insertar un extra");
				System.out.println("08| Eliminar un extra");
				System.out.println("09| Salir");
				
				//Al iniciar sesi�n con �xito, se le dar�n opciones al usuario
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
					modificarUnVehiculo();
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
					eliminarExtra(extras, sc);
					break;
				case 9:
					System.out.println("�Hasta la pr�xima!");
					break;
					//En caso de que el usuario introduzca un n�mero fuera del rango
				default:
					System.out.println("Por favor, introduzca un n�mero entre el 1 y el 9.");
				}

				//En caso de que el usuario introduzca una letra
			} catch (InputMismatchException e) {
				System.err.println("Introduzca un n�mero. ");
				sc.nextLine();
			}
			//Se vuelve a ejecutar si la opci�n introducida est� fuera dentro del rango
		} while (opcion != 9);
	}
	
	public static void eliminarExtra(ArrayList<Extra> extras, Scanner sc) throws IOException {
		//Se pide el ID por teclado
		System.out.println("Indica el Id");
		int id = sc.nextInt();
		boolean encontrado = false;
		//Recorre el fichero de extras
		for (int i = 0; i < extras.size(); i++) {
			if (extras.get(i).getId()==id) {
				extras.remove(i);
				Extra delExtra = new Extra();
				delExtra.escribir(extras);
				encontrado = true;
				break;
			}
		}
		//En caso de no encontrar el ID
		if (encontrado == false) {
			System.out.printf("No existe la beca con el Id %s\n", id);
		}		
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
				//En caso de que el id ya est� en uso 
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
		//Llama al m�todo insertar en la clase ExtraDao
		extras.add(newExtra);
		newExtra.escribir(extras);
	}

	public static void mostrarTodosLosExtras(ArrayList<Extra>extras) throws ClassNotFoundException {
		System.out.println("Extras");
		//Bucle que recorre el fichero de Extra
		for (int i = 0; i < extras.size(); i++) {
			System.out.println(extras.get(i).toString());
		}
	}


	//M�todo modificar un veh�culo
		public static void modificarUnVehiculo() throws IOException, ClassNotFoundException {
			Vehiculo turismo = new Turismo();
			ArrayList<Vehiculo> turismos=turismo.leer();
			Vehiculo camion = new Camion();
			ArrayList<Vehiculo> camiones=camion.leer();
			Extra extra = new Extra();
			ArrayList<Extra> extras=extra.leer();
			
			System.out.println("Indica la matr�cula");
			String matricula = sc.next();
			boolean encontrado = false;
			
			for (int i = 0; i < camiones.size(); i++) {
				if (camiones.get(i).getMatricula().equals(matricula)) {
					System.out.println("�Qu� deseas modificar?");
					System.out.println("1. Matr�cula");
					System.out.println("2. Marca");
					System.out.println("3. Modelo");
					System.out.println("4. Color");
					System.out.println("5. Precio");
					System.out.println("6. Capacidad");
					try {
					int opcion = sc.nextInt();
					switch (opcion) {
					case 1:
						System.out.println("Introduce la nueva matr�cula");
						String newMatricula = sc.next();
						camiones.get(i).setMatricula(newMatricula);
						break;
					case 2:
						System.out.println("Introduce la nueva marca");
						String marca = sc.next();
						camiones.get(i).setMarca(marca);
						break;
					case 3:
						System.out.println("Introduce el nuevo modelo");
						String modelo = sc.next();
						camiones.get(i).setModelo(modelo);
						break;
					case 4:
						System.out.println("Introduce el nuevo color");
						String color = sc.next();
						camiones.get(i).setColor(color);
						break;
					
					case 5:
						boolean seguir = false;
						do {
							seguir = false;
							try {
								System.out.println("Introduce el nuevo precio");
								double precio = sc.nextDouble();
								camiones.get(i).setPrecio(precio);
								break;
							} catch (InputMismatchException e) {
								System.err.println("Introduzce solo n�meros");
								sc.nextLine();
								seguir = true;
							}
						} while (seguir);
						break;
					
					case 6:
						seguir = false;
						do {
							seguir = false;
							try {
								System.out.println("Introduce la nueva capacidad");
								int capacidad = sc.nextInt();
								((Camion)camiones.get(i)).setCapacidadcarga(capacidad);
								break;
							} catch (InputMismatchException e) {
								System.err.println("Introduzce solo n�meros");
								sc.nextLine();
								seguir = true;
							}
						} while (seguir);
						break;
					}
					encontrado = true;
					Vehiculo modVehiculo = new Camion();
					modVehiculo.escribir(camiones);
					} catch (InputMismatchException e) {
						System.err.println("Introduzce solo n�meros");
					}
					}
			}
			
			for (int i = 0; i < turismos.size(); i++) {
				if (turismos.get(i).getMatricula().equals(matricula)) {
					System.out.println("�Qu� deseas modificar?");
					System.out.println("1. Matr�cula");
					System.out.println("2. Marca");
					System.out.println("3. Modelo");
					System.out.println("4. Color");
					System.out.println("5. Puertas");
					System.out.println("6. Precio");
					System.out.println("7. Extras");
					try {
						int opcion = sc.nextInt();
						switch (opcion) {
						case 1:
							System.out.println("Introduce la nueva matr�cula");
							String newMatricula = sc.next();
							turismos.get(i).setMatricula(newMatricula);
							break;
						case 2:
							System.out.println("Introduce la nueva marca");
							String marca = sc.next();
							turismos.get(i).setMarca(marca);
							break;
						case 3:
							System.out.println("Introduce el nuevo modelo");
							String modelo = sc.next();
							turismos.get(i).setModelo(modelo);
							break;
						case 4:
							System.out.println("Introduce el nuevo color");
							String color = sc.next();
							turismos.get(i).setColor(color);
							break;
						case 5:
							boolean seguir = false;
							do {
								seguir = false;
								try {
									System.out.println("Introduce el nuevo n�mero de puertas");
									int numPuertas = sc.nextInt();
									((Turismo)turismos.get(i)).setNumpuertas(numPuertas);
									break;
								} catch (InputMismatchException e) {
									System.err.println("Introduzce solo n�meros");
									sc.nextLine();
									seguir = true;
								}
							} while (seguir);
							break;
						case 6:
							seguir = false;
							do {
								seguir = false;
								try {
									System.out.println("Introduce el nuevo precio");
									double precio = sc.nextDouble();
									turismos.get(i).setPrecio(precio);
									break;
								} catch (InputMismatchException e) {
									System.err.println("Introduzce solo n�meros");
									sc.nextLine();
									seguir = true;
								}
							} while (seguir);
							break;
						case 7:
							seguir = false;
							do {
								seguir = false;
								try {
									System.out.println("Introduce el nuevo extra");
									int newExtra= sc.nextInt();
									encontrado=false;
									for(int j=0;j<extras.size();j++) {
										if(extras.get(j).getId()==newExtra) {
											encontrado=true;
											((Turismo)turismos.get(i)).setExtras(extras.get(j));
											break;
										}
									}
									if(encontrado==false) {
										Extra sinExtra = new Extra (0,"Sin extra");
										((Turismo)turismos.get(i)).setExtras(sinExtra);

									}
									break;
								} catch (InputMismatchException e) {
									System.err.println("Introduzce solo n�meros");
									sc.nextLine();
									seguir = true;
								}
							} while (seguir);
							break;
					}
					Vehiculo modVehiculo = new Turismo();
					modVehiculo.escribir(turismos);
					} catch (InputMismatchException e) {
						System.err.println("Introduzce solo n�meros");
					}
					}
			}
			
			if (encontrado == false) {
				System.out.printf("No existe ning�n veh�culo con la matricula %s\n", matricula);
			}
		}

	public static void eliminarVehiculo(ArrayList<Vehiculo> camiones, ArrayList<Vehiculo> turismos, Scanner sc) throws IOException, ClassNotFoundException {
		//Pide la matr�cula por teclado
		System.out.println("Indica la matricula");
		String matricula = sc.next();
		boolean encontrado = false;
		//Bucle que recorre el fichero Camiones
		for (int i = 0; i < camiones.size(); i++) {
			if (camiones.get(i).getMatricula().equals(matricula)) {
				//M�todo para borrar el camion
				camiones.remove(i);
				Vehiculo delVehiculo = new Camion();
				//Se vuelve a escribir el fichero sin el cami�n borrado
				delVehiculo.escribir(camiones);
				encontrado = true;
			}
		}
		//Bucle que recorre el fichero Turismos
		for (int i = 0; i < turismos.size(); i++) {
			if (turismos.get(i).getMatricula().equals(matricula)) {
				//M�todo para borrar el turismo
				turismos.remove(i);
				Vehiculo delVehiculo = new Turismo();
				//Se vuelve a escribir el fichero sin el turismo borrado
				delVehiculo.escribir(turismos);
				encontrado = true;
			}
		}
		//En caso de no encontrar la matr�cula introducida
		if (encontrado == false) {
			System.out.printf("No existe el vehiculo con matricula %s\n", matricula);
		}
	}

	public static void insertarVehiculo(ArrayList<Vehiculo> camiones, ArrayList<Vehiculo> turismos, ArrayList<Extra>extras,Scanner sc) throws IOException, ClassNotFoundException {
		boolean seguir = false;
		String matricula = "";
		Extra newExtra = new Extra(0, matricula);
		do {
			//Se introducir� la nueva matr�cula por teclado
			seguir = false;
			System.out.println("Introduzca el matricula");
			matricula = sc.next();
			//Llama al m�todo leerVeh�culos para ver si existe la matr�cula introducida por teclado
			Vehiculo existeTurismo = new Turismo();
			existeTurismo = existeTurismo.leerVehiculos(matricula);
			Vehiculo existeCamion = new Camion();
			existeCamion = existeCamion.leerVehiculos(matricula);
			if (existeTurismo != null) {
				//En caso de que la matr�cula ya exista en Turismo, le imprimir� el siguiente mensaje
				if (existeTurismo.getMatricula().equals(matricula)) {
					System.out.println("Matricula repetida");
					seguir = true;
				}
			}

			if (existeCamion != null) {
				//En caso de que la matr�cula ya exista en Camion, le imprimir� el siguiente mensaje:
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
				//En caso de introducir algo distinto a n�meros, le imprimir� el siguiente mensaje:
				System.err.println("Introduzce solo n�meros");
				seguir = true;
				sc.nextLine();
			}
			//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
		} while (seguir);
		int opcion = 0;
		do {
			try {
				//Preguntar� al usuario si es Turismo o Camion
				System.out.println("�Es turismo o cami�n?\n1. Turismo\n2. Cami�n");
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				//En caso de introducir algo distinto a n�meros, le imprimir� el siguiente mensaje:
				System.err.println("Introduzce solo n�meros");
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
					//Se llamar� al m�todo mostrarTodosLosExtras para imprimir todos los extras disponibles
					//mostrarTodosLosExtras();
					//Se pedir� el extra por teclado
					System.out.println("Introduzca el extra");
					extra = sc.nextInt();
					newExtra = new Extra();
					newExtra = newExtra.leerExtras(extra);
					if (newExtra == null) {
						//En caso de que el extra no exista le imprimir� el siguiente mensaje: 
						seguir = true;
					}
				} catch (InputMismatchException e) {
					//En caso de introducir algo distinto a n�meros, le imprimir� el siguiente mensaje:
					System.err.println("Introduzce solo n�meros");
					seguir = true;
					sc.nextLine();
				}
				//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
			} while (seguir);
			//Llama al m�todo insertar de la clase Turismo para insertar el nuevo vehiculo
			Vehiculo newVehiculo = new Turismo(matricula, marca, modelo, color, precio, extra, newExtra);
			turismos.add(extra, newVehiculo);
			newVehiculo.escribir(turismos);

		}
		int capacidad = 0;
		if (opcion == 2) {

			do {
				seguir = false;
				try {
					//Se pedir� la capacidad de carga por teclado
					System.out.println("Introduzca la capacidad de carga");
					capacidad = sc.nextInt();
				} catch (InputMismatchException e) {
					//En caso de introducir algo distinto a n�meros, le imprimir� el siguiente mensaje:
					System.err.println("Introduzce solo n�meros");
					seguir = true;
					sc.nextLine();
				}
				//Bucle para que vuelva a ejecutarse si se han introducido mal los datos
			} while (seguir);
			//Llama al m�todo insertar de la clase Camion para insertar el nuevo vehiculo
			Vehiculo newVehiculo = new Camion(matricula, marca, modelo, color, precio, capacidad);
			camiones.add(capacidad, newVehiculo);
			newVehiculo.escribir(camiones);
		}

	}

	public static void buscarVehiculo(ArrayList<Vehiculo> camiones,ArrayList<Vehiculo> turismo, Scanner sc) {
		//Se pide la matr�cula por teclado
		System.out.println("Indica la matricula");
		String matricula = sc.next();
		boolean encontrado = false;
		//Bucle que recorre el fichero Turismoa
		for (int i = 0; i < turismo.size(); i++) {
			if (turismo.get(i).getMatricula().equals(matricula)) {
				System.out.println(turismo.get(i).toString());
				encontrado = true;
			}
		}
		//Bucle que recorre el fichero Camiones
		for (int i = 0; i < camiones.size(); i++) {
			if (camiones.get(i).getMatricula().equals(matricula)) {
				System.out.println(camiones.get(i).toString());
				encontrado = true;
			}
		}
		//En caso de no encontrar la matr�cula
		if (encontrado == false) {
			System.out.printf("No existe el vehiculo con esa matricula %s\n", matricula);

		}
	}

	public static void mostrarTodos(ArrayList<Vehiculo> camiones, ArrayList<Vehiculo> turismos) {
		System.out.println("Camiones");
		//Bucle que recorre todo el fichero Camiones
		for (int i = 0; i < camiones.size(); i++) {
			//Imprime el fichero Camiones
			System.out.println(camiones.get(i).toString());
		}
		System.out.println("Turismos");
		//Bucle que recorre todo el fichero Turismos
		for (int i = 0; i < turismos.size(); i++) {
			//Imprime el fichero de Turismos
			System.out.println(turismos.get(i).toString());
		}
	}

	private static Empleado login(ArrayList<Empleado> empleados) throws ClassNotFoundException {
        boolean login = false;
        String usuario;
        String contrase�a;
        Empleado emp = null;
        do {
            //Se pedir� el usuario y contrase�a por teclado
            System.out.println("Ind�que el usuario:");
            usuario = sc.next();
            System.out.println("Ind�que la contrase�a:");
            contrase�a = sc.next();
            //Bucle que recorre el fichero Empleados
            for(int i=0; i<empleados.size();i++) {
                if(empleados.get(i).getUsuario().equals(usuario)&&contrase�a.equals(empleados.get(i).getContrase�a())) {
                    login=true;
                    emp= new Empleado(usuario,contrase�a);
                }
            }
            //En caso de que el usuario o contrase�a est�n incorrectos
            if(login==false) {
                System.err.println("Usuario o Contrase�a incorrectos. Vuelva a introducir los datos");
            }
        } while (login == false);
        return emp;
    }
}
