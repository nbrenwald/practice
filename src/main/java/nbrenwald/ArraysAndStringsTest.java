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

}
