package nbrenwald;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      String s1 = "waterbottle";
      String s2 = "erbottlewat";
      String s3 = "rbottlewaet";
      System.out.println(ArraysAndStrings.isRotation(s1,s2));
        
    }
    
    private static void printMatrix(int[][] x){
      for(int[] row: x){
        for(int i : row){
          System.out.print(i+", ");
        }
        System.out.println();
      }
    }
}
