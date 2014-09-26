package nbrenwald;

/* Solutions to Chapter 1, Arrays and Strings */
public class ArraysAndStrings {

  /* Exercise 1-3 */

  public static boolean isPermutation(String a, String b) {
    if (a != null && b != null && (a.length() == b.length())) {
      // One option, count chars in each one - perhaps in a hash map.
      // Order the chars in both string, then walk along checking. Order n log n
      // If chars are ascii, we could do counting sort quite easily with array of 127 elements
      // we could hold an array of 127 chars, we do one loop to count characters for a, one to
      // decrement for b,
      // one to check all are 0.
      // Question is a and A considered the same?
      // Are they Ascii
      int[] asciiArray = new int[127];
      char[] aArray = a.toCharArray();
      char[] bArray = b.toCharArray();
      for (char ca : aArray) {
        asciiArray[ca]++;
      }
      for (char cb : bArray) {
        asciiArray[cb]--;
      }
      for (int i : asciiArray) {
        if (i != 0)
          return false;
      }
      return true;

    }
    return false;


  }

  /* Exercise 1-4 */

  public static String replaceSubstring(String a, String b, int trueLength) {
    char[] aArray = a.toCharArray();
    char[] bArray = b.toCharArray();
    // Told we can assume enough space at the end, so we wont check this at the moment.
    // First idea, we should work backwards from length shifiting characters to true end.
    // so one pointer to end of initial string, one pointer to end of ful string.

    int i = trueLength - 1;
    int j = aArray.length - 1;

    while (i >= 0) {
      if (aArray[i] == ' ') {
        for (int x = bArray.length - 1; x >= 0; x--) {
          aArray[j] = bArray[x];
          j--;
        }
        i--;
      } else {
        aArray[j] = aArray[i];
        i--;
        j--;
      }
    }

    return String.valueOf(aArray);
  }

  /* Exercise 1-5 */
  public static String compress(String uncompressed) {
    // Only upper and lower case ascii characters. need to keep the original ordering.
    if (uncompressed.length() > 2) {// a string with fewer than 3 chars could not be improved
      char[] uncompressedArray = uncompressed.toCharArray();
      StringBuilder sb = new StringBuilder(uncompressedArray.length * 2); // area to test
      char previousChar = uncompressedArray[0];
      int previousCount = 1;

      for (int i = 1; i < uncompressedArray.length; i++) {
        if (uncompressedArray[i] == previousChar) {
          previousCount++;
        } else {
          // do the write
          sb.append(previousChar);
          sb.append(previousCount);
          previousChar = uncompressedArray[i];
          previousCount = 1;
        }
      }
      sb.append(previousChar);
      sb.append(previousCount);
      if (sb.length() < uncompressed.length())
        return sb.toString();
    }
    return uncompressed;
  }

