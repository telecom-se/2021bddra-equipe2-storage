package aionDB.AionDB;
import aionDB.AionDB.Storage;
import java.util.Scanner;
import java.util.Arrays;
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
    
    public static void main( String[] args )
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
    	}
    	
    	else if (user_command_split.length < 5)
    	{
    		System.out.println("unknown command");
    	}
    	
    	else if (user_command_split[0].equals("INSERT") && user_command_split[1].equals("INTO") && !user_command_split[2].equals(" ") && user_command_split[3].equals("VALUES") && !user_command_split[4].equals(" "))
    	{
    		System.out.println("INSERTING INTO " +user_command_split[2] + " VALUES " +  user_command_split[4]);
    		String[] values = removeFirstandLast(user_command_split[4].split(","));
    		System.out.println("VALUES ARE " + Arrays.toString(values));
    	}
    }
}
