package nbrenwald;

import static org.junit.Assert.*;

import java.util.Arrays;

import nbrenwald.SortingAndSearching.AnagramComparator;

import org.junit.Before;
import org.junit.Test;

public class SortingAndSearchingTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testMergesortedArraysSlow() {
    int size = 200000;
    int[] a = new int[size*2];
    int[] b = new int[size];
    int[] c = new int[size*2];
    
    for(int i = 0; i< size; i++){
      b[i]= size+i;
    }
    for(int i = 0; i< size*2; i++){
      c[i]= i;
      a[i] = Integer.MAX_VALUE;
    }
    for(int i = 0; i< size; i++){
      a[i]= i;
    }
    
   
    SortingAndSearching.mergeSortedArraysSlow(a, b);
    assertTrue(Arrays.equals(c,a));
  }
  
  @Test
  public void testMergesortedArraysFast() {
    int size = 200000;
    int[] a = new int[size*2];
    int[] b = new int[size];
    int[] c = new int[size*2];
    
    for(int i = 0; i< size; i++){
      b[i]= size+i;
    }
    for(int i = 0; i< size*2; i++){
      c[i]= i;
      a[i] = Integer.MAX_VALUE;
    }
    for(int i = 0; i< size; i++){
      a[i]= i;
    }
    
   
    SortingAndSearching.mergeSortedArraysFast(a, b);
    assertTrue(Arrays.equals(c,a));
  }
  
  @Test
  public void testSortAnagram(){
    String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
    SortingAndSearching.sortStrings(array);
    AnagramComparator comp = new AnagramComparator();
    System.out.println(comp.compare("apple", "papel"));
    System.out.println(Arrays.toString(array));
    
  }

}
