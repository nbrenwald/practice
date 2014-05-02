package nbrenwald;

import static org.junit.Assert.assertEquals;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int[][] x = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printMatrix(x);
        ArraysAndStrings.rotateSquareMatrix(x,0);
        printMatrix(x);
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
