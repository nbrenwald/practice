package nbrenwald;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListsTest {

  @Test
  public void testRemoveDuplicates() {
    List<Integer> list = new List<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(2);
    List<Integer> list2 = new List<>();
    list2.add(1);
    list2.add(2);
    list2.add(3);
    list2.add(2);
    assertTrue(list.equals(list2));
  }

  

}
