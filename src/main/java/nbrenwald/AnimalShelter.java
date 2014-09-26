package nbrenwald;

import java.util.ArrayDeque;
import java.util.Deque;

public class AnimalShelter {
  private Deque<AnimalAbandonedEvent> cats = new ArrayDeque<>();
  private Deque<AnimalAbandonedEvent> dogs = new ArrayDeque<>();
  private static int counter = 0;
  
  
  
  public void addCat(String s){
    cats.add(new AnimalAbandonedEvent(s));
  }
  public void addDog(String s){
    dogs.add(new AnimalAbandonedEvent(s));
  }
  
  public String getCat(){
    if (cats.isEmpty()) return null;
    return cats.remove().name;
  }
  public String getDog(){
    if (dogs.isEmpty()) return null;
    return dogs.remove().name;
  }
  
  public String getAnimal(){
    return (cats.peek().time < dogs.peek().time) ? cats.remove().name : dogs.remove().name;
  }
  
  public class AnimalAbandonedEvent{
    private int time;
    private String name;
    public AnimalAbandonedEvent(String n){
     name = n;
     time = counter++;
    }
    
  }
  
}
