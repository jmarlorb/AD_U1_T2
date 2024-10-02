package ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import suplementarias.Contacto;

public class E7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		int res = -1;
		String dir = System.getProperty("user.dir");
		String fileName = "listaContactos.dat";
		File f = new File(dir, fileName);
		Contacto c;
		String nombre;
		String apellidos;
		String numero;
		ArrayList<Contacto> agenda = null;
		if (f.exists()&& f.canRead()) {
		agenda = recuperarContactos(f); 
		if (agenda==null) {
			agenda = new ArrayList<Contacto>();
		}
		} else {
			agenda = new ArrayList<Contacto>();
		}
		
		do {
			System.out.println("Actualmente hay " + agenda.size() + " contactos almacenados.");
			System.out.println("1.) Mostrar Contactos.");
			System.out.println("2.) Añadir Contacto.");
			System.out.println("3.) Borrar Contacto.");
			System.out.println("4.) Salir");
			System.out.println("Introduzca la opción que desea realizar.");
			res = scan.nextInt();
			while (res < 1 || res > 4) {
				System.out.println("Introduzca una opción válida.");
				res = scan.nextInt();
			}
			scan.nextLine();
			switch (res) {
			case 1:
				for (int i = 0; i<agenda.size(); i++) {
					System.out.println(agenda.get(i));
				}
				break;
			case 2:
				System.out.println("Introduzca nombre del contacto.");
				nombre = scan.nextLine();
				System.out.println("Introduzca apellidos del contacto.");
				apellidos = scan.nextLine();
				System.out.println("Introduzca numero del contacto.");
				numero = scan.nextLine();
				c = new Contacto(nombre, apellidos, numero);
				agenda.add(c);
				break;
			case 3:
				System.out.println("Introduzca nombre del contacto.");
				nombre = scan.nextLine();
				System.out.println("Introduzca apellidos del contacto.");
				apellidos = scan.nextLine();
				System.out.println("Introduzca numero del contacto.");
				numero = scan.nextLine();
				c = new Contacto(nombre, apellidos, numero);
				agenda.remove(c);
				break;
			default:
				break;
			}
		} while (res != 4);
		guardarContactos(f, agenda);
		scan.close();

	}
	
	private static void guardarContactos(File f, ArrayList<Contacto> a) {
		FileOutputStream FOS_f = null;
		try {
			ObjectOutputStream OOS_f;
			FOS_f = new FileOutputStream(f);
			OOS_f = new ObjectOutputStream(FOS_f);
			OOS_f.writeObject(a);
			OOS_f.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			System.out.println("Escritura completada con exito");
			try {
				FOS_f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static ArrayList<Contacto> recuperarContactos(File f) {
		FileInputStream FIS_f = null;
		ArrayList<Contacto> auxContactos = null;
		if (f.exists() && f.canRead()) {
			try {
				FIS_f = new FileInputStream(f);
				ObjectInputStream OIS_f = new ObjectInputStream(FIS_f);
				auxContactos = (ArrayList<Contacto>) OIS_f.readObject();
				OIS_f.close();
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {

				try {
					FIS_f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		if (auxContactos == null) System.out.println("Lectura Fallida");
		else System.out.println("Lectura Exitosa");
		return auxContactos;
	}

}
