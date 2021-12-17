package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BinaryService {
	
		public static  void openBinaryFile()
	    {
	        Path path = Paths.get("data");
	        byte[] bytes = "11/04/2021,14,25.5,12/05/2020,10,22.3;".getBytes(StandardCharsets.UTF_8);
	        
	        try {
	            Files.write(path, bytes);    
	            System.out.println("Successfully written data to the file");
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        try {
	            File myObj = new File("data");
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
		public static void insert(String database,String series,String values) {
			StringBuffer databasestring = new StringBuffer();
	        Map<String, String> databasemap = new HashMap<String, String>();
			try {
	            File myObj = new File(database);
	            Scanner myReader = new Scanner(myObj);
	            while (myReader.hasNextLine()) {
	              String data = myReader.nextLine();
	              String datas[]=data.split("!");
	              System.out.println(data);
	              databasestring.append(data);
	              databasemap.put(datas[0],datas[1]);            
	              
	            }
	            System.out.println(databasestring);
	            System.out.println(databasemap);
	            myReader.close();
	          } catch (FileNotFoundException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
			Path path = Paths.get(database);
			if(databasemap.containsKey(series)) {
				String entry = databasemap.get(series);
				databasemap.put(series, entry+values);
			}
			Iterator iterator = databasemap.entrySet().iterator();
	        while (iterator.hasNext()) {
	          Map.Entry mapentry = (Map.Entry) iterator.next();
	          System.out.println("clé: "+mapentry.getKey()
	                            + " | valeur: " + mapentry.getValue());
	        } 
			//System.out.println(databasemap[0]);

	        /*byte[] bytes = entry.getBytes(StandardCharsets.UTF_8);
	        		
	        try {
	            Files.write(path, bytes);    
	            System.out.println("Successfully written data to the file");
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }*/
	        
		}
		public static void main(String[] args) {
			//openBinaryFile();
			String test="11/04/2021,14,25.5,12/05/2020,10,22.3,12/04/2021,14,25.5,13/05/2020,10,22.3";
			System.out.println(test);
			insert("data","j1",test);
	    }
}
