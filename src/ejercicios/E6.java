package ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import suplementarias.Agenda;
import suplementarias.Contacto;

public class E6 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int res = -1;
		String dir = System.getProperty("user.dir");
		String fileName = "agenda.dat";
		File f = new File(dir, fileName);
		Contacto c;
		String nombre;
		String apellidos;
		String numero;
		Agenda a = null;
		if (f.exists()&& f.canRead()) {
		a = readAgenda(f);
		if (a==null) {
			a = new Agenda();
		}
		} else {
			a = new Agenda();
		}
		
		do {
			System.out.println("Actualmente hay " + a.getNoOfContacts() + " contactos almacenados.");
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
				a.showContactos();
				break;
			case 2:
				System.out.println("Introduzca nombre del contacto.");
				nombre = scan.nextLine();
				System.out.println("Introduzca apellidos del contacto.");
				apellidos = scan.nextLine();
				System.out.println("Introduzca numero del contacto.");
				numero = scan.nextLine();
				c = new Contacto(nombre, apellidos, numero);
				a.addContacto(c);
				break;
			case 3:
				System.out.println("Introduzca nombre del contacto.");
				nombre = scan.nextLine();
				System.out.println("Introduzca apellidos del contacto.");
				apellidos = scan.nextLine();
				System.out.println("Introduzca numero del contacto.");
				numero = scan.nextLine();
				c = new Contacto(nombre, apellidos, numero);
				a.deleteContacto(c);
				break;
			default:
				break;
			}
		} while (res != 4);
		saveAgenda(f, a);
		scan.close();

	}

	private static void saveAgenda(File f, Agenda a) {
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
	
	private static Agenda readAgenda(File f) {
		FileInputStream FIS_f = null;
		Agenda auxAgenda = null;
		if (f.exists() && f.canRead()) {
			try {
				FIS_f = new FileInputStream(f);
				ObjectInputStream OIS_f = new ObjectInputStream(FIS_f);
				auxAgenda = (Agenda) OIS_f.readObject();
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
		if (auxAgenda == null) System.out.println("Lectura Fallida");
		else System.out.println("Lectura Exitosa");
		return auxAgenda;
	}

}
