package nbrenwald;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.LinkedList;

public class Skyline{

  Building[] buildings;
  Coordinate[] outline;

  Skyline(Building[] b){
   buildings = b;
   outline = createOutlineBruteForce();
  }

  Coordinate[] createOutlineBruteForce() { 
    // Get max x2
    int max = 0;
    for(Building b : buildings) {
      max = Math.max(max, b.xRight);
    } 
    int[] heightMap = new int[max+1];

    for(Building b : buildings) {
      for(int i = b.xLeft; i <= b.xRight; i++){
        heightMap[i] = Math.max(heightMap[i], b.height);
      }
    }
    System.out.println(Arrays.toString(heightMap));

    // now scan over coordinate and try record changes
    Coordinate previous = new Coordinate(0,0);
    List<Coordinate> outline = new ArrayList<>();

    for (int i = 0; i < heightMap.length; i++) {
      if (heightMap[i] != previous.y) {
        previous = new Coordinate(i, previous.y);
        //outline.add(previous);
        Coordinate next = new Coordinate(i, heightMap[i]);
        outline.add(next);
        previous = next;
      }
    }

    Coordinate[] result = new Coordinate[outline.size()];
    return outline.toArray(result);
  }

  public int maxArea() {
  Deque<Coordinate> s = new LinkedList<>();
  int maxArea = 0;

  for (int i = 0; i < outline.length; i++) {
    if (s.isEmpty() || outline[i].y > s.peek().y) {
      s.push(outline[i]);
    }
    else {
      while (!s.isEmpty() && s.peek().y > outline[i].y) {
        Coordinate c = s.pop();
        int width = (outline[i].x-1) - c.x;
        int height = c.y;
        if (maxArea < width * height)System.out.println(c);
        maxArea = Math.max(maxArea, width * height);
      }
      s.push(outline[i]);
    }
  }
  return maxArea;
    
  }

public static void main(String[] args) {


  Building[] b = new Building[6];
  b[0] = new Building(9, 17, 20);
  b[1] = new Building(9, 9, 14);
  b[2] = new Building(4, 8, 20);
  b[3] = new Building(11, 3, 6);
  b[4] = new Building(7, 1, 11);
  b[5] = new Building(3, 19, 21);
  
  Skyline s = new Skyline(b);
  System.out.println(Arrays.toString(s.createOutlineBruteForce()));
  System.out.println(s.maxArea());
}
 

private static class Coordinate{
 int x;
 int y;

  Coordinate(int x, int y){
    this.x=x;
    this.y=y;
  }

  @Override
  public String toString(){
   return "("+x+", "+y+")";
  }
}

private static class Building{
 int height;
 int xLeft;
 int xRight;

 Building(int h, int x1, int x2){
  height = h;
  xLeft = x1;
  xRight = x2;
 }
}




}