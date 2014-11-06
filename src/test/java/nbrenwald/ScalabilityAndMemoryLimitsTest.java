package nbrenwald;

import org.junit.Test;

public class ScalabilityAndMemoryLimitsTest {
  
  @Test
  public void testPrintDups(){
    int[] a =new int[32005];
    for(int i=0; i<=32000; i++){
      a[i] = i;
    }
    a[32001]= 5;
    a[32002]= 500;
    a[32003]= 10000;
    a[32004]= 20000;
    MemoryTest.printDups(a);
  }

}
