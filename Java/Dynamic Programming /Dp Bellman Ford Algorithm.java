import java.util.*;

class BellmanFord { 
     static class Edge { 
          int src, dest, weight;

  Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

static void bellmanFord(List<Edge> edges, int V, int src) {
    int[] dist = new int[V + 1]; // 1-based index
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;

    for (int i = 1; i < V; i++) {
        System.out.println("\nIteration " + i + ":");
        System.out.println("+---------+---------+");
        System.out.println("| Vertex  |  Dist   |");
        System.out.println("+---------+---------+");

        boolean updated = false;
        for (Edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                dist[edge.dest] = dist[edge.src] + edge.weight;
                updated = true;
            }
        }

        for (int j = 1; j <= V; j++) {
            System.out.printf("|    %d    |  %s  |\n", j, (dist[j] == Integer.MAX_VALUE ? "INF" : dist[j]));
        }
        System.out.println("+---------+---------+");

        if (!updated) break;
    }

    for (Edge edge : edges) {
        if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
            System.out.println("Graph contains a negative weight cycle");
            return;
        }
    }

    System.out.println("\nFinal Shortest Distances from Source:");
    for (int i = 1; i <= V; i++) {
        if (i != src) {
            System.out.println(src + " -> " + i + " : shortest distance = " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter number of vertices: ");
    int V = sc.nextInt();

    System.out.print("Enter number of edges: ");
    int E = sc.nextInt();

    List<Edge> edges = new ArrayList<>();

    System.out.println("Enter edges (src dest weight):");
    for (int i = 0; i < E; i++) {
        int src = sc.nextInt();
        int dest = sc.nextInt();
        int weight = sc.nextInt();
        edges.add(new Edge(src, dest, weight));
    }

    System.out.print("Enter source vertex: ");
    int src = sc.nextInt();

    bellmanFord(edges, V, src);
    sc.close();
}

}

