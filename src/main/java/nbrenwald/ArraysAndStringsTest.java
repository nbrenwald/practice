package nbrenwald;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArraysAndStringsTest {

  @Test
  public void testIsPermutation() {
    assertTrue(ArraysAndStrings.isPermutation("aabcd", "dabca"));
    assertFalse(ArraysAndStrings.isPermutation("aebcd", "dabca"));
  }

  @Test
  public void testReplaceSubstring() {
    assertEquals(ArraysAndStrings.replaceSubstring("a b c    ", "%20", 5), "a%20b%20c");
  }
  
  @Test
  public void testCompress() {
    assertEquals(ArraysAndStrings.compress("aabbbbc"), "a2b4c1");
    assertEquals(ArraysAndStrings.compress("aabbc"), "aabbc");
  }
  
  @Test
  public void testRotateMatrix() {
    int[][] x = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    int[][] y = {{13,9,5,1},{14,10,6,2},{15,11,7,3},{16,12,8,4}};
    ArraysAndStrings.rotateSquareMatrix(x, 0);
    assertTrue(compareMatrix(x,y));
  }

  @Test
  public void testSetToZeroWhereZero() {
    int[][] x = {{1,2,3,4},{5,0,7,8},{0,10,11,0},{13,14,15,16}};
    int[][] y = {{0,0,3,0},{0,0,0,0},{0,0,0,0},{0,0,15,0}};
    ArraysAndStrings.setToZeroWhereZero(x);
    assertTrue(compareMatrix(x,y));
  }

@Test
  public void testIsRotation() {
    String s1 = "waterbottle";
    String s2 = "erbottlewat"
    String s3 = "rbottlewaet"
    assertTrue(ArraysAndString.isRotation(s1,s2));
    assertFalse(ArraysAndString.isRotation(s1,s3));
  }
  
  private static boolean compareMatrix(int[][]a, int[][] b){
    for(int i = 0; i< a.length; i++){
      for(int j=0; j< a[i].length; j++){
        if(a[i][j]!=b[i][j]) return false;
      }
    }
    return true;
  }

}
