import java.util.*;

public class MapIteration {
	public static void main(String[]args) {

		Map<String,String> sites = new HashMap<String,String>();
		// enter name/url pairs with put
		sites.put("Facebook", "facebook.com");
		sites.put("Google", "google.com");
		sites.put("Apple", "apple.com");
		sites.put("Twitter", "twitter.com");

		// Here is what happens if I print
		System.out.println(sites);

		// Iterate using keySet()  [not as efficient as entry set]
		for (String key: sites.keySet())
		  System.out.println("Key = " + key + ", Value = " + sites.get(key));


		// Create an Iterator using entrySet() method [more efficient]
		// Note the elements  a set of type Map.Entry
		Iterator<Map.Entry<String, String>> itr = sites.entrySet().iterator();

		while(itr.hasNext())
		{
		    Map.Entry<String, String> entry = itr.next();
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}

		// With an iterator you can change/remove the values selectively
		Iterator<Map.Entry<String, String>> itr2 = sites.entrySet().iterator();

		while(itr2.hasNext())
		{
		    Map.Entry<String, String> entry = itr2.next();
		    if (entry.getValue().contains("a"))
			entry.setValue(entry.getValue().replace(".com",".org"));
		    if (entry.getValue().contains("og"))
				itr2.remove();
		}
		System.out.println(sites);


		// Finally, this code implicitly uses Set Iterator
		Set<Map.Entry<String, String>> siteSet = sites.entrySet();
		  for (Map.Entry<String, String> entry : siteSet)
		     System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	}
}