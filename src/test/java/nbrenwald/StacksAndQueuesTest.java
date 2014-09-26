package nbrenwald;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StacksAndQueuesTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testMin() {
    Stack<Integer> s1 = new Stack<>();
    s1.push(4);
    s1.push(2);
    s1.push(7);
    s1.push(12);
    assertEquals((int)s1.min(), 2);
    
  }
  
  @Test
  public void testTowersOfHanoi() {
    Stack<Integer> src = new Stack<>();
    Stack<Integer> temp = new Stack<>();
    Stack<Integer> dest = new Stack<>();
    for(int i = 5; i>0; i--){
      src.push(i);
    }
    System.out.println(src.toString());
    System.out.println(temp.toString());
    System.out.println(dest.toString());
    System.out.println();
    Stack.towersOfHanoi(5, src, temp, dest);
    System.out.println(src.toString());
    System.out.println(temp.toString());
    System.out.println(dest.toString());
    System.out.println();
    assertEquals(dest.toString(), "|54321");
    
  }
  
  @Test
  public void testStackQueue(){
    StackQueue<Integer> queue = new StackQueue<>();
    queue.enQueue(1);
    queue.enQueue(2);
    assertEquals((int)queue.deQueue(),1);
    assertEquals((int)queue.deQueue(),2);
    assertNull(queue.deQueue());
    assertTrue(queue.isEmpty());
    queue.enQueue(1);
    assertFalse(queue.isEmpty());
    queue.enQueue(2);
    assertEquals((int)queue.deQueue(),1);
    queue.enQueue(3);
    queue.enQueue(4);
    assertEquals((int)queue.deQueue(),2);
    assertEquals((int)queue.deQueue(),3);
    assertEquals((int)queue.deQueue(),4);
  }
  
  @Test
  public void testSort(){
    Stack<Integer> s1 = new Stack<>();
    s1.push(3);
    s1.push(1);
    s1.push(5);
    s1.push(2);
    s1.push(8);
    s1.push(2);
    assertEquals("|315282",s1.toString() );
    Stack.sort(s1);
    assertEquals("|853221",s1.toString() );
  }
  
  @Test
  public void testAnimalShelter(){
    AnimalShelter as  = new AnimalShelter();
    as.addCat("Mila");
    as.addCat("Shoo");
    as.addDog("Hiea");
    as.addCat("Peee");
    assertEquals("Mila",as.getAnimal() );
    assertEquals("Hiea",as.getDog() );
    assertEquals("Shoo",as.getCat() );
    assertNull(as.getDog() );
  }
  

}
