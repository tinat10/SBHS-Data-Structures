import java.util.*;
import java.io.*;

public class DijkstraHelper {

	private WeightedGraph g;
	private HashSet<Vertex> visited;
	private HashMap<Vertex, Vertex> predecessors;
	private HashMap<Vertex, Integer> distance;
	private PriorityQueue<VertexDistance> queue;

	public DijkstraHelper(WeightedGraph graph) {
		g = graph;
	}


	public String getShortestPath(Vertex target)
	{

		String path = "";
		Vertex temp = target;

System.out.println(predecessors);
System.out.println(distance);
		while (predecessors.get(temp) != (null)) {
			path+=  " -> " + distance.get(temp)+ " -> " + temp;
			temp = predecessors.get(temp);
		}

		// After calling Create paths.  Use the predecessor map in conjunction with
		// the distance map to construct a path with distances from the
		// source vertex to the target.

		return path.substring(0,path.length()); // this is a placeholder so the file will compile
    }

	public void createPaths(Vertex source) {
		visited = new HashSet<Vertex>();
		predecessors = new HashMap<Vertex, Vertex>();
		distance = new HashMap<Vertex, Integer>();
		queue = new PriorityQueue<VertexDistance>();
System.out.println(g.getVertices());
		for (Vertex v: g.getVertices())
			distance.put(v, Integer.MAX_VALUE);
		distance.put(source, 0);

		predecessors.put(source, null);

		queue.add(new VertexDistance(source, 0));
		while (!queue.isEmpty()) { // or (queue.size() > 0)

			VertexDistance vd = queue.poll();
			Vertex v = vd.v;
			//Vertex v = queue.poll().v; //referencing VertexDistance vertex

			if (visited.contains(v))
				continue; //breaks me out of this iteration NOT loop

			for (Vertex neighbor: g.getNeighbors(v)/*all neighbors of v*/) {
				if (visited.contains(neighbor))
					continue;

				int altDist = distance.get(v) + g.getDistance(v, neighbor);
				if (distance.get(neighbor) > altDist) { //*altDist is better than that in the table(hashMap) */ {

					//update distance and predecessor in table(hashMap)
					distance.put(neighbor, altDist);
					predecessors.put(neighbor, v);

					//add vertexDistnce to queue
					queue.add(new VertexDistance(v, altDist));

				}
			}
			visited.add(v);
		}
	}

	private class VertexDistance implements Comparable<VertexDistance> {
		private Vertex v;
		private int d;

		VertexDistance(Vertex vert, int dist) { // the constructor
			v = vert;
			d = dist;
		}

		public int compareTo(VertexDistance other) {
			return this.d - other.d;
		}
	}

}