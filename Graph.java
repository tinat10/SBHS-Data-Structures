import java.util.*;
import java.io.*;

public class Graph {

	private HashMap<String, HashSet<String>> g; // undirected (non directional)

	public Graph() {
		g = new HashMap<String, HashSet<String>>();
	}

	public void add(String v1, String v2) {
		if (!g.containsKey(v1)) {
			g.put(v1, new HashSet<String>());
		}
		if (!g.containsKey(v2)) {
			g.put(v2, new HashSet<String>());
		}
		g.get(v1).add(v2);
		g.get(v2).add(v1);

		// Add edges here? :)
	}

	public boolean dfs (String v1, String v2) { //depth first search

		return dfs(v1, v2, new HashSet<String>());

	}

	// --> return true if path from v1 to v2 exists
	private boolean dfs (String v1, String v2, HashSet<String> visited) { //depth first search

		visited.add(v1);

		if (v1.equals(v2))
			return true;

		HashSet<String> neighbors = (HashSet)getNeighbors(v1);
		for (String v: neighbors) {
			if (!visited.contains(v) && dfs(v, v2, visited))
				return true;
		}

		return false;

	}

	public String dfsPath(String v1, String v2) {

		Stack<String> stack = new Stack();
		Stack<String> visited = new Stack();

		stack.add(v1);
		while (!stack.isEmpty()) {
			String v = stack.pop();
			visited.add(v);

			if (v.equals(v2)) {
				visited.add(v2);
				return buildPath(visited);
			}

			HashSet<String> hs = (HashSet)getNeighbors(v);
			Iterator<String> it = hs.iterator();
			while(it.hasNext()) {
				String string = it.next();

				if (!visited.contains(string)) {
					stack.add(string);
				}
			}
		}

		return "No Connection";

	}


	private String buildPath(Stack<String> path) { // path is visited

		String st = "";

		while (!path.isEmpty()) {
			String v = path.pop();
			st = v + " -> " + st ;
			while (!path.isEmpty() && !getNeighbors(v).contains(path.peek()))
				path.pop();
		}

		return st.substring(0, st.length()-3);
	}

	public String bfs(String v1, String v2) {

		Queue<String> q = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		HashMap<String, String> map = new HashMap<String, String>();

		q.add(v1);
		while (!q.isEmpty()) {

			String v = q.poll();
			visited.add(v);

			if (v.equals(v2))
				return bfsPathBuilder(v1, v2, map);

			HashSet<String> hs = (HashSet)getNeighbors(v);
			Iterator<String> it = hs.iterator();
			while(it.hasNext()) {
				String string = it.next();
				if (!map.containsKey(string))
					map.put(string, v);
				if (!visited.contains(string))
					q.add(string);
			}

		}

		return "No Connection";

	}

	private String bfsPathBuilder(String v1, String v2, HashMap<String, String> map) {

		String output = v2;
		boolean end = false;

		while (!end) {
			output = map.get(v2) + " -> " + output;
			if (map.get(v2).equals(v1))
				end = true;
			v2 = map.get(v2);
		}

		return output;

	}

	public Set<String> getVertices() {
		return g.keySet();
	}

	public int getNumVertices() {
		return g.size();
	}

	public Set<String> getNeighbors(String vertex) {
		return g.get(vertex);
	}

	public static void main(String[]args) {

		TreeMap<String, String> countryCodes = new TreeMap<String, String>();
		Graph graph = new Graph();

		String [] split = new String[4];

		try {
			File name = new File("geoData.csv");
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output = "";

			while((text=input.readLine())!= null) {
				output+=text +"\n";
			}

			split = output.split("\n");

		} catch (Exception io) {
			io.printStackTrace();
		}

		for (int i = 0; i<split.length; i++) {
			String [] connections = split[i].split(",");
			if (connections.length == 4)
				graph.add(connections[1], connections[3]);

		}

		System.out.println("DFS PATH ==> " + graph.dfsPath("Germany", "Thailand"));
		System.out.println("\nBFS PATH ==> " + graph.bfs("Germany", "Thailand"));
		System.out.println("\nBFS PATH ==> " + graph.bfs("Peru", "Armenia"));






	}



}