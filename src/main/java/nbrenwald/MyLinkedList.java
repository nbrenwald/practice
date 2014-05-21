package nbrenwald;

public class MyLinkedList {
  private int length = 0;
  private Node head = null;
  
  private class Node{
    private Node nextElement;
    private int value;
    
    public Node(int v){
      value =v;
    }
    
  }
  
  public void add(int value){
    Node newNode = new Node(value); 
    if(head == null){ 
      head = newNode;
    }
    else {
      Node node = head;
      while(node.nextElement != null){
        node = node.nextElement;
      }
      node.nextElement=newNode; //need a pointer to end, this is too slow.
    }
    length ++;
  }
}
