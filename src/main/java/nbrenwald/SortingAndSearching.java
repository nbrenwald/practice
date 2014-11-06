package nbrenwald;

import java.util.Arrays;
import java.util.Comparator;

public class SortingAndSearching {

  public static void mergeSortedArraysSlow(int[] a, int[] b){
    // a and b are sorted
    // a contains enough space to hold b.
    // running time O(n^2) as we have to keep shifting
    int i =0;
    int j=0;
    while(j<b.length){
      if(a[i]>b[j]){
        leftShift(a,i,1);
        a[i] = b[j];
        j++;
      }
      i++;
    }
  }
  
  public static void mergeSortedArraysFast(int[] a, int[] b){
    // running time linear O(n) in terms of size of a.
    // first shift a by b.length so we can fit in b.
    leftShift(a,0,b.length);
    int i =b.length;
    int j=0;
    int k =0;
    while(j<b.length){
      if(i<a.length && a[i] <= b[j]){
        a[k] = a[i];
        i++;
      }
      else{
        a[k] = b[j];
        j++;
      } 
      k++;
    }
  }

  private static void leftShift(int[] a, int i, int places) {
    // given array a, shifts all elements left one place from index i onwards.
    for(int j=a.length-1;j>=i+places;j--){
      a[j] = a[j-places];
    }
  }
  
  public static void sortStrings(String[] unsorted){
    AnagramComparator comp = new AnagramComparator();
    Arrays.sort(unsorted, comp);
  }
  
  static class AnagramComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
      char[] ca1 = o1.toCharArray();
      char[] ca2 = o2.toCharArray();
      Arrays.sort(ca1);
      Arrays.sort(ca2);
      String s1 = new String(ca1);
      String s2 = new String(ca2);
      return s1.compareTo(s2);
    }

    
  }
  
  
  public static int findElement(int[] a, int n){
    // find n in a, ideally in O(log n)
    // assume a was originally ordered, but rotated right i places
    // try and design a modified binary search
    
    int low =0;
    int high=a.length;
    int mid = high/2;
    
    while(!(low>high)){
      mid = (high-low) /2;
      if(a[mid] == n)return mid;
      if(a[mid]<n && a[high]>n){
        low = mid+1;
        
      }
      
    }
    
    return 0;
    
  }
}
