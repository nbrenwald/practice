package nbrenwald;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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
    int size =  100000000;
    int[] a = new int[size];
    for(int i = 0; i< size ; i++){
      a[i] = i;
    }
    a[size/2] = size+1;
    
Random r = new Random();
    
    
    for(int i =0; i < 1000000; i++){
      int first = r.nextInt(size);
      int second = r.nextInt(size);
      int tmp =a[first];
      a[first] = a[second];
      a[second] = tmp;
      
    }
    
    long start = System.currentTimeMillis();
    assertEquals(size/2, BinaryUtils.findMissingValue(a));
    System.out.println((System.currentTimeMillis()-start)/1000.0);
    
    
  }
  
  @Test
  public void testFindMissingValueUsingSort(){
    
    int size =  100000000; //100mn
    int[] a = new int[size];
    for(int i = 0; i< size ; i++){
      a[i] = i;
    }
    a[size/2] = size+1;
    
    Random r = new Random();
    
    
    for(int i =0; i < 1000000; i++){
      int first = r.nextInt(size);
      int second = r.nextInt(size);
      int tmp =a[first];
      a[first] = a[second];
      a[second] = tmp;
      
    }
    
    
    
    
    long start = System.currentTimeMillis();
    assertEquals(size/2, BinaryUtils.findMissingValueUsingSort(a));
    System.out.println((System.currentTimeMillis()-start)/1000.0);
  }
  
  @Test
  public void testDrawHorizontalLine(){
    byte[] screen = {0,0,0,0,0,0,0,0,0};
    BinaryUtils.drawHorizontalLine(screen, 24, 10, 6, 0);
    assertTrue(true);
  }
}
