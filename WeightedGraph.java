import java.util.*;
import java.io.*;

public class WeightedGraph {

	Map<Vertex, HashSet<Edge>> map; //private?
	DijkstraHelper dh;

	public WeightedGraph() {
		map = new HashMap<Vertex, HashSet<Edge>>();
	}

	public String findPath(Vertex v1, Vertex v2) {
		dh = new DijkstraHelper(this);
		dh.createPaths(v1);
		return dh.getShortestPath(v2);
	}

	public void addEdge(Vertex v1, Vertex v2, int d) {
		if (!map.containsKey(v1)) {
			HashSet<Edge> hs = new HashSet<Edge>();
			hs.add(new Edge(v1, v2, d));
			map.put(v1, hs);
		}
		else {
			HashSet<Edge> hs = map.get(v1);
			hs.add(new Edge(v1, v2, d));
		}

		if (!map.containsKey(v2)) {
			HashSet<Edge> hs = new HashSet<Edge>();
			hs.add(new Edge(v2, v1, d));
			map.put(v2, hs);
		}
		else {
			HashSet<Edge> hs = map.get(v1);
			hs.add(new Edge(v2, v1, d));
		}
	}

	public void addEdge(Edge edge) {

		Vertex v2 = edge.getV2();
		Vertex v1 = edge.getV1();

		if (!map.containsKey(v1)) {
			HashSet<Edge> hs = new HashSet<Edge>();
			hs.add(edge);
			map.put(v1, hs);
		}
		else {
			HashSet<Edge> hs = map.get(v1);
			hs.add(edge);
		}

		if (!map.containsKey(v2)) {
			HashSet<Edge> hs = new HashSet<Edge>();
			hs.add(edge);
			map.put(v2, hs);
		}
		else {
			HashSet<Edge> hs = map.get(v1);
			hs.add(edge);
		}


	}

	public int getDistance(Vertex v1, Vertex v2) {
		for (Edge e: map.get(v1)) {
			if (e.getV2().equals(v2) || e.getV1().equals(v2))
				return e.getDistance();
		}
		return Integer.MAX_VALUE;
	}

	public Set<Vertex> getNeighbors(Vertex v) {
		Set<Vertex> n = new HashSet<Vertex>();
		if (map.get(v) != null) {
			for (Edge e : map.get(v)) {
				if (e.getV1().equals(v))
					n.add(e.getV2());
				if (e.getV2().equals(v))
					n.add(e.getV1());
			}
		}
		return n;
	}

	public Vertex getVertex(String name) {

		for (Map.Entry<Vertex, HashSet<Edge>> entry: map.entrySet()) {
			if (entry.getKey().getName().equals(name)) {
				return entry.getKey();
			}
		}

		Vertex v = new Vertex(name);
		//map.put(v, new HashSet<Edge>());
		return v; // DO I ADD NEW VERTEX TO THE MAP

	}

	public Set<Vertex> getVertices() {
		return map.keySet();
	}

	public Set<Edge> getEdges() {
	    Set<Edge> edges = new HashSet<Edge>();
	    for (Map.Entry<Vertex, HashSet<Edge>> entry : map.entrySet()) {
	      edges.addAll(entry.getValue());
	    }
	    return edges;
    }


	public static void main(String[]args) {

		WeightedGraph graph = new WeightedGraph();

		String[] split = new String[2];

		try {
			File name = new File("CityDistances.txt");
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text, output = "";

			while ((text=input.readLine()) != null) {
				output+=text + "\n";
			}

			split = output.split("\n");
		} catch (Exception e) { e.printStackTrace(); }

		for (int i = 0; i<split.length; i++) {
			String [] info = split[i].split(",");
			graph.addEdge(graph.getVertex(info[0]), graph.getVertex(info[1]), Integer.parseInt(info[2]));
		}

		Vertex v1 = graph.getVertex("Philadelphia");
		Vertex v2 = graph.getVertex("Montreal");

		System.out.println(graph.findPath(v1, v2));

	}

}