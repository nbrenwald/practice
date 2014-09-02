package nbrenwald;

public class LinkedList {
  private Node head;
  private int length = 0;

  public void add(int v) {
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


  private class Node {
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
   * Exercise 2-1
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

}
