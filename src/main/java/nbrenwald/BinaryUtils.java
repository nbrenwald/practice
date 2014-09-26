package nbrenwald;

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
  
  public static int findMissingValue(int[] n){
    for(int i = 0; i<n.length;i++){
      System.out.println("Position = "+ i + " value = "+n[i]+" in Binary = "+Integer.toBinaryString(n[i]));
    }
    
    int i = 0;
    int p=1;
    while(p<n.length){
      int bit = getIthBit(i,n[p-1]);
      if(bit==1) break;
      i++;
      p *=2;
    }
    System.out.println(p-1);
    while(getIthBit(i,n[p-1])==1){
      p -= 1;
    }
    return p-1;
  }
    
  public static int getIthBit(int i, int n){
    int mask = 1 << i;
    return ((n&mask) > 0) ? 1 : 0;
  }  
    
    
}
