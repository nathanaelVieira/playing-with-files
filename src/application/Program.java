package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<Product>();
		
		System.out.print("Enter with path of the archive: ");
		String strPath = sc.nextLine().trim();
		
		File path = new File(strPath);
		
		/** Pegando em String o caminho **/
		String newPath = path.getParent();
		
		boolean createdPaste = new File(newPath + "\\out").mkdir();
		
		String fileOfThePath = newPath + "\\out\\summary.csv";
		
		try (BufferedReader bufReader = new BufferedReader( new FileReader(newPath))) {
			
			String itemOfTheFile = bufReader.readLine();
			while ( itemOfTheFile != null) {
				
				String[] fields = itemOfTheFile.split(",");
				String name = fields[0].trim();
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				
				list.add( new Product(name, price, quantity));
				
				itemOfTheFile = bufReader.readLine();
			}
			
			try (BufferedWriter bufWriter = new BufferedWriter( new FileWriter(fileOfThePath))){
				
				for (Product product : list) {
					bufWriter.write(product.getName() + ", " + String.format("%.2f", product.totalPrice()));
					bufWriter.newLine();
				}
				
				System.out.println(fileOfThePath + " Created with sucess.");
			}
			catch ( IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		}
		catch ( IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		finally {
			if (sc != null) {
				sc.close();
			}
		}
		
	}

}
