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
    assertEquals("a%20b%20c", ArraysAndStrings.replaceSubstring("a b c    ", "%20", 5));
  }
  
  @Test
  public void testCompress() {
    assertEquals("a2b4c1", ArraysAndStrings.compress("aabbbbc"));
    assertEquals("aabbc", ArraysAndStrings.compress("aabbc"));
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
    String s2 = "erbottlewat";
    String s3 = "rbottlewaet";
  
    assertTrue(ArraysAndStrings.isRotation(s1,s2));
    assertFalse(ArraysAndStrings.isRotation(s1,s3));
  }


@Test
  public void testIsSubstring() {
    String p = "345";
    String s1 = "123456789";
    String s2 = "12456789";
  
    assertTrue(ArraysAndStrings.isSubstring(p,s1));
    assertFalse(ArraysAndStrings.isRotation(p, s2));
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
