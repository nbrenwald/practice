package nbrenwald;

public class StackQueue<T extends Comparable<T>> {
  private Stack<T> newest;
  private Stack<T> oldest;
  
  public StackQueue(){
    newest = new Stack<>();
    oldest = new Stack<>();
  }
  
  public void enQueue(T n){
    newest.push(n);
  }
  
  public T deQueue(){
    if(oldest.isEmpty()){
      if(newest.isEmpty()) return null;
      while(!newest.isEmpty()){
        oldest.push(newest.pop());
      }
    }
    return oldest.pop();
    
  }
  
  public boolean isEmpty(){
    return newest.isEmpty() && oldest.isEmpty();
  }

}
