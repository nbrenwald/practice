package nbrenwald;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RecursiveProblemsTest {

  @Before
  public void setUp() throws Exception {}

  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  @Test
  public void testCountSteps() {
    assertEquals(0, RecursiveProblems.countSteps(0));
    assertEquals(1, RecursiveProblems.countSteps(1));
    assertEquals(2, RecursiveProblems.countSteps(2));
    assertEquals(4, RecursiveProblems.countSteps(3));
    assertEquals(7, RecursiveProblems.countSteps(4));
    assertEquals(0, RecursiveProblems.countSteps(-7));
  }
  
  @Test
  public void testCountPaths() {
    assertEquals(1, RecursiveProblems.countPaths(0,0,0,1));
    assertEquals(1, RecursiveProblems.countPaths(0,0,1,0));
    assertEquals(2, RecursiveProblems.countPaths(0,0,1,1));
    assertEquals(3, RecursiveProblems.countPaths(0,0,2,1));
    assertEquals(3, RecursiveProblems.countPaths(0,0,2,1));

  }
  
  
  @Test
  public void testGetSubsets(){
    Set<Integer> set = new HashSet<>();
    int limit = 5;
    for(int i = 0 ; i<limit; i++){
    set.add(i);
    set.add(i);
    set.add(i);
    set.add(i);}
    Set<Set<Integer>> subsets = RecursiveProblems.getSubsets(set);
    assertEquals((int)Math.pow(2, limit), subsets.size());
  }
  
  @Test
  public void testGetAllPerms(){
    String s = "abcde";
    
    java.util.List<String> li = RecursiveProblems.getAllPerms(s);
    for(String s1 : li)System.out.println(s1);
    assertEquals(120, li.size());
    
  }
  
  @Test
  public void testGetAllPermsBT(){
    String s = "abcde";
    java.util.List<String> li = new ArrayList<>();
    RecursiveProblems.getAllPermsBT(s,li);
    for(String s1 : li)System.out.println(s1);
    assertEquals(120, li.size());
    
  }
  
  @Test
  public void testIsMagicArray(){
    int[] a = {0,2,3,6,7,8,9,10,11};
    assertTrue(RecursiveProblems.isMagicArray(a));
    int[] a2 = {0,1,4,5,6,7,8,9,10,11};
    assertTrue(RecursiveProblems.isMagicArray(a2));
    
    int[] a3 = {2,3,4,5,6,7,8,9};
    assertFalse(RecursiveProblems.isMagicArray(a3));
  }
  
  @Test
  public void testPairsDuplicates(){
    Set<String> pairs = RecursiveProblems.createPairsDuplicates(4);
    for(String s : pairs){
      System.out.println(s);
    }
    assertTrue(true);
  }
  
  @Test
  public void testBracketsBT(){
    RecursiveProblems.bracketsBT("", 3, 0, 0);
  }
  
  @Test
  public void testFibRecursive(){
    assertEquals(832040 , RecursiveProblems.fib_r(30));
  }
  
  @Test
  public void testFibCache(){
    assertEquals(832040 , RecursiveProblems.fib_c(30));
  }
  
  @Test
  public void testFibDP(){
    assertEquals(832040 , RecursiveProblems.fib_dp(30));
  }
  
  @Test
  public void testBinomialCoeffDP(){
    assertEquals(1,RecursiveProblems.binomialCoeffDP(1000, 0));
    assertEquals(1,RecursiveProblems.binomialCoeffDP(1000, 1000));
    assertEquals(6,RecursiveProblems.binomialCoeffDP(4, 2));
    assertEquals(19600,RecursiveProblems.binomialCoeffDP(50, 3));
    assertEquals(166167000,RecursiveProblems.binomialCoeffDP(1000, 3));
  }
  
  @Test
  public void testGetMoney(){
    Set<String> solutions = new HashSet<>();
    int[] solution = {0,0,0,0};
    int target = 38;
    RecursiveProblems.getMoney(solutions, solution, target);
    
    for(String s : solutions){
      System.out.println(s);
    }
    
    //assertEquals(solutions.size(), RecursiveProblems.getMoneyDP(target));
  }
  
  @Test
  public void testGetParanExpression(){
    List<String> li = new ArrayList<>();
    RecursiveProblems.getParanExpression(li, "1^0|0|1", false);
    assertEquals(2,li.size());
    assertTrue(li.contains("1^((0|0)|1)"));
    assertTrue(li.contains("1^(0|(0|1))"));
  }
  
  

}
