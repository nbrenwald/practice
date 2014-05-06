package nbrenwald;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class LinkedListsTest {

  @Test
  public void testRemoveDuplicates() {
    List list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(2);
    List<Integer> list2 = new LinkedList<>();
    list2.add(1);
    list2.add(2);
    list2.add(3);
    list2.add(2);
    assertTrue(list.equals(list2));
  }

  

}
