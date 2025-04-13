import java.util.*;

public class BellmanFordWithParent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        // Arrays to hold edge information
        int[] src = new int[E];
        int[] dest = new int[E];
        int[] weight = new int[E];

        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            src[i] = sc.nextInt();
            dest[i] = sc.nextInt();
            weight[i] = sc.nextInt();
        }

        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();

        int[] dist = new int[V + 1];
        int[] parent = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[source] = 0;

        for (int i = 1; i < V; i++) {
            boolean updated = false;
            System.out.println("\nIteration " + i + ":");
            System.out.println("+---------+---------+---------+");
            System.out.println("| Vertex  |  Dist   | Parent  |");
            System.out.println("+---------+---------+---------+");

            for (int j = 0; j < E; j++) {
                if (dist[src[j]] != Integer.MAX_VALUE && dist[src[j]] + weight[j] < dist[dest[j]]) {
                    dist[dest[j]] = dist[src[j]] + weight[j];
                    parent[dest[j]] = src[j];
                    updated = true;
                }
            }

            for (int v = 1; v <= V; v++) {
                String d = (dist[v] == Integer.MAX_VALUE ? "INF" : String.valueOf(dist[v]));
                String p = (parent[v] == -1 ? "-" : String.valueOf(parent[v]));
                System.out.printf("|    %d    |  %4s   |   %3s    |\n", v, d, p);
            }
            System.out.println("+---------+---------+---------+");

            if (!updated) break;
        }

        // Check for negative cycle
        for (int i = 0; i < E; i++) {
            if (dist[src[i]] != Integer.MAX_VALUE && dist[src[i]] + weight[i] < dist[dest[i]]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Final Output
        System.out.println("\nFinal Shortest Distances and Parents from Source:");
        for (int i = 1; i <= V; i++) {
            if (i != source) {
                System.out.print(source + " -> " + i + " : distance = " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
                if (dist[i] != Integer.MAX_VALUE) {
                    System.out.print(", path = ");
                    printPath(i, parent);
                }
                System.out.println();
            }
        }

        sc.close();
    }

    static void printPath(int v, int[] parent) {
        if (parent[v] == -1) {
            System.out.print(v);
            return;
        }
        printPath(parent[v], parent);
        System.out.print(" -> " + v);
    }
}
