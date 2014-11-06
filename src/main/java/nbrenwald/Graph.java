package nbrenwald;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
  public Map<Integer, List<Edge>> adj;

  public Graph() {
    adj = new HashMap<>();
  }

  public void addEdge(Integer from, Integer to, int weight) {
    if(!adj.containsKey(from)){
      List<Edge> li = new ArrayList<>();
      adj.put(from, li);
    }
    if(!adj.containsKey(to)){
      List<Edge> li = new ArrayList<>();
      adj.put(to, li);
    }
    
    adj.get(from).add(new Edge(to, weight));
    adj.get(to).add(new Edge(from, weight));
    
  }

  public boolean isPath(Integer from, Integer to) {
    if ((!adj.isEmpty()) && (from != to) && adj.containsKey(from)) {

      Set<Integer> visited = new HashSet<>();
      List<Integer> vertices = new ArrayList<>();

      vertices.add(from);
      visited.add(from);

      while (!vertices.isEmpty()) {
        Integer currentVertex = vertices.remove(0);
        if (currentVertex == to)
          return true;
        List<Edge> adjacentVertices = adj.get(currentVertex);
        for (Edge i : adjacentVertices) {
          if (!visited.contains(i)) {
            vertices.add(i.to);
            visited.add(i.to);
          }
        }
      }
    }

    return false;
  }
  
  
  public int primsMST(){
    //calculates weight of minimum spanning tree using prims algorithm
    Set<Integer> visited = new HashSet<>(); // nodes that have been included
    int cost = 0;
    List<Edge> potentialEdges = new ArrayList<>();// maintain a list of potential edges
    
    for(Edge e: adj.get(1)){
      potentialEdges.add(e);
    }
    visited.add(1);// Start with node 1
    
    while(visited.size() != adj.size()){
      //pick min weight which moves us somewhere else.
      Edge nextEdge = potentialEdges.get(0);
      int maxWeight = Integer.MAX_VALUE;
      for(Edge e: potentialEdges){
       if((!visited.contains(e.to)) && e.weight< maxWeight) {
         nextEdge = e;
         maxWeight = e.weight;
       }
      }
      cost += nextEdge.weight;
      visited.add(nextEdge.to);
      potentialEdges.remove(nextEdge);
      for(Edge e: adj.get(nextEdge.to)){
        potentialEdges.add(e);
      }
    }
    
    return cost;
  }
  
  public int shortestPathDijkstra(int start, int to){
    
    int[] dist = new int[adj.size()];
    int[] previous = new int[adj.size()];
    Set<Integer> unvisited =  new HashSet<>();
    
    for(Integer i : adj.keySet()){
      if(i != start){
        dist[i-1] = Integer.MAX_VALUE;
        previous[i-1] = -1;
        
      }
      unvisited.add(i);
    }
    
    
    while(!unvisited.isEmpty()){
      System.out.println("Unvisited = "+ unvisited);
      int u = -1;
      int uWeight = Integer.MAX_VALUE;
      for(Integer i : unvisited){
        if(dist[i-1] <= uWeight){
          u=i;
          uWeight = dist[i-1];
        }
      }
      
      unvisited.remove(u);
      System.out.println("Print u = "+u);
      
      for(Edge e : adj.get(u)){
        if(unvisited.contains(e.to)){
          int alt = uWeight + e.weight;
          if(alt < dist[e.to-1]){
            dist[e.to-1] = alt;
            previous[e.to-1] = u;
          }
        }
      }
      
    }
    
   /* 1  while Q is not empty:                  // The main loop
     12          u := vertex in Q with min dist[u]  // Source node in first case
     13          remove u from Q 
     14          
     15          for each neighbor v of u:           // where v has not yet been removed from Q.
     16              alt := dist[u] + length(u, v)
     17              if alt < dist[v]:               // A shorter path to v has been found
     18                  dist[v]  := alt 
     19                  previous[v]  := u 
     20              end if
     21          end for
     22      end while
     23      return dist[], previous[]
     24  end function
    */
    return dist[to-1];
    
  }
}
