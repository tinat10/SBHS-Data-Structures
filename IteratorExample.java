import java.util.*;

public class IteratorExample {
  public static void main(String[] args) {

    TreeSet<String> treeSet = new TreeSet<String>();
    System.out.println("Original Set: ");
    treeSet.add("Marie");
    treeSet.add("Anne");
    treeSet.add("Jacqueline");
    treeSet.add("Louisa");
    treeSet.add("Stephanie");

    for (String s: treeSet)
		System.out.println(" + " + s);

	Iterator<String> iterator = treeSet.iterator();
	while (iterator.hasNext()) {
		if (iterator.next().length() > 5)
			iterator.remove();
	}

	System.out.println("\nModified Set: ");

	for (String s: treeSet)
		System.out.println(" + " + s);






  }
}

//Haver's Example
/*Set<String> hashSet = new HashSet<String>();
    hashSet.add("apple");
    hashSet.add("banana");
    hashSet.add("cherry");
    hashSet.add("blueberry");

    System.out.println("Any object in the Java collections framework");
    System.out.println("will have an Iterator object associated with it");
    System.out.println("This Iterator is used implicitly in a for-each loop");
    System.out.println("OR can be created explicitly");
    System.out.println("\nHere is an example of both with the hashSet:");

    for (String s : hashSet)
      System.out.println(s);

    System.out.println("\nExplicit Iterator version:");
    Iterator<String> hashIt = hashSet.iterator();
    while(hashIt.hasNext())
      System.out.println(hashIt.next());


    System.out.println("\nUsing the Iterator lext you have more control");
    System.out.println("\nAnd actually remove things, for example:");
    hashIt = hashSet.iterator(); // get again to reset
    while(hashIt.hasNext()){
      if (hashIt.next().charAt(0) == 'b')  // remove if starts with b
        hashIt.remove();   // removing from iterator removes from HashSet
    }


    System.out.println("\nAfter removal");
    for (String s : hashSet)
      System.out.println(s);
    System.out.println("New size = "+hashSet.size());

    /* YOUR TASK
    Create a TreeSet<String> and add 5 Strings of varying length.  Print them
    out using a for-each loop.   Then create an Iterator and selectively
    remove one or more elements based on length (e.g. remove the ones
    that are less than 6 characters long).  Finally use another for-each
    loop to print the modified TreeSet

    */