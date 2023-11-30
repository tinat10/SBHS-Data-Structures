public class Edge { //references Vertex and WeightedGraph Classes

	private Vertex v1, v2;
	private int distance;

	public Edge(Vertex v1, Vertex v2, int d) {
		this.v1 = v1;
		this.v2 = v2;
		distance = d;
	}

	public Vertex getV1() {
		return v1;
	}

	public Vertex getV2() {
		return v2;
	}

	public int getDistance() {
		return distance;
	}

	public boolean equals(Edge e) {
		int count = 0;
		if (e.getV1().equals(v1) || e.getV1().equals(v2))
			count++;
		if (e.getV2().equals(v1) || e.getV2().equals(v2))
			count++;

		return count==2;
	}

	public String toString() {
		return v1.getName() + " -> " + v2.getName() + " has a distance of " + distance;
	}

}