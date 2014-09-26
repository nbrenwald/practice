package nbrenwald;

import static org.junit.Assert.*;
import nbrenwald.LinkedList.Node;

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
  
  @Test
  public void testDeleteNode(){
    LinkedList li = new LinkedList();
    li.add(1);
    li.add(2);
    Node n = li.add(3);
    li.add(4);
    li.add(5);
    li.add(6);
    assertEquals(li.toString(), "1:2:3:4:5:6");
    li.deleteMember(n);
    assertEquals(li.toString(), "1:2:4:5:6");
  }
  
  @Test
  public void testAddLSDLists(){
    LinkedList li1 = new LinkedList();
    li1.add(9);
    li1.add(8);
    li1.add(8);
    LinkedList li2 = new LinkedList();
    li2.add(7);
    li2.add(8);
    li2.add(9);
    li2.add(9);
    assertEquals(li1.toString(), "9:8:8");
    assertEquals(li2.toString(), "7:8:9:9");
    assertEquals(LinkedList.addLSDLists(li1, li2).toString(), "6:7:8:0:1");
  }
  
  @Test
  public void testIsPalindrome(){
    
    LinkedList li1 = new LinkedList();
    li1.add(1);
    assertTrue(li1.isPalindrome());
    
    LinkedList li2 = new LinkedList();
    li2.add(1);
    li2.add(1);
    assertTrue(li2.isPalindrome());
    
    LinkedList li3 = new LinkedList();
    li3.add(1);
    li3.add(2);
    assertFalse(li3.isPalindrome());
    
    LinkedList li4 = new LinkedList();
    li4.add(1);
    li4.add(2);
    li4.add(1);
    assertTrue(li4.isPalindrome());
    
    LinkedList li5 = new LinkedList();
    li5.add(1);
    li5.add(2);
    li5.add(3);
    assertFalse(li5.isPalindrome());
    
    
  }
  
}
