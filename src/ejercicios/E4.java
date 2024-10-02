package ejercicios;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class E4 {

	public static void main(String[] args) {
		String dir = System.getProperty("user.dir");
		String fileName = "datosFibonacci.dat";
		Scanner scan = null;
		int res = -1;
		int salto;
		try {
			RandomAccessFile f = new RandomAccessFile(dir + System.getProperty("file.separator") + fileName, "rw");
			scan = new Scanner(System.in);
			do {
				System.out.println("1. Escribir 10 números");
				if (f.length() != 0)
					System.out.println("2. Leer con salto X");
				System.out.println("3. Salir");
				System.out.println("Introduzca la opción deseada.");
				res = scan.nextInt();
				while (res < 1 || res > 3) {
					System.out.println("Introduzca una opción válida.");
					res = scan.nextInt();
				}
				switch (res) {
				case 1:
					writeTen();
					break;
				case 2:
					System.out.println("Introduzca salto");
					salto = scan.nextInt();
					while (salto < 0) {
						System.out.println("Introduzca un salto válido");
						salto = scan.nextInt();
					}
					showWithLeap(salto);
					break;
				default:
				}
			} while (res != 3);
			scan.close();
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void writeTen() {
		String dir = System.getProperty("user.dir");
		String fileName = "datosFibonacci.dat";
		try {
			RandomAccessFile f = new RandomAccessFile(dir + System.getProperty("file.separator") + fileName, "rw");
			if (f.length() == 0) {
				f.writeInt(0);
				f.writeInt(1);
				for (int i = 0; i < 8; i++) {
					f.seek(f.length() - (Integer.BYTES * 2));
					f.writeInt(f.readInt() + f.readInt());
				}

			} else {
				for (int i = 0; i < 10; i++) {
					f.seek(f.length() - (Integer.BYTES * 2));
					f.writeInt(f.readInt() + f.readInt());
				}
			}
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void showWithLeap(int leap) {
		String dir = System.getProperty("user.dir");
		String fileName = "datosFibonacci.dat";
		try {
			RandomAccessFile f = new RandomAccessFile(dir + System.getProperty("file.separator") + fileName, "rw");
			f.seek(0);
			while (f.getFilePointer() < f.length()) {
				System.out.print(f.readInt() + " ");
				f.seek(f.getFilePointer() + (Integer.BYTES * leap));
			}
			System.out.println();
			f.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

}
