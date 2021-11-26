package AionDB.AionDB;
import AionDB.AionDB.Storage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        double[] startValues = {0.1, 0.2, 0.3};
    	Storage S1 = new Storage(0, startValues, true);
    	System.out.println("S1 storage has : "+S1.getTimeStamp()+" ns\n"+S1.getValues()[0]+" as first data point");
    }
}
