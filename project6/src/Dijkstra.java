import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Vertex implements Comparable<Vertex> {
	public static Vertex[] split1;
	public final String name;
	public Edge[] adjancies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public Vertex(String argsName) {
		name = argsName;
	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

}

class Edge {
	public String target;
	public double weight;

	public Edge(String destVertex, double weight) {
		this.target = destVertex;
		this.weight = weight;

	}
}

public class Dijkstra {

	public void readInputFile(String file_name) {
		try {
			FileReader file = new FileReader(file_name);
			BufferedReader fr = new BufferedReader(file);

			try {
				String text = null;
				String srcVertex = null;
				String destVertex = null;
				double weight = 0;
				while ((text = fr.readLine()) != null) {
					String[] split = text.split("  ");
					srcVertex = split[0];
					Vertex v = new Vertex(srcVertex);
					System.out.print("Source: " + srcVertex);
					for (int i = 1; i < split.length; i++) {
						String[] split1 = split[i].split(" ");
						destVertex = split1[0];
						weight = Double.parseDouble(split1[1]);

						System.out.print(" Destination: " + destVertex);
						System.out.print(" Weight: " + weight);
					}
					v.adjancies = new Edge[] { new Edge(destVertex, weight) };
					for (Edge e : v.adjancies) {
						System.out.println();
						System.out.println(e.target);
						System.out.println(e.weight);
					}
				}

			} finally {
				fr.close();
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("File Not Found " + file_name);
		} catch (IOException e) {
			System.out.println("Unable to read file " + file_name);
		}
	}

	public static void main(String[] args) {

		String file_name = "airports.txt";

		Dijkstra dj = new Dijkstra();
		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.print("Enter departure airport: "); String dept =
		 * sc.next(); System.out.print("Enter arrival airport: "); String arvl =
		 * sc.next(); sc.close();
		 */
		dj.readInputFile(file_name);

	}
}
