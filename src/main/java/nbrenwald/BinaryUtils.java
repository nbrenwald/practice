package nbrenwald;

import java.util.Arrays;

public class BinaryUtils {
  public static int previous(int n){
    if (n==0) return -1;
    
    System.out.println("n = "+Integer.toBinaryString(n));
    // Figure out position of first 1.
    int c0 = 0;
    int tmp = n;
    while((tmp & 1) == 0){
      tmp = tmp >> 1;
      System.out.println("tmp = "+Integer.toBinaryString(tmp));
      c0++;
      
    }
    System.out.println("c0 = " + c0);
    
    //create bit mask to set trailing 0s to 1s.
    int off = ~(1 << c0);
    System.out.println("off bit mask = "+Integer.toBinaryString(off));
    
    int on = ~((~0) << c0);
    
    System.out.println("on bit mask = "+Integer.toBinaryString(on));
    
    n = ((n & off)|on);
    System.out.println("n-1 = "+Integer.toBinaryString(n));
    return n;
  }
  
  public static int bitsToTransform(int src, int target){
    int counter = 0;
    while(!(src==0 && target==0)){
      if(((src&1)^(target&1)) == 1) counter++;
      src >>= 1;
      target >>= 1;
    }
    
    return counter;
  }
  
  public static int count1s(int bitmap){
    int count=0;
    while(bitmap != 0){
      bitmap &=(bitmap-1);
      count++;
    }
    return count;
  }
  
  public static boolean isNegative(int n){
    //need to check if MSB is 1.
    //create a mask which is 100000..000, bitwise and and test for greater than zero
    // 3 ways to calculate the mask 
    // ~(~0>>>1)
    // use the fact that -1 is = ~0  ~(-1>>>1)
    // use the fact that the smallest int is 1 followed by 0s Integer.MIN_VALUE
    int mask=~(-1>>>1);
    System.out.println("input  = "+Integer.toBinaryString(n));
    System.out.println("mask   = " + Integer.toBinaryString(mask));
    System.out.println("result = " + Integer.toBinaryString(mask&n));
    
    return ((n&mask)<0);
  }
  
  public static int flipBits(int n){
    System.out.println("n = "+ Integer.toBinaryString(n));
    int mask =0;
    for(int i=0;i<31;i++){
      mask <<=1;
      if(i%2==0)mask |= 1;
    }
    System.out.println("mask = "+ Integer.toBinaryString(mask));
    int evens = n&mask;
    System.out.println("evens = "+ Integer.toBinaryString(evens));
    int odds = n&~mask;
    System.out.println("odds = "+ Integer.toBinaryString(odds));
    odds >>>=1;
    evens <<= 1;
    System.out.println("evens shifted = "+ Integer.toBinaryString(evens));
    System.out.println("odds shifted  = "+ Integer.toBinaryString(odds));
    return (odds|evens);
  }
  
  public static int findMissingValueUsingSort(int[] n){
    Arrays.sort(n);
    int previous = 0;
    for(int i = 1 ; i < n.length; i++, previous++){
      if(n[i] >previous +1) break;
      
    }
    return previous+1;
  }
  
  public static int findMissingValue(int[] n){
    
    //Step 1. Optimization - Do one pass to determine the number of digits used to represent the max
    int max=0;
    for(int i = 0 ; i < n.length; i++){
      if(n[i] > max)max= n[i];
    }
    max++; // in case max element is missing
    //System.out.println("Max = " + max);
    
    
    //Step 2. Calculate bits required to store max value;
    int bits = 0;
    while(max >0){
      bits++;
      max>>=1;
    }
    //System.out.println("Bits to store max value = "+ bits);
    
    
    int[] expectedOnes = new int[bits];
    int[] expectedZeros = new int[bits];
    
    int expectedValues = n.length+1;
    int missingValue = 0;
    
    for(int i = 0 ; i < bits; i++){
      int valueBit = (int) Math.pow(2, i);
      int patternLength = valueBit *2;
      expectedZeros[i] = (expectedValues / patternLength) *  valueBit;
      expectedOnes[i] = expectedZeros[i];
      
      // now remainder
      int remainder = expectedValues % patternLength;
      if(remainder > valueBit){
        expectedOnes[i] += remainder - valueBit;
      }
        expectedZeros[i] += Math.min(remainder, valueBit);
        
        //System.out.println("Expected ones for bit i = "+i+ " is "+ expectedOnes[i]);
        //System.out.println("Expected zeros for bit i = "+i+ " is "+ expectedZeros[i]);
        
        int actualOnes = 0;
        int actualZeros = 0;
        
        for(int j = 0; j < n.length; j++){
          if (getIthBit(n[j], i) == 0 )actualZeros ++;
          else actualOnes++;
        } 
        
        //System.out.println("Actual ones for bit i = "+i+ " is "+ actualOnes);
        //System.out.println("Actual zeros for bit i = "+i+ " is "+ actualZeros);
        
        if(actualZeros == expectedZeros[i]){
          missingValue |= 1 << i;
        }
    }
    System.out.println("Missing Value is "+missingValue);
    return missingValue;
  }
     
  
  
  
  public static int getIthBit(int n, int i){
    int mask = 1 << i;
    return ((n&mask) > 0) ? 1 : 0;
  } 
  
  
  
  
  
  public static void drawHorizontalLine(byte[] screen, int width, int x1, int x2, int y){
    int widthBytes = width/8;
    int startByte = widthBytes * y; // calculates an offset into the array for our row y.
    
    for(int i = 0; i< widthBytes; i++){ // loop over every byte in our row
      int mask = 0; //this mask will be or with our byte
      
      int startBit = x1-(i*8);
      int endBit = x2-(i*8);
      
      if(startBit < 8 && endBit >=0){// does this byte include any part of the line?
        mask = ~mask;
        
        if(startBit >= 0){ // does the line start in this byte?
          mask <<=8-startBit;
          mask = ~mask;
        }
        
        if(endBit < 8){ // does the line start in this byte?
          mask = mask & (~0 << (8-endBit));
        }
      }
      screen[i+startByte] = (byte) (screen[i+startByte] | mask);     
    }
  
  for(int i = 0; i < screen.length; i++){
    System.out.print(String.format("%8s:",
        Integer.toBinaryString((screen[i] + 256) % 256)).replace(' ', '0'));
    if(((i+1)*8) % width ==0)  System.out.println();
  }
}
    
    
}
