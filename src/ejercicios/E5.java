package ejercicios;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import suplementarias.MiObjectOutputStream;
import suplementarias.Pedido;

public class E5 {

	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		String fileName = "pedidos.dat";
		File f = new File(dir, fileName);
		Pedido[] listaCompra = new Pedido[3];
		Pedido[] listaCompraDos = new Pedido[2];
		Scanner scan = new Scanner(System.in);
		String descripcion;
		int numUnidades;
		double precio;
		System.out.println("Introduzca los datos de los pedidos");
		for (int i = 0; i < listaCompra.length; i++) {
			System.out.println("Introduzca descripción del pedido " + (i + 1) + ":");
			descripcion = scan.nextLine();
			System.out.println("Introduzca número de unidades del pedido " + (i + 1) + ":");
			numUnidades = scan.nextInt();
			System.out.println("Introduzca precio del pedido " + (i + 1) + ":");
			precio = scan.nextDouble();
			listaCompra[i] = new Pedido(descripcion, numUnidades, precio);
			scan.nextLine();
		}
		writeShipments(listaCompra, f);
		readShipments(f);
		System.out.println("-------------");
		listaCompraDos[0] = new Pedido(listaCompra[0].getDescripcion(), listaCompra[1].getNumUnidades(),
				listaCompra[0].getPrecio());
		listaCompraDos[1] = new Pedido(listaCompra[1].getDescripcion(), listaCompra[2].getNumUnidades(),
				listaCompra[1].getPrecio());
		addShipments(listaCompraDos, f);
		readShipments(f);
		scan.close();

	}

	private static void writeShipments(Pedido[] shipment, File f) {
		FileOutputStream FOS_f = null;
		try {
			ObjectOutputStream OOS_f;
			FOS_f = new FileOutputStream(f);
			OOS_f = new ObjectOutputStream(FOS_f);
			for (int i = 0; i < shipment.length; i++) {
				OOS_f.writeObject(shipment[i]);
			}
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

	private static void readShipments(File f) {
		FileInputStream FIS_f = null;
		if (f.exists() && f.canRead()) {
			try {
				FIS_f = new FileInputStream(f);
				ObjectInputStream OIS_f = new ObjectInputStream(FIS_f);
				Pedido auxPedido;
				try {
					while (true) {
						auxPedido = (Pedido) OIS_f.readObject();
						System.out.println(auxPedido);
					}
				} catch (EOFException eof) {
					System.out.println("Lectura completada con exito");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				OIS_f.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {

				try {
					FIS_f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private static void addShipments(Pedido[] shipment, File f) {
		FileOutputStream FOS_f = null;
		try {
			ObjectOutputStream OOS_f;
			FOS_f = new FileOutputStream(f, true);
			OOS_f = new MiObjectOutputStream(FOS_f);
			for (int i = 0; i < shipment.length; i++) {
				OOS_f.writeObject(shipment[i]);
			}
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

}
