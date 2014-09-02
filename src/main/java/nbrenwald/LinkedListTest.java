package nbrenwald;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testRemoveDuplicates() {
    LinkedList li = new LinkedList();
    li.add(3);
    li.add(4);
    li.add(3);
    li.add(3);
    li.add(5);
    li.add(3);
    assertEquals(li.toString(), "3:4:3:3:5:3");
    li.removeDuplicates();
    assertEquals(li.toString(), "3:4:5");
  }

  @Test
  public void testAdd() {
    LinkedList li = new LinkedList();
    li.add(3);
    assertEquals(li.toString(), "3");
    li.add(4);
    assertEquals(li.toString(), "3:4");
  }

  @Test
  public void testRemoveLastElement() {
    LinkedList li = new LinkedList();
    li.add(4);
    li.add(3);
    li.add(5);
    assertEquals(li.toString(), "4:3:5");
    li.remove(5);
    assertEquals(li.toString(), "4:3");
  }

  @Test
  public void testRemove() {
    LinkedList li = new LinkedList();
    li.add(3);
    li.add(4);
    li.add(3);
    li.add(3);
    li.add(5);
    assertEquals(li.toString(), "3:4:3:3:5");
    li.remove(3);
    assertEquals(li.toString(), "4:3:3:5");
    li.remove(3);
    assertEquals(li.toString(), "4:3:5");
    li.remove(5);
    assertEquals(li.toString(), "4:3");
  }

  @Test
  public void testRemoveKLastElement() {
    LinkedList li = new LinkedList();
    li.add(1);
    li.add(2);
    li.add(3);
    li.add(4);
    li.add(5);
    li.add(6);
    assertEquals(li.toString(), "1:2:3:4:5:6");
    assertEquals(li.returnKFromLast(3), 4);
    try{
      li.returnKFromLast(7);
    }
    catch(IllegalArgumentException e){
      assertTrue(true);
      
    }
  }
}
