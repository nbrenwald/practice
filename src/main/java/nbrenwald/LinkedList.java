package nbrenwald;

import java.util.ArrayDeque;
import java.util.Deque;

public class LinkedList {
  private Node head;
  private int length = 0;

  public Node add(int v) {
    Node n = head;
    Node newNode = new Node(v);
    if (n == null) {
      head = newNode;
    } else {
      while (n.next != null) {
        n = n.next;
      }
      n.next = newNode;
    }
    length++;
    return newNode;
  }

  public void remove(int v) {
    // Find the element to remove.
    // Update the previous node's next pointer to point to this nodes next.
    Node n = head;
    if (n.value == v) {
      head = n.next;
      length--;
      return;

    }

    while (n.next != null) {
      if (n.next.value == v) {
        n.next = n.next.next;
        length--;
        return;
      }
      n = n.next;
    }

    return;

  }


  public class Node {
    private int value;
    private Node next;

    public Node(int v) {
      value = v;
    }
  }

  /*
   * Exercise 2-1 
   * If we can rely on other data structures such as a HashMap, this is relatively easy.
   * We would iterate through the list once counting occurrences of each key  in O(n) time.
   * We could then iterate through the HashMap calling remove n-1 times for each key > 1, or
   * iterate through the list and build one new copy.
   * If we have to do in place duplicate removal without other Data structures, 
   * we can use two pointers as shown below. For each element, we iterate through the 
   * remainder of the list removing any nodes with an equal value. Running time is close to O(n2).
   * Will be sum where i =  1 to n, of n-i
   */
  public void removeDuplicates() {

    Node n1 = head;
    while (n1 != null) {
      Node n2 = n1;
      while (n2.next != null) {
        if (n2.next.value == n1.value) {
         n2.next = n2.next.next;
          length--;

        } else {
          n2 = n2.next;
        }
      }
      n1 = n1.next;
    }


  }
  
  /*
   * Exercise 2-2
   * Return kth to last element from a singly linked list.
   * If the list contained 10 elements, then returning the last element means returning
   * the 10th element. Returning the 2nd to last element means returning the 9th element 
   * i.e. 10 - k -1  
   */
  
  public int returnKFromLast(int v){
    if(v>length) throw new IllegalArgumentException();
    Node p1 = head;
    Node p2 = head;
    // increment p2 by v-1;
    for(int i=1; i<v; i++){
      p2 = p2.next;
      System.out.println("incrementing p2");
    }
    System.out.println("p1 = "+p1.value);
    System.out.println("p2 = "+p2.value);
    
    while(p2.next!=null){
      p1=p1.next;
      p2=p2.next;
      
    }
    
    return p1.value;
  }
  
  /* 
   * Exercise 2-3 
   * Delete a given node from the list that contains it. 
   * Copy the value from the next node and update the next pointer to point to the next next node.
  */
  public void deleteMember(Node n){
    n.value=n.next.value;
    n.next=n.next.next;
    
  }
  
  /*
   * Exercise 2-4
   * Add two numbers stored as a linked list where the least significant digit is at the head.
   * Basically advance two pointers. Add, if greater than 9 carry 1.
   */
  
  public static LinkedList addLSDLists(LinkedList li1, LinkedList li2){
    LinkedList result = new LinkedList();
    Node p1 = li1.head;
    Node p2 = li2.head;
    int carry = 0;
    while(p1 != null || p2 !=null){
      int a = 0;
      int b = 0;
      if(p1 != null) {a = p1.value;p1=p1.next;} 
      if(p2 != null) {b = p2.value;p2=p2.next;} 
      int c = a+b+carry;
      carry = c/10;//(c > 9) ? 1 : 0;
      result.add(c % 10);
    }
    
    if(carry == 1){ result.add(1);}
    return result;
  }
  
  
  /*
   * Exercise 2-5
   * Add two numbers stored as a linked list where the Most Significant digit is at the head.
   * Could reverse the lists. Or, we could either reverse the input, then reverse the output,
   * or we could use powers of ten(would need to length to know where to start from)
   * or keep doubly linked list.
   */
  
  /*
   * Exercise 2-6
   * Is palindrome
   * Add first half to a stack, and then pop from stack whilst reading second half.
   */
  public boolean isPalindrome(){
    // stack to hold values from the first half of the stack.
    Deque<Integer> stack = new ArrayDeque<>(); 
    
    // fast and slow runners
    Node slow = head;
    Node fast = head;
    
    while(fast.next != null && fast.next.next!= null ){
      stack.add(slow.value);
      slow=slow.next;
      fast=fast.next.next;
    }
    // slow now either points to the true middle(odd length list), or element before the middle (even length list).
    if(fast.next!=null){stack.add(slow.value);}
    // advance slow so that it points to element after the middle.
    slow=slow.next;
    
    // now check slow against stack
    while(slow!=null){
      if(slow.value!=stack.peek()) return false;
      stack.pop();
      slow= slow.next;
    }
    
    return true;
    // time O(n) only one pass through the list, space O(n) for the stack
  }

  @Override
  public String toString() {
    String output = "";
    if (head != null) {
      output = "" + head.value;
      Node n = head;
      while (n.next != null) {
        output = output + ":" + n.next.value;
        n = n.next;
      }
    }
    return output;
  }
  
  public static void reverse(LinkedList li){
    if(li.head==null) return;
    Node current=li.head;
    Node previous = null;
    while(current.next != null){
      Node tmp = current;
      current = current.next;
      tmp.next=previous;
      previous = tmp;
    }
    current.next=previous;
    li.head = current;
  }

}
