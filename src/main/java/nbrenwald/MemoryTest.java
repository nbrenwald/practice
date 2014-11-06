package nbrenwald;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class MemoryTest {
  
  public static void printDups(int[] a){
    // a java int consumes 32 bits. N=32000, so we need a int[]1000 for our bit set.
    int[] bitset = new int[1001]; // this should consume ((32/8)*1000)/1024
    
    for(int i=0; i < a.length; i++){
      int arrayPos = a[i] / 32;
      int bitPos = a[i] % 32;
      
      if((bitset[arrayPos] & (1<<bitPos)) > 0){
        System.out.println(a[i]);
      }
      else {
        bitset[arrayPos] |=  (1<<bitPos);
      }
    }
  }
  
  public static void dumpToDisk(int[] a, int len, int maxRowID){
    String filename = "sorted_"+maxRowID;
    try (PrintWriter outputStream = new PrintWriter(new FileWriter(filename))) {
      
      for(int j=0; j< len; j++){
        outputStream.println(a[j]);
      }
    } 
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static void printMissingInt(){
    // assume we have space for 10KB
    // 10 * 1024  * 8 = 81920 bits
    // 81920 = 25600  ints.
    // read in chunks of 2560 and sort. O(n log n), then read in each file count up looking
    // for missing int
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String inputLine;
      int size = 2621440;
      int[] smallBuffer = new int[size];
      int i = 0;
      int counter=0;
      int chunk = 0;
      while ((inputLine = br.readLine()) != null) {
        smallBuffer[i] = Integer.valueOf(inputLine);
        i++;
        if(i==size){
          System.out.println("Sorting rows up to "+counter);
          Arrays.sort(smallBuffer);
          dumpToDisk(smallBuffer, i, chunk );
          i=0;
          chunk++;
        }
     counter++;
    }
      smallBuffer = Arrays.copyOf(smallBuffer, i);
      Arrays.sort(smallBuffer);
      dumpToDisk(smallBuffer, i, chunk );
      
      
      
      // now create an array of readers 
      BufferedReader[] readers = new BufferedReader[chunk+1];
      int[] values = new int[chunk+1];
      for(int j =0; j< chunk+1; j++){
        readers[j] = new BufferedReader(new FileReader("sorted_"+j));

      }
      
      for(int j =0; j< chunk+1; j++){
        String firstLine = readers[j].readLine();
        values[j]= (firstLine==null) ? -1 : Integer.valueOf(firstLine);
      }
      System.out.println(Arrays.toString(values));
      
      int k =0;
      boolean found = true;
      
      while(found){
        found =false;
        for(int j =0; j< chunk+1; j++){
          if(values[j] == k){
            //System.out.println("Found match");
            String nextLine = readers[j].readLine();
            values[j] = (nextLine==null)? -1 : Integer.valueOf(nextLine);
            k++;
            found=true;
            break;
          }
        }
        
      }
      System.out.println(k);
      

      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

//  public static void main(String[] args) {
//    int mb = 1024*1024;
//    
//    //Getting the runtime reference from system
//    Runtime runtime = Runtime.getRuntime();
//     
//    System.out.println("##### Heap utilization statistics [MB] #####");
//     
//    //Print used memory
//    System.out.println("Used Memory:"
//        + (runtime.totalMemory() - runtime.freeMemory()) / mb);
//
//    //Print free memory
//    System.out.println("Free Memory:"
//        + runtime.freeMemory() / mb);
//     
//    //Print total available memory
//    System.out.println("Total Memory:" + runtime.totalMemory() / mb);
//
//    //Print Maximum available memory
//    System.out.println("Max Memory:" + runtime.maxMemory() / mb);
//    
//    //byte[] n = new byte[1000000000];
//    BitSet bs  = new BitSet(100000000);
//    for(int i = 0; i<100000000; i++){
//      if(i%2==0)bs.set(i);//n[i] = 1;
//      //else n[i]=0;
//    }
//    
//   // BitSet bs  = new BitSet(1000000000);
//     
//    System.out.println("##### Heap utilization statistics [MB] #####");
//     
//    //Print used memory
//    System.out.println("Used Memory:"
//        + (runtime.totalMemory() - runtime.freeMemory()) / mb);
//
//    //Print free memory
//    System.out.println("Free Memory:"
//        + runtime.freeMemory() / mb);
//     
//    //Print total available memory
//    System.out.println("Total Memory:" + runtime.totalMemory() / mb);
//
//    //Print Maximum available memory
//    System.out.println("Max Memory:" + runtime.maxMemory() / mb);
//     while(true){
//       
//     }
//     
//     
//
//  }
  
  private static boolean notNull(int[] a) {
    for(int i =0; i< a.length; i++){
      if(a[i] != -1) return true;
    }
    return false;
  }

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    printMissingInt();
    long end = System.currentTimeMillis();
    System.out.println((end-start)/1000 );
  }
  

}
