package aionDB.AionDB;
import aionDB.AionDB.Storage;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import com.opencsv.CSVWriter;
@SuppressWarnings("unused")
public class App 
{
	public static String[] removeFirstandLast(String[] str)
    {
  
        // Removing first and last character
        // of a string using substring() method
        String[] newStr = new String[str.length];
        for (int i = 0; i < str.length; i++)
        {
            newStr[i] = str[i].substring(1, str[i].length() - 1);
        }
        
        return newStr;
    }
	
	public static void createDatabase(String dbName) throws FileNotFoundException, IOException
    {
		/* Create a database means creating a new CSV, so we have to check if the file already exists. */
	    try {
	        File f = new File("src/main/resources/"+dbName+".csv");
	        if (f.createNewFile()) {
	          System.out.println("File created: " + f.getName());
	        } else {
	          System.out.println("File already exists.");
	        }
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }

        /*/String fileName = "src/main/resources/myBDD.csv";

        try (var fos = new FileOutputStream(fileName); 
             var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             var writer = new CSVWriter(osw)) {

            writer.writeNext(databaseName);
        }*/
    }
    
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
   	
        double[] startValues = {0.1, 0.2, 0.3};
    	Storage S1 = new Storage(0, startValues, true);
    	System.out.println("S1 storage has : "+S1.getTimeStamp()+" ns\n"+S1.getValues()[0]+" as first data point");
    	Scanner sc= new Scanner(System.in);    //System.in is a standard input stream  
    	System.out.print("Entrez votre commande : "); 
    	String[] user_command_split = sc.nextLine().split(" ");
    	
    	if (user_command_split.length < 3)
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
    		System.out.println("CREATING SERIE " +user_command_split[2] );
    	}
    
    	else if (user_command_split.length < 4)
    	{
    		System.out.println("unknown command");
    	}
    	
    	else if (user_command_split[0].equals("SELECT") && !user_command_split[1].equals(" ") && user_command_split[2].equals("FROM") && !user_command_split[3].equals(" "))
    	{
    		System.out.println("SELECTING " + user_command_split[1] + " FROM " + user_command_split[3] );
    		//Here we need to read a CSV and print the whole content
    	}
    	
    	else if (user_command_split.length < 5)
    	{
    		System.out.println("unknown command");
    	}
    	
    	else if (user_command_split[0].equals("INSERT") && user_command_split[1].equals("INTO") && !user_command_split[2].equals(" ") && user_command_split[3].equals("VALUES") && !user_command_split[4].equals(" "))
    	{
    		// Let's get all .csv files :
    		File[] existing_databases = new File("src/main/resources/").listFiles(new FilenameFilter() 
    		{
    			@Override
    			public boolean accept(File dir, String name) 
    			{                         
    				return name.endsWith(".csv");                     
    			}                 
    		});
    		// Now we want to get their name :
    		HashSet<String> results = new HashSet<String>();
    		for (File file : existing_databases) {
    		    if (file.isFile()) {
    		        results.add(file.getName());
    		    }
    		}
    		
    		//Printing existing dbs
    		System.out.printf("Existing BDDs are : ");
    		System.out.println(results);
    		
    		// And check that the database is existing. Otherwise we can't add data.
    		if (results.contains(user_command_split[2]+".csv"))
    		{
        		System.out.println("INSERTING INTO " +user_command_split[2] + " VALUES " +  user_command_split[4]);
        		String[] values = removeFirstandLast(user_command_split[4].split(","));
        		System.out.println("VALUES ARE " + Arrays.toString(values));

    		}
    		else
    		{
    			System.out.println("This database doesn't exist. (This is case sensitive)");
    		}

    	}
    	
    	/* We didn't find any matching commands. */
    	else
    	{
    		System.out.println("unknown command");
    	}
    }
}
