package nbrenwald;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecursiveProblems {
  public static int countSteps(int n){
    // print paths
    if(n<=0) return 0;
    else if(n==1) return 1;
    else if(n==2) return 2;
    else if(n==3) return 4;
    else return countSteps(n-3) + countSteps(n-2) + countSteps(n-1);
  }
  
  public static int countPaths(int startX, int startY, int targetX, int targetY){
    if(startX > targetX || startY > targetY) return 0;
    if(startX == targetX && startY == targetY) return 1;
    return countPaths(startX+1, startY,targetX,targetY) + countPaths(startX, startY+1,targetX,targetY); 
  }
  
  
  // Recursive, keyword all in question.Runtime with set size n - >
  // call getSubsets n times, then iterate over all subsets, which gets bigger by factor of 2 each time, so 
  // exponential.
  public static <T> Set<Set<T>> getSubsets(Set<T> set){
    
    Set<Set<T>> allSubsets= new HashSet<Set<T>>();
    
    if(set.isEmpty()){
      allSubsets.add(new HashSet<T>());
    }
    else {
    
    T item = set.iterator().next();
    set.remove(item);
    allSubsets = getSubsets(set);
    Set<Set<T>> moreSubsets= new HashSet<Set<T>>();
    for(Set<T> s : allSubsets){
      Set<T> newSet = new HashSet<>(s);
      newSet.add(item);
      moreSubsets.add(newSet);
    }
    allSubsets.addAll(moreSubsets);
    }
    return allSubsets;
  }
  
  public static void getAllPermsBT(String s, List<String> result){
    char[] cArray = s.toCharArray();
    backTrack(cArray, result, "");
  }
  
  public static void backTrack(char[] ca, List<String> result, String a){
    if(a.length()==ca.length) result.add(a);
    else{
      // generate the next possible candidates
      // build a hashmap of the chars and counts in s., decrement
      Map<Character, Integer> charCount = new HashMap<>();
      for(int i=0; i< ca.length;i++){
       if(charCount.containsKey(ca[i]))  {
         int count =charCount.get(ca[i]); 
         charCount.put(ca[i], count ++);
       }
       else charCount.put(ca[i], 1);
      }
      
      char[] ra = a.toCharArray();
      for(int i = 0 ; i< ra.length; i++){
        int count =charCount.get(ra[i]); 
        charCount.put(ra[i], --count);
      }
      
      
      Set<Character> candidates = new HashSet<>();
      for(Character c : charCount.keySet()){
        if(charCount.get(c) > 0) candidates.add(c);
      }
      
      for(Character c : candidates){
        backTrack(ca, result, a+c);
      }
      
    }
  }
  
  
  public static List<String> getAllPerms(String s){
    List<String> li = new ArrayList<>();
    if(s.length()==1){
      li.add(s);
    }
    else{
      char c = s.charAt(0);
      List<String> liStrings = getAllPerms(s.substring(1));
      for(String s2 : liStrings){
        for(int i =0; i <= s2.length(); i++){
          String s3 = s2.substring(0,i) + c + s2.substring(i);
          li.add(s3);
        }
      }
    }
    return li;
  }
  
  public static boolean isMagicArray(int[] a){
    // check a[mid]. if greater than i, then look in a[0],a[mid-1]
    //if equal return true
    int mid = a.length/2;
    int low = 0;
    int high = a.length-1;
        while(low <= high){
          System.out.println("Start low = "+ low+" mid = "+mid+" high = "+high);
          if(a[mid] == mid) return true;
          high = mid -1;
          mid = (high-low) / 2;
          System.out.println("End low = "+ low+" mid = "+mid+" high = "+high);
        }
    return false;
  }
  
  public static Set<String>  createPairsDuplicates(int n){
    Set<String> pairs = new HashSet<>();
    if(n==0) return pairs;
    if(n==1) {
      pairs.add("()");
      return pairs;
    }
    else {
      Set<String> smallerPairs=createPairsDuplicates(n-1);
      for(String s : smallerPairs){
        pairs.add(s+"()");
        pairs.add("()"+s);
        pairs.add("("+s+")");
      }
      
      return pairs;
      
    }
    }
    
//  public static Set<String> createPairs(int n, int l, int r){
//    Set<String> pairs = new HashSet<>();
//    if(n==0) return pairs;
//    if(l< n){
//      pairs.
//    }
//    if(r<l){
//      
//    }
//  }
  
  public static void bracketsBT(String s, int n, int l, int r){
    if(s.length()== 2*n)System.out.println(s);
    if(l < n)bracketsBT(s+"(", n, l+1, r);
    if(r < l) bracketsBT(s+")", n, l, r+1);
    
  }
  
    public static int fib_r(int n){
      if(n==0)return 0;
      if(n==1)return 1;
      return fib_r(n-1) + fib_r(n-2);
    }
    
    
    
    
    public static int fib_c(int n){
      int[] cache = new int[n+1];
      for(int i=0;i<cache.length;i++){
        cache[i]=-1;
      }
      cache[1]=1;
      return fib_rc(n, cache);
    }

    private static int fib_rc(int n, int[] cache) {
      if(n==1 || n==0)return n;
      if(cache[n-1]==-1) cache[n-1]=fib_rc(n-1, cache);
      if(cache[n-2]==-1) cache[n-2]=fib_rc(n-2, cache);
      return cache[n-2]+cache[n-1];
    }
    
    public static int fib_dp(int n){
      int[] cache = new int[n+1];
      cache[1]=1;
      
      for(int i = 2; i<=n; i++){
        cache[i]= cache[i-1]+cache[i-2];
      }
      return cache[n];
    }
    
    public static int binomialCoeffDP(int n, int m){
      // calculate n choose m
      // use recurrence n choose m = n-1 choose m-1 + n-1 choose m
      // n choose 0 is 1, the empty set
      // n choose n is 1, the complete set
      int[][] cache = new int[n+1][n+1];
      for(int i=0;i<=n;i++){
        cache[i][0]=1;
        cache[i][1]=i;
        cache[i][i]=1;
      }
      for(int i=2;i<=n;i++){
        for(int j=2; j<=n; j++){
          cache[i][j] = cache[i-1][j] + cache[i-1][j-1];
        }
      }
      return cache[n][m];
    }
    
   public static int getMoneyDP(int target){
      int[] base = {1,1,2,4,13};
      int[] ways = new int[target+1];
      ways[0] =0;
      ways[1] =1;
      
      for(int i = 2; i<=target; i++){
        if(i%25 ==0) ways[i]+= ways[i-25]+base[4];
        if(i%10 ==0) ways[i]+= ways[i-10]+base[3];
        if(i%5 ==0) ways[i]+= ways[i-5]+base[2];
        ways[i]+=ways[i-1];
        System.out.println("ways "+i+ " is "+ways[i]);
      }
      return ways[target];
    }
    
    
    public static void getMoney(Set<String> solutions, int[] solution, int target){
    //money[0] is cents, money[1] is nickels, money[2] is dimes, money[3] is quarters
      int value = solution[0]+(5*solution[1])+(10*solution[2])+(25*solution[3]);
      if(value == target){
        solutions.add(solution[0]+"C "+solution[1]+"N "+solution[2]+"D "+solution[3]+"Q");
      }
      else if(value >target) return;
      else {
        //try all alternatives
        int[] solution1 = solution.clone();
        solution1[0] += 1;
        int[] solution2 = solution.clone();
        solution2[1] += 1;
        int[] solution3 = solution.clone();
        solution3[2] += 1;
        int[] solution4 = solution.clone();
        solution4[3] += 1;
        getMoney(solutions, solution1, target);
        getMoney(solutions, solution2, target);
        getMoney(solutions, solution3, target);
        getMoney(solutions, solution4, target);
      }
    }
  
    
    public static List<String> getParanExpression(List<String> li, String exp, boolean result){
      // at each step, can either add next element, or a left bracket, or a right bracket(can't exceed right brackets).
      // can keep going whilst there are elements to add
      // can we use the fact that either we need brackets
      li.add("1^((0|0)|1)");
      li.add("1^(0|(0|1))");
      
      // assume that adding() is pointless.
      return li;
    } 
  
  
}
