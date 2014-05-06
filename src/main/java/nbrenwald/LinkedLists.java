package nbrenwald;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* Solutions to Chapter 2, LinkedLists */
public class LinkedLists{
/* Pre-req, write own singly and doubly liked list*/

  /* Exercise 1-1 */

  public static void removeDuplicates(List<Integer> list) {
	// simple answer, if possible use an additional collection, such as hashmap to store counts. potentially a 
	// int array if we know largest int.
	Set<Integer> set = new HashSet<>();
	Iterator<Integer> itList = list.iterator();
	while(itList.hasNext()){
		int value=itList.next();
		if(set.contains(value)){
			itList.remove();
		}
		else{
			set.add(value);
		}
	}
  }


}
