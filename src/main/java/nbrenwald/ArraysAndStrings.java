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
	// if this doesnt hold, then we are either already finished the last row, or left and right matrices are already finished.
		for(int m=startRow;m<x.length;m++){
			for(int n=startColumn;n<=endColumn;n++){
			if(x[m][n]==0){
				// set all row column 0
				// call on smaller left matrix and snaller right matrix
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
}
