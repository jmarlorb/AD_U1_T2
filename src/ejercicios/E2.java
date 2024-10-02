package ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class E2 {

	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		String fileName = "datos.dat";
		write(dir, fileName);
		read(dir, fileName);
	}

	private static void read(String dir, String fileName) {
		File f = new File(dir, fileName);
		FileInputStream FIS_f = null;
		if (f.exists() && f.canRead()) {
			try {
				FIS_f = new FileInputStream(f);
				DataInputStream DIS_f = new DataInputStream(FIS_f);
				int num;
				try {
					while (true) {
						num = DIS_f.readInt();
						System.out.println(num);
					}
				} catch (EOFException eof) {
					System.out.println("Lectura completada con exito");
				}

				FIS_f.close();
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

	private static void write(String dir, String fileName) {
		File f = new File(dir, fileName);
		FileOutputStream FOS_f = null;
		if (f.exists() && f.canWrite()) {
			Scanner scan = new Scanner(System.in);
			int num;
			try {
				FOS_f = new FileOutputStream(f);
				DataOutputStream DOS_f = new DataOutputStream(FOS_f);
				try {
					System.out.println("Introduzca números enteros. Introduzca \"Y\" para salir.");
					while (true) {
						num = scan.nextInt();
						DOS_f.writeInt(num);
					}
				} catch (Exception e) {

				}
				scan.close();
				FOS_f.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					FOS_f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
