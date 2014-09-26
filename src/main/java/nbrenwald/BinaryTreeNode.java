package nbrenwald;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeNode {

  public BinaryTreeNode parent;
  public BinaryTreeNode left;
  public BinaryTreeNode right;
  private int height;
  public int data;
  
  public BinaryTreeNode(int n){
    data = n;
  }
  
  public BinaryTreeNode() {
    // TODO Auto-generated constructor stub
  }

  public void insertInOrder(int n){
    if(data == 0){
      data=n;
    }
    else if(n < data){
      if(left == null){ 
        BinaryTreeNode newNode = new BinaryTreeNode(n);
        left = newNode;
        newNode.parent=this;
        updateHeight(newNode);
      }
      else {left.insertInOrder(n);}
    } 
    else {
      if(right == null){ 
        BinaryTreeNode newNode = new BinaryTreeNode(n);
        right = newNode;
        newNode.parent=this;
        updateHeight(newNode);
        }
      else {right.insertInOrder(n);}
    }
  }
  
  public static void updateHeight(BinaryTreeNode tn){
    if(tn.left == null && tn.right == null){
      tn.height = 0;
    }
    else {
      tn.height = 1 + Math.max(tn.left == null ?0: tn.left.height, tn.right == null ?0: tn.right.height);
    }
    
    if(tn.parent != null) updateHeight(tn.parent);
    
    
  }
  
  public boolean isLeaf(){
    return (left == null && right == null);
  }
  
  public int getHeight(){
    return height;
  }
  
  // This version of get height relies on supplementing our data structure with an additional height field.
  // if we need to check if it is balanced frequently, this may be a good choice as running time will be O(lg n)
  // This will slow down inserts and deletes as we may need to recalculate height n times.
  public static boolean isBalanced(BinaryTreeNode tn){
    if(tn == null) {
      return true;
      }
    else if(tn.left == null && tn.right == null){
      return true;
    }
    else {
      if(Math.abs(((tn.left == null) ? 0 : tn.left.height) - ((tn.right == null) ? 0: tn.right.height)) > 1) {
        return false;
      }
      else {
        return isBalanced(tn.left) && isBalanced(tn.right);
      }
    }
  }
  
  public static BinaryTreeNode valueOf(int[] sortedArray){
    BinaryTreeNode newTree = new BinaryTreeNode();
    insertNodesFromArray(newTree, sortedArray, 0, sortedArray.length);
    return newTree;
  }

  private static void insertNodesFromArray(BinaryTreeNode tree, int[] sortedArray, int start, int length) {
    if(length > 0) {
      if(length == 1) {
        tree.insertInOrder(sortedArray[start]);
        System.out.println("inserting value "+sortedArray[start]);
        }
      else {
        int i = length/2;
        tree.insertInOrder(sortedArray[start+i]);
        System.out.println("inserting value "+sortedArray[start+i]);
        insertNodesFromArray(tree, sortedArray, start, i);
        insertNodesFromArray(tree, sortedArray, start+i+1, length-(i+1));
      }
    
    
    
    }
    
  }
  
  public static boolean isBST(BinaryTreeNode root, Integer lastValue){
    // true if for all nodes, all nodes in left are <= root and all nodes on the right are > than root
    // perform an inorder traversal keeping track of last value printed which should always be greater.
    if(root==null) return true;
    
    if(!isBST(root.left, lastValue)) return false;
    
    //if(! (root.left.data<=root.data && root.right.data > root.data) ) return false;
    if(lastValue!= null && root.data <=lastValue) return false;
    lastValue = root.data;
    System.out.println("node "+ lastValue);
    
    if(!isBST(root.right, lastValue)) return false;
    
    return true;
    
  }
  
  public static BinaryTreeNode getBSTSuccessor(BinaryTreeNode node){
    BinaryTreeNode successor;
    if(node.right!=null){
      successor = node.right;
      while(successor.left!=null){
        successor = successor.left;
      }
      return successor;
    }
    else {
      successor = node.parent;
      while(successor != null && successor.data <= node.data){
        successor = successor.parent;
      }
      if(successor == null) return null;
      else return successor;
    }
    
  }
  
  
  public static boolean containsSubTree(BinaryTreeNode root,BinaryTreeNode sub){
    //System.out.println("iterate "+root.left.data + "   "+ root.right.data + "    "+ sub.data);
    if(sub == null) return true;
    
    if(root==null) return false;
    
    if(root.data == sub.data){
      System.out.println("Found potential match "+root.data);
      if(matches(root, sub)) return true;
    }
    
    //System.out.println("iterate root.data="+root.data+"  root.left.data="+root.left.data + "  root.right.data="+ root.right.data + "  sub.data="+ sub.data);
    return ( containsSubTree(root.left,sub) || containsSubTree(root.right, sub) );

    
    
  }

  private static boolean matches(BinaryTreeNode root, BinaryTreeNode sub) {
    // Compare two trees
    System.out.println("matches ");
    if(root == null && sub == null)return true;
    
    if(root==null || sub==null) return false;
    
    if(root.data != sub.data) return false;
    
    return ( matches(root.left, sub.left) && matches(root.right, sub.right) );
    
  }
  
  public static void fb(int n){
    
    for(int i = 1; i<= n; i++){
      if(i%3!=0 && i%5!=0) System.out.println(i);
      else System.out.println(((i%3==0)?"F":"") + ((i%5==0)?"B":"") );
    }
  }
  
  public static List<String> printAllPaths(BinaryTreeNode root, int n){
    List<String> result = new ArrayList<>();
    printAllPaths(root, result, n);
    return result;
  }

  private static void printAllPaths(BinaryTreeNode root, List<String> result, int n) {
    if(root!=null){
      printAllPaths(root.left, result, n);
      pathsFromThisNode(root, result,"", n, 0);
      printAllPaths(root.right, result, n);
    }
    
  }

  private static void pathsFromThisNode(BinaryTreeNode root, List<String> result, String currentpath, int n, int total) {
    if(root!=null){
      currentpath = currentpath+"->"+root.data;
      total=total+root.data;
      if(total == n){
        result.add(currentpath);
      }
      pathsFromThisNode(root.left,result,currentpath, n, total);
      pathsFromThisNode(root.right,result,currentpath, n, total);
    }
    
    
  }
  
}
