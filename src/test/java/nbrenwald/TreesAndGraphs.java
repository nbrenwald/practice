package nbrenwald;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TreesAndGraphs {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void testIsBalanced() {
    BinaryTreeNode node = new BinaryTreeNode(1); 
    node.insertInOrder(2);
    node.insertInOrder(3);
    node.insertInOrder(4);
    node.insertInOrder(5);
    node.insertInOrder(6);
    assertEquals(5,node.getHeight());
  }

  @Test
  public void testBuildFromArray() {
    /*BinaryTreeNode node = new BinaryTreeNode(); 
    node.insertInOrder(5);
    node.insertInOrder(3);
    node.insertInOrder(2);
    node.insertInOrder(4);
    node.insertInOrder(7);
    node.insertInOrder(6);
    node.insertInOrder(8);
    assertEquals(2,node.getHeight());*/
    
    int[] sortedArray = {2,3,4};
    BinaryTreeNode node = BinaryTreeNode.valueOf(sortedArray); 
    assertEquals(1,node.getHeight());
    
    int[] sortedArray2 = {2,3,4,5};
    BinaryTreeNode node2 = BinaryTreeNode.valueOf(sortedArray2); 
    assertEquals(2,node2.getHeight());
  }
  
  @Test
  public void testIsBST(){
    BinaryTreeNode node = new BinaryTreeNode(); 
    node.insertInOrder(5);
    assertTrue(BinaryTreeNode.isBST(node, null));
    node.insertInOrder(4);
    assertTrue(BinaryTreeNode.isBST(node, null));
    node.insertInOrder(8);
    node.insertInOrder(7);
    node.insertInOrder(9);
    assertTrue(BinaryTreeNode.isBST(node, null));
    
    BinaryTreeNode node2 = new BinaryTreeNode(); 
    node2.insertInOrder(1);
    node2.insertInOrder(2);
    node2.insertInOrder(3);
    node2.insertInOrder(4);
    node2.insertInOrder(5);
    assertTrue(BinaryTreeNode.isBST(node2, null));
    
    BinaryTreeNode node_5 = new BinaryTreeNode(5);
    BinaryTreeNode node_3 = new BinaryTreeNode(3);
    BinaryTreeNode node_8 = new BinaryTreeNode(8);
    BinaryTreeNode node_4 = new BinaryTreeNode(4);
    node_5.left=node_3;
    node_5.right=node_8;
    node_8.left=node_4; 
    assertFalse(BinaryTreeNode.isBST(node_5, null));
    
  }
  
  @Test
  public void testGetSuccessor(){
    BinaryTreeNode node_5 = new BinaryTreeNode(5);
    BinaryTreeNode node_3 = new BinaryTreeNode(3);
    BinaryTreeNode node_2 = new BinaryTreeNode(2);
    BinaryTreeNode node_4 = new BinaryTreeNode(4);
    BinaryTreeNode node_6 = new BinaryTreeNode(6);
    BinaryTreeNode node_7 = new BinaryTreeNode(7);
    BinaryTreeNode node_8 = new BinaryTreeNode(8);
    node_5.left=node_2;
    node_2.parent= node_5;
    node_5.right=node_7;
    node_7.parent= node_5;
    
    node_2.right=node_3;
    node_3.parent= node_2;
    node_3.right=node_4;
    node_4.parent= node_3;
    node_7.left=node_6; 
    node_6.parent= node_7;
    node_7.right=node_8;
    node_8.parent= node_7;
    assertEquals(5,BinaryTreeNode.getBSTSuccessor(node_4).data);
    assertEquals(3,BinaryTreeNode.getBSTSuccessor(node_2).data);
    assertEquals(6,BinaryTreeNode.getBSTSuccessor(node_5).data);
    assertNull(BinaryTreeNode.getBSTSuccessor(node_8));
  }
  
  @Test
  public void testIsSubTree(){
    int[] sortedArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    BinaryTreeNode tree1 = BinaryTreeNode.valueOf(sortedArray);
    assertEquals(3, tree1.getHeight());
    int[] sortedArray2 = {1,2,3,4,5,6,7};
    BinaryTreeNode tree2 = BinaryTreeNode.valueOf(sortedArray2);
    
    assertTrue(BinaryTreeNode.containsSubTree(tree1, tree2));
    
  }
  
  @Test
  public void testFB(){
    BinaryTreeNode.fb(100);
  }
  
  @Test
  public void testPrintAllPaths(){
    int[] sortedArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    BinaryTreeNode tree1 = BinaryTreeNode.valueOf(sortedArray);
    for(String s : BinaryTreeNode.printAllPaths(tree1, 6)){
      System.out.println(s);
    }
    
  }
  
  @Test
  public void testIsPath(){
    Graph g = new Graph();
    g.addEdge(1, 2,1);
    g.addEdge(2, 3, 2);
    g.addEdge(3, 4, 3);
    g.addEdge(3, 5, 4);
    g.addEdge(4, 5, 5);
    g.addEdge(2, 5, 3);
    g.addEdge(1, 5, 2);
    
    assertTrue(g.isPath(1, 4));
    assertFalse(g.isPath(6, 1));
    assertFalse((new Graph()).isPath(1, 2));
  }
  
  @Test
  public void testPrimsMST(){
    Graph g = new Graph();
    g.addEdge(1, 2, 1);
    g.addEdge(2, 3, 2);
    g.addEdge(3, 5, 4);
    g.addEdge(5, 4, 5);
    g.addEdge(2, 5, 3);
    g.addEdge(1, 5, 2);
    assertEquals(10, g.primsMST());
    
    g.addEdge(3, 4, 3);
    assertEquals(8, g.primsMST());
  }
  
  @Test
  public void testShortestPath(){
    Graph g = new Graph();
    g.addEdge(1, 2, 1);
    g.addEdge(2, 3, 2);
    g.addEdge(3, 5, 4);
    g.addEdge(5, 4, 5);
    g.addEdge(2, 5, 3);
    g.addEdge(1, 5, 2);
    g.addEdge(3, 4, 3);
    assertEquals(6, g.shortestPathDijkstra(1, 4));
    
    
  }
  
  
}
