package ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class E3 {

	public static void main(String[] args) {
		double[][] matrix = inputMatrixData();
		String dir = System.getProperty("user.dir");
		String fileName = "datosMatriz.dat";
		writeMatrix(dir,fileName,matrix);
		matrix = readMatrix(dir, fileName);
		showMatrix(matrix);

	}

	private static double[][] inputMatrixData() {
		double[][] matrix;
		int rowNum;
		int columnNum;
		Scanner scan = new Scanner(System.in);
		System.out.println("Input the number of rows the matrix has.");
		try {
			rowNum = scan.nextInt();
			while (rowNum <=0) {
				System.out.println("Input a positive number.");
				rowNum = scan.nextInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
			scan.close();
			return null;
		}
		System.out.println("Input the number of columns the matrix has.");
		try {
			columnNum = scan.nextInt();
			while (columnNum <=0) {
				System.out.println("Input a positive number.");
				columnNum = scan.nextInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
			scan.close();
			return null;
		}
		matrix = new double[rowNum][columnNum];
		try {
			for (int i = 0; i<rowNum; i++) {
				for (int j = 0; j<columnNum; j++) {
					System.out.println("Input the number at ["+i+"]["+j+"]: ");
					matrix[i][j]=scan.nextDouble();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			scan.close();
			return null;
		}
		scan.close();
		return matrix;
	}
	
	private static void writeMatrix(String dir, String fileName, double[][] matrix) {
		File f = new File(dir, fileName);
		FileOutputStream FOS_f = null;
		if (f.exists() && f.canWrite()) {
			try {
				FOS_f = new FileOutputStream(f);
				DataOutputStream DOS_f = new DataOutputStream(FOS_f);
				try {
					DOS_f.writeInt(matrix.length);
					DOS_f.writeInt(matrix[0].length);
					for (int i = 0; i<matrix.length; i++) {
						for (int j = 0; j<matrix[0].length;j++) {
							DOS_f.writeDouble(matrix[i][j]);
						}
					}
				} catch (Exception e) {

				}
				
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
	
	private static double[][] readMatrix(String dir, String fileName){
		double[][] matrix = null;
		int rowNum;
		int columnNum;
		File f = new File(dir, fileName);
		FileInputStream FIS_f = null;
		if (f.exists() && f.canRead()) {
			try {
				FIS_f = new FileInputStream(f);
				DataInputStream DIS_f = new DataInputStream(FIS_f);
				rowNum = DIS_f.readInt();
				columnNum = DIS_f.readInt();
				matrix = new double[rowNum][columnNum];
				for (int i = 0; i<matrix.length; i++) {
					for (int j = 0; j<matrix[0].length;j++) {
						matrix[i][j] = DIS_f.readDouble();
					}
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
			return matrix;
		} else {
			return null;
		}
	}
	
	private static void showMatrix(double[][] matrix) {
		for (int i = 0; i<matrix.length; i++) {
			for (int j = 0; j<matrix[0].length;j++) {
				
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
