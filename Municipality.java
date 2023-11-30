public class Municipality {

	String name, county, type;

	public Municipality(String name, String county, String type) {
		this.name = name;
		this.county = county;
		this.type = type;
	}

	public Municipality(String[] info) {
		name = info[0];
		county = info[1];
		type = info[2];
	}

	public Municipality(String data) {
		String [] info = data.split(",");
		name = info[0];
		county = info[1];
		type = info[2];
	}

	public String getName() {
		return name;
	}

	public String getCounty() {
		return county;
	}

	public String getType() {
		return type;
	}

	public String toString() {
		return "NAME: " + name + " | COUNTY: " + county + " | TYPE: " + type;
	}

}