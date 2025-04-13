import java.util.*;

public class Dijkstra {

    static final int INF = Integer.MAX_VALUE;

    public static void printTable(int[][] iterParents, int[][] iterDistances, int V) {
        final int col1 = 10, col2 = 8, col3 = 14, col4 = 7;
        System.out.printf("%-10s", "Vertex");
        for (int i = 0; i < V; i++)
            System.out.printf("%-" + col3 + "s", ("   It-" + (i + 1)));
        System.out.println();
        
        System.out.printf("%-10s", "    ");
        for (int i = 0; i < V; i++)
            System.out.printf("%-" + col3 + "s", "Dist Parent");
        System.out.printf("%-" + col2 + "s", "Shortest path from source");
        System.out.println();
        
        System.out.println("-".repeat(col1 + col2 + col2 + (col3 * V)));
        
        for (int i = 0; i < V; i++) {
            System.out.printf("%-10d", i);
            for (int j = 0; j < V; j++) {
                System.out.printf("%-" + col4 + "s", (iterDistances[i][j] == INF ? "inf" : iterDistances[i][j] + ""));
                System.out.printf("%-" + col4 + "s", (iterParents[i][j] == -1 ? "-" : iterParents[i][j] + ""));
            }
            System.out.printf("%-" + col2 + "s", (iterDistances[i][V - 1] == INF ? "inf" : iterDistances[i][V - 1] + ""));
            System.out.println();
        }
        System.out.println();
    }

    public static void dijkstra(int[][] adj, int src, int V) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        int[][] iterDistances = new int[V][V];
        int[][] iterParents = new int[V][V];

        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        
        dist[src] = 0;
        
        // Priority Queue for Dijkstra algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] { 0, src });

        for (int count = 0; count < V; count++) {
            if (pq.isEmpty()) break;
            
            int u = pq.poll()[1];

            for (int v = 0; v < V; v++) {
                if (adj[u][v] != 0 && dist[u] + adj[u][v] < dist[v]) {
                    dist[v] = dist[u] + adj[u][v];
                    parent[v] = u;
                    pq.add(new int[] { dist[v], v });
                }
            }

            for (int i = 0; i < V; i++) {
                iterDistances[i][count] = dist[i];
                iterParents[i][count] = parent[i];
            }
        }

        printTable(iterParents, iterDistances, V);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        
        int[][] adj = new int[V][V];
        System.out.println("Enter the edges (start vertex, end vertex, weight):");
        for (int i = 0; i < E; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            adj[u][v] = weight;
            adj[v][u] = weight;
        }
        
        System.out.print("Enter the source vertex: ");
        int src = sc.nextInt();
        
        dijkstra(adj, src, V);
    }
}
