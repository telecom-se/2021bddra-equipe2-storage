package aionDB;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.opencsv.CSVWriter;

import models.Storage;
@SuppressWarnings("unused")
public class App 
{
	public static HashSet<String> getAllDB()
	{
		File[] existing_databases = new File("src/main/java/aionDB/databases/").listFiles(new FilenameFilter() 
		{
			@Override
			public boolean accept(File dir, String name) 
			{                         
				return name.endsWith(".txt");                     
			}                 
		});
		// Now we want to get their name :
		HashSet<String> results = new HashSet<String>();
		for (File file : existing_databases) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
		return results;
	}

	
	public static void createDatabase(String dbName) throws FileNotFoundException, IOException
    {
		/* Create a database means creating a new CSV, so we have to check if the file already exists. */
	    try {
	        File f = new File("src/main/java/aionDB/databases/"+dbName+".txt");
	        if (f.createNewFile()) {
	          System.out.println("File created: " + f.getName());
	        } else {
	          System.out.println("File already exists.");
	        }
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
    }
    
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
    	String selectedDB = "";
        double[] startValues = {0.1, 0.2, 0.3};
    	Storage S1 = new Storage(0, startValues, true);
    	System.out.println("S1 storage has : "+S1.getTimeStamp()+" ns\n"+S1.getValues()[0]+" as first data point");
    	Scanner sc= new Scanner(System.in);    //System.in is a standard input stream  
    	String[] user_command_split = {""};
    	while(!user_command_split[0].equals("STOP"))
    	{
    		System.out.print("Command : "); 
        	user_command_split = sc.nextLine().split(" ");
        	
        	if (user_command_split.length < 2)
        	{
        		if(user_command_split[0].equals("STOP"))
        		{
        			System.out.println("bye bye");
        			continue;
        		}
        		System.out.println("unknown command");
        	}
        	
        	else if (user_command_split[0].equals("USE") && !user_command_split[1].equals(" "))
        	{
        		System.out.println("USING "+user_command_split[1]);

        		// And check that the database is existing. Otherwise we can't add data.
        		if (getAllDB().contains(user_command_split[1]+".txt"))
        		{
        			selectedDB = user_command_split[1];
        		}
        		else
        		{
            		//Printing existing dbs
            		System.out.printf("DB don't exist. -- Existing DBs are : ");
            		System.out.println(getAllDB());
        		}
        	}
        	
        	else if (user_command_split.length < 3)
        	{
        		System.out.println("unknown command");
        	}
        	
        	else if (user_command_split[0].equals("CREATE") && user_command_split[1].equals("DATABASE") && !user_command_split[2].equals(" "))
        	{
        		System.out.println("CREATING DATABASE " +user_command_split[2] );
        		createDatabase(user_command_split[2]);
        		
        	}
        	
        	else if (user_command_split[0].equals("CREATE") && user_command_split[1].equals("SERIE") && !user_command_split[2].equals(" "))
        	{
        		if (selectedDB == "")
        		{
        			System.out.println("use the command 'USE database' before creating a serie.");
        		}
        		else
        		{
            		System.out.println("CREATING SERIE " +user_command_split[2] + " into " + selectedDB);
        			Path pathToDB = Paths.get("src/main/java/aionDB/databases/"+selectedDB+".txt/");
        	        byte[] bytes = ("$" + user_command_split[2]+"$").getBytes(StandardCharsets.UTF_8);
        	        try {
        	            Files.write(pathToDB, bytes, StandardOpenOption.APPEND);    
        	            System.out.println("Successfully written serie's name to the DB file");
        	        }
        	        catch (IOException e) {
        	            e.printStackTrace();
        	        }
        		}
        	}
        
        	else if (user_command_split.length < 4)
        	{
        		System.out.println("unknown command");
        	}
        	
        	else if (user_command_split[0].equals("SELECT") && !user_command_split[1].equals(" ") && user_command_split[2].equals("FROM") && !user_command_split[3].equals(" "))
        	{
        		if (selectedDB == "")
        		{
        			System.out.println("use the command 'USE database' before selecting values.");
        			continue;
        		}

        		System.out.println("SELECTING " + user_command_split[1] + " FROM " + user_command_split[3] );

    	        try {
            			Path pathToDB = Paths.get("src/main/java/aionDB/databases/"+selectedDB+".txt/");
            	        String fileContent = new String(Files.readAllBytes(pathToDB), StandardCharsets.UTF_8);            	        
            	        String[] allSerie = StringUtils.substringsBetween(fileContent, "$", "$");
            	        System.out.println("Available series ares : " + Arrays.toString(allSerie));
            	        
            	        int indexOfSerie = Arrays.asList(allSerie).indexOf(user_command_split[3]);
            	        if (indexOfSerie == -1)
            	        {
            	        	System.out.println("This serie doesn't exist.");
            	        	continue;
	            	        
            	        }
            	        
            	        /* Il faudrait checker ici si le contenu est vide ou pas, mais je ne sais pas comment */
            	        String[] allSerieAndContent = fileContent.split("\\$");
            	        indexOfSerie = Arrays.asList(allSerieAndContent).indexOf(user_command_split[3]);
            	        System.out.println("Serie find. Content is : " + allSerieAndContent[indexOfSerie+1]);
    	            }
    	          catch (FileNotFoundException e) {
    	            System.out.println("An error occurred.");
    	            e.printStackTrace();
    	          }
        	}
        	
        	else if (user_command_split.length < 5)
        	{
        		System.out.println("unknown command");
        	}
        	
        	else if (user_command_split[0].equals("INSERT") && user_command_split[1].equals("INTO") && !user_command_split[2].equals(" ") && user_command_split[3].equals("VALUES") && !user_command_split[4].equals(" "))
        	{        		

        		/* Checking that a DB is selected */
        		if (selectedDB == "")
        		{
        			System.out.println("use the command 'USE database' before inserting values.");
    	        	continue;
        		}
        		
    			Path pathToDB = Paths.get("src/main/java/aionDB/databases/"+selectedDB+".txt/");
    	        String fileContent = new String(Files.readAllBytes(pathToDB), StandardCharsets.UTF_8);
    	        String[] allSerie = StringUtils.substringsBetween(fileContent, "$", "$");
    	        int indexOfSerie = Arrays.asList(allSerie).indexOf(user_command_split[2]);
    	        
    	        /* Checking that this serie exists */
    	        if (indexOfSerie == -1)
    	        {
    	        	System.out.println("This serie doesn't exist.");
    	        	continue;
    	        }
        		
        		System.out.println("INSERTING INTO " +user_command_split[2] + " VALUES " +  user_command_split[4]);
        		String [] brichie = user_command_split[4].split("\\)");
        		String[] keys = brichie[0].replaceAll("[(]","").split(",");
        		String[] values = brichie[1].replaceAll("[(]","").substring(1).split(",");
        		System.out.println("Keys are" + Arrays.toString(keys));
        		System.out.println("Values are " + Arrays.toString(values));
        		
        		if (keys.length != values.length)
        		{
        			System.out.println("Error : keys and values don't have the same length");
        			continue;
        		}
        		
        		String newContent = "";
        		
        		for (int i = 0; i < keys.length; i++) 
        		{
        			newContent += keys[i]+":"+values[i]+",";
        		}
        		
        		//newContent = newContent.substring(0, newContent.length() - 1); Lets keep the ',' otherwise when we add multiple time datas, it will be weird
        		fileContent = fileContent.replace("$"+user_command_split[2]+"$", "$"+user_command_split[2]+"$"+ newContent);
        		System.out.println("New content : " + fileContent);
        		
    	        byte[] bytes = fileContent.getBytes(StandardCharsets.UTF_8);
    	        try {
    	            Files.write(pathToDB, bytes);    
    	            System.out.println("Successfully written new content");
    	        }
    	        catch (IOException e) {
    	            e.printStackTrace();
    	        }
        		
        	}
        	
        	/* We didn't find any matching commands. */
        	else
        	{
        		System.out.println("unknown command");
        	}
    	}
    	
    }
}
