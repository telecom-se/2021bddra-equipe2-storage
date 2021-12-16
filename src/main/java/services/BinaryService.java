package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BinaryService {
	
		public static  void openBinaryFile()
	    {
	        Path path = Paths.get("data");
	        byte[] bytes = "11/04/2021,14,25.5;12/05/2020,10,22.3;".getBytes(StandardCharsets.UTF_8);
	        
	        try {
	            Files.write(path, bytes);    
	            System.out.println("Successfully written data to the file");
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        try {
	            File myObj = new File("doc.txt");
	            Scanner myReader = new Scanner(myObj);
	            while (myReader.hasNextLine()) {
	              String data = myReader.nextLine();
	              System.out.println(data);
	            }
	            myReader.close();
	          } catch (FileNotFoundException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
	    }
		
		public static void main(String[] args) {
			openBinaryFile();
	    }
	
}
