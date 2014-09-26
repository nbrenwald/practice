package nbrenwald;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BitwiseOperations {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testPrevious() {
    for(int i = 0; i<1000; i++){
      assertEquals(i-1, BinaryUtils.previous(i));
    }
    
  }
  @Test
  public void testbitsToTransform(){
    assertEquals(2,BinaryUtils.bitsToTransform(0b10010, 0b1010));
  }
  
  @Test
  public void testCount1s(){
    assertEquals(0,BinaryUtils.count1s(0));
    assertEquals(32,BinaryUtils.count1s(-1));
    assertEquals(2, BinaryUtils.count1s(10));
    assertEquals(6, BinaryUtils.count1s(1055));
  }
  
  @Test
  public void testIsNegative(){
    assertTrue(BinaryUtils.isNegative(-5));
    assertFalse(BinaryUtils.isNegative(5));
    assertFalse(BinaryUtils.isNegative(0));
    assertFalse(BinaryUtils.isNegative(-0));
    assertFalse(BinaryUtils.isNegative(Integer.MAX_VALUE));
    assertTrue(BinaryUtils.isNegative(Integer.MIN_VALUE));
    assertTrue(BinaryUtils.isNegative(-1));
  }
  
  @Test
  public void testFlipBits(){
    assertEquals(0b101010, BinaryUtils.flipBits(0b010101));
  }

  @Test
  public void testFindMissingValue(){
    int[] n = {0,1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,21};
    BinaryUtils.findMissingValue(n);
    assertEquals(13, n);
  }
}