  /* Exercise 1-6 */
  public static int[][] rotateSquareMatrix(int[][] x, int level) {
    if (x.length - (2 * level) > 1) {
      for (int i = level; i < x.length - 1 - level; i++) {
        int tmp = x[level][i];// top row element.
        x[level][i] = x[x.length - 1 - i][level];// ok
        x[x.length - 1 - i][level] = x[x.length - 1 - level][x.length - 1 - i];//
        x[x.length - 1 - level][x.length - 1 - i] = x[i][x.length - 1 - level];
        x[i][x.length - 1 - level] = tmp;
      }
      // recursive step, call rotateSquareMatrix for one level lower
      rotateSquareMatrix(x, level + 1);
    }
    return x;
  }

/* Exercise 1-7 */
// to run call on int[][] x = {{1,1,1,1}{1,0,1,1}{1,1,1,1}{1,1,1,1}{1,1,1,0}};
// setToZeroWhereZero(x,0,0,x[0].length-1)

public static void setToZeroWhereZero(int[][] x, int startRow, int startColumn, int endColumn){
// in recursive case, we will always be attempting to go to the final row
	if(startRow < x.length && startColumn <= endColumn){
	// if this doesn't hold, then we are either already finished the last row, or left and right matrices are already finished.
		for(int m=startRow;m<x.length;m++){
			for(int n=startColumn;n<=endColumn;n++){
			if(x[m][n]==0){
				// set all row column 0
				// call on smaller left matrix and smaller right matrix
				for(int i=0;i<x.length;i++){// set column 0
					x[i][n]=0;
				}
				for(int i=0;i<x[0].length;i++){// set row zero
					x[m][i]=0;//duplicate?
				}
				// call on m+1, n-1 and m+1, n+1
				setToZeroWhereZero(x,m+1,startColumn, n-1);
				setToZeroWhereZero(x,m+1,n+1, endColumn);
								
			}
			
		}
		}
	}
}

/* Exercise 1-7 2nd attempt*/
// to run call on int[][] x = {{1,1,1,1}{1,0,1,1}{1,1,1,1}{1,1,1,1}{1,1,1,0}};
// setToZeroWhereZero(x)

public static void setToZeroWhereZero(int[][] x){
// running time O(mn)+O(m)+O(n) = O(mn)
// space O(m)+O(n) almost in place
/* List of mistakes
 * 1 missing forward slash for comment
 * 1 using a point to reference an array x.[0]
 * 2 forgot to use a array accesor - said rowBitmap instead of rowBitmap[m]
 * Total = 4
 */

	if(x.length > 0 && x[0].length > 0){// m and n must be at least 1 row and column
	// we could do a check to make sure we have rectangle array
	// we could just return the array if not rectangular, 
	// or we could work on the smallest length n would need to ask a question
	// to get min loop through all rows and get min length, then use that instead of x[0].length

	int[] rowBitmap = new int[x.length];
	int[] columnBitmap = new int[x[0].length];

	for(int m=0;m<x.length;m++){
			
		for(int n=0;n<x[0].length;n++){
			
			if(x[m][n]==0){
				rowBitmap[m] = 1;
				columnBitmap[n]=1;
				//break;
								
			}//if
			
		}//for loop
	}//for loop
	
	

	// now lets loop through the row setting all to zero, then through the columns.
	for(int m=0; m<rowBitmap.length; m++){
		if(rowBitmap[m]==1){
			for(int i=0; i<x[m].length;i++){// as we are told it is m by n, this could be 0
				x[m][i]=0;
			}
		}
	}

	// now lets loop through the row setting all to zero, then through the columns.
	for(int n=0; n<columnBitmap.length; n++){
		if(columnBitmap[n]==1){
			for(int i=0; i<x.length;i++){
				x[i][n]=0;
			}
		}
	}
	}//if../java/practice/src
}

/* Exercise 1-7 2nd attempt*/
public static boolean isRotation(String s1, String s2){
  // Concatenate S2 to itself. S1 should appear within it.
  // then test 1 by 1, or perhaps using rolling hash?


if(s1.length() != s2.length()) return false;
else if (s1.length()==0) return true;
else {
  char[] s1Array = s1.toCharArray();
  char[] s2Array = (s2+s2).toCharArray();
  
  
  for(int i = 0; i <s1Array.length;i++){
    for(int j = 0; j <s1Array.length; j++){
      if(s1Array[j] != s2Array[i+j]) break;
      if(j == s1Array.length-1) return true;
    }
  }
}
  return false;
  
//  // make a hash of s1
//  double pHash = 0;
//  double sHash = 0;
//  int base = 10;
//  int bigPrime = 7919;
//  
//  for(int i =0; i< s1Array.length; i++){
//    System.out.println(Math.pow(base,(s1Array.length-i-1))*s1Array[i]);
//    System.out.println("length " + (s1Array.length-i-1));
//    System.out.println("char is " + (s1Array.length-i-1));
//    pHash = pHash + (Math.pow(base,(s1Array.length-i-1))*Integer..parseInt(s1Array[i]));
//    sHash += Math.pow(base,(s1Array.length-i-1))*s2Array[i];  
//  }
//  //pHash %= bigPrime;
//  //sHash %= bigPrime;
//  System.out.println("pHash = "+pHash);
//  System.out.println("sHash = "+sHash);
//  
//  if (sHash == pHash) return true;
//  
//  for(int i =1; i <s2Array.length-s1Array.length; i++){
//    
//    //we need to subtract
//    System.out.println("subtract character "+ s2Array[i-1] +" whose numeric value is "+(int)s2Array[i-1]);
//    System.out.println("subtract "+Math.pow(base,(s1Array.length-1))*s2Array[i-1]);
//    
//    sHash = sHash - ((Math.pow(base,(s1Array.length-1))*s2Array[i-1])% bigPrime) ;
//    sHash = (sHash * base + s2Array[i+s1Array.length-1]) % bigPrime;
//    //sHash = (sHash - (Math.pow(base,(s1Array.length-1))*s2Array[i-1]))*base + s2Array[i+s1Array.length-1] % bigPrime;
//    System.out.println("sHash = "+sHash);
//    if (sHash == pHash) return true;
//  }
//}
//return false;
}

// p represents the pattern to search for in string t. Naive a approach would require O(pt) as we would check every char of p for every substring of t
// use Rabin Karp to achieve better results.
public static boolean isSubstring(String pattern, String text){
  
  int base = 10;
  char[] p = pattern.toCharArray();
  char[] t = text.toCharArray();
  double high =Math.pow(base, p.length-1);
  
  double pHash =0 ,tHash = 0;
  // Step 1: make a hash of string p and the first substring of t
  for(int i = 0; i<p.length; i++){
    pHash += Math.pow(base, p.length-1-i)* Character.getNumericValue(p[i]);
    tHash += Math.pow(base, p.length-1-i)* Character.getNumericValue(t[i]);
  }
  System.out.println(pHash);
  System.out.println(tHash);
  
  
  // Step 2: compare the hashes
  if(pHash == tHash) return true;
  
  // Step3: compare all substrings of t
  for (int i = 1; i<= t.length-p.length; i++){
    // update rolling hash tHash by removing one char and adding a new one
    // subtract the leading char
    tHash = tHash - high*Character.getNumericValue(t[i-1]);
    System.out.println(tHash);
    // then shift a digit
    tHash = tHash * base;
    System.out.println(tHash);
    //then add new char
    tHash = tHash + Character.getNumericValue(t[i+p.length-1]);
    System.out.println(tHash);
    // then compare. if we have a match, we then need to do a char by char comparison
    if(pHash == tHash) return true;
    
  }
  
  return false;
 
  
}


}
