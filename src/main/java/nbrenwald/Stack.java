package nbrenwald;


public class Stack<T extends Comparable<T>> {
  private Node<T> top;
  
  public T peek(){
    return top.value;
  }
  
  public T pop(){
    if(top == null) throw new IllegalStateException();
    T a = top.value;
    top = top.next;
    return a;
  }
  
  public void push(T n){
    Node<T> newNode = new Node<T>(n);
    if(top != null){
      newNode.next = top;
      newNode.min = (n.compareTo(top.min) < 0) ? n : top.min;}
      top = newNode;
  }
  
  public T min(){
    return top.min;
  }
  
  
  
  public class Node<E> {
    private E value;
    private Node<E> next;
    private E min;

    public Node(E v) {
      value = v;
      min = v;
    }
  }
  
  // n represents the disks
  // Use a stack for each rod.
  public static <T extends Comparable<T>> void towersOfHanoi(int n, Stack<T> src, Stack<T> temp, Stack<T> dest){
    if(n>0){
      
    
    towersOfHanoi(n-1, src, dest, temp);
    dest.push(src.pop());
    towersOfHanoi(n-1, temp, src, dest);
    }
      
    }
 

  public boolean isEmpty() {
    return (top==null) ? true : false;
  }

  @Override
  public String toString() {
    String s = "";
    Node<T> n = top;
    while(n != null){
      s = n.value + s ;
      n=n.next;
    }
    return "|" + s;
  }
  
  //
  public static <T extends Comparable<T>> void sort(Stack<T> s){
    // Space O(N), time O(N^2)
    Stack<T> temp = new Stack<>();
    T value;
    
    while(!s.isEmpty()){
      value = s.pop();
      
      // While the value we are processing is less than the top value on the temp stack, move items
      // from temp back to the stack
      while(!temp.isEmpty() && value.compareTo(temp.peek()) <0){
        s.push(temp.pop());
      }
      
      // Now we know the correct position for our value on the temp stack
      temp.push(value);
      
      // Now we can move back items to the temp stack until we find a value which is smaller than
      // top of the temp which means its time to re start the loop
      while(!s.isEmpty() && s.peek().compareTo(temp.peek())>0){
        temp.push(s.pop());
      }
    }
    
    // Temp stack now contains ordered items, with largest item on the top.
    // We move one by one back on to the original stack to finish with largest item on the bottom.
    while(!temp.isEmpty()){
      s.push(temp.pop());
    }
  }

}
