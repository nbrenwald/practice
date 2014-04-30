package nbrenwald;

/* Solutions to Chapter 1, Arrays and Strings */
public class ArraysAndStrings {

  /* Exercise 1-3 */

  public static boolean isPermutation(String a, String b) {
    if (a != null && b != null && (a.length() == b.length())) {
      // One option, count chars in each one - perhaps in a hash map.
      // Order the chars in both string, then walk allong checking. Order n log n
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
}
