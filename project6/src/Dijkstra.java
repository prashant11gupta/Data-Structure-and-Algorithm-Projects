import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Storage {
	private Map<String, Node> map;

	public Storage() {
		map = new HashMap<String, Node>();
	}

	public Node getNode(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		}
		return null;

	}

	public Node addNode(String name) {
		if (map.containsKey(name)) {
			return map.get(name);

		} else {
			Node n = new Node(name);
			map.put(name, n);
			return n;
		}

	}

	public Collection<Node> getAllNodes() {
		return map.values();
	}

}

class Node implements Comparable<Node> {
	public final String name;
	public List<Edge> adjancies;
	public Integer minDistance = Integer.MAX_VALUE;
	public Node previousNode;
	boolean known;

	public Node(String argsName) {
		name = argsName;
		this.adjancies = new LinkedList<Edge>();
	}

	@Override
	public int compareTo(Node other) {
		return Double.compare(this.minDistance, other.minDistance);

	}
}

class Edge {
	public Node destNode;
	public Integer weight;

	public Edge(Node destNode, Integer weight) {
		this.destNode = destNode;
		this.weight = weight;

	}
}

public class Dijkstra {

	public static void findPaths(Node source, Collection<Node> allNodes) {
		for (Node n : allNodes) {
			n.minDistance = Integer.MAX_VALUE;
			n.previousNode = null;
		}

		source.minDistance = 0;
		PriorityQueue<Node> NodeQueue = new PriorityQueue<Node>();
		NodeQueue.add(source);

		while (!NodeQueue.isEmpty()) {
			Node u = NodeQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjancies) {
				Node v = e.destNode;
				Integer weight = e.weight;
				Integer distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					NodeQueue.remove(v);
					v.minDistance = distanceThroughU;
					v.previousNode = u;
					NodeQueue.add(v);
				}
			}
		}
	}

	public static List<String> getShortestPath(Node destNode) {
		List<String> path = null;
		path = new ArrayList<String>();
		for (Node node = destNode; node != null; node = node.previousNode)
			path.add(node.name);
		Collections.reverse(path);
		return path;
	}

	public static Storage readInputFile(String file_name) throws IOException {
		Storage storage = new Storage();
		BufferedReader bufferedReader = null;
		try {
			FileReader file = new FileReader(file_name);
			bufferedReader = new BufferedReader(file);

			String text = null;
			String srcVertex = null;
			String destVertex = null;
			Integer weight = null;
			Node sourceNode = null;
			Node destNode = null;

			while ((text = bufferedReader.readLine()) != null) {
				String[] split = text.split("  ");
				srcVertex = split[0];
				sourceNode = storage.addNode(srcVertex);

				for (int i = 1; i < split.length; i++) {

					String[] split1 = split[i].split(" ");
					destVertex = split1[0];
					weight = Integer.parseInt(split1[1]);
					destNode = storage.addNode(destVertex);
					Edge e = new Edge(destNode, weight);
					sourceNode.adjancies.add(e);
				}

			}

			return storage;
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found " + file_name);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
		return null;
	}

	public static void main(String[] args) {

		String yesOrNo = "Y";
		String file_name = "airports.txt";
		try {
			Storage storage = readInputFile(file_name);
			try (Scanner sc = new Scanner(System.in)) {
				do {

					System.out.println("---------------------");
					System.out.print("Enter departure airport: ");
					String dept = sc.next();
					System.out.print("Enter arrival airport: ");
					String arvl = sc.next();
					System.out.println("---------------------");
					Node src = storage.getNode(dept);
					Node dest = storage.getNode(arvl);
					if (src == null | dest == null) {
						System.out
								.println("Please enter correct name, Airport entered does not exist");
						continue;
					}
					findPaths(src, storage.getAllNodes());
					System.out.println();
					System.out.println("By Price:");
					System.out.println("  Price:       " + dest.minDistance);

					List<String> path = getShortestPath(dest);
					System.out.println("  Connections: " + (path.size() - 2));
					System.out.print("  Route:       ");
					int i = 1;
					for (String connections : path) {
						System.out.print(connections);
						if (i < path.size()) {
							System.out.print("->");
							i++;
						}
					}
					System.out.println(" Check another route (Y/N)?");
					yesOrNo = sc.next("\\w{1}");

				} while ("Y".equalsIgnoreCase(yesOrNo));
			}
			System.out.println("Thank you very much for using Application!!!");
		} catch (NullPointerException e) {
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
