public class Vertex { //references Edge and WeightedGraph Classes

	private final String name;
	private String description;

	public Vertex(String name) {
		this.name = name;
	}

	public Vertex(String name, String description){
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		description = desc;
	}

	public boolean equals(String name) {
		return this.name.equals(name);
	}

	public String toString() {
		return name;// + ": " + description;
	}

}