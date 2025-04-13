import java.util.Scanner;

public class PrimMST {
    static final int INF = Integer.MAX_VALUE;

    // Method to find the vertex with the minimum key value
    static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = INF, min_index = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // Method to print the result table and the minimum cost
    static void printTable(int[] parent, int[][] dist, int[] vertex, int V) {
        final int col1 = 15, col2 = 10, col3 = 7;
        System.out.printf("%-" + col1 + "s%-" + col2 + "s", "Active Vertex", "Initial");
        for (int i = 0; i < V; i++) {
            System.out.printf("%-" + col2 + "s", "It-" + (i + 1) + "(" + (char)(vertex[i] + 'a') + ")");
        }
        System.out.printf("%-" + col3 + "s\n", "Parent");
        System.out.println("-".repeat(col1 + col2 + (col2 * V) + col3));
        for (int i = 0; i < V; i++) {
            System.out.printf("%-" + col1 + "s%-" + col2 + "s", (char)(i + 'a'), (i == 0 ? "0" : "inf"));
            for (int j = 0; j < V; j++) {
                System.out.printf("%-" + col2 + "s", (dist[i][j] == INF ? "inf" : dist[i][j] + ""));
            }
            System.out.printf("%-" + col3 + "s\n", (parent[i] == -1 ? '-' : (char)(parent[i] + 'a')));
        }
        int minCost = 0;
        for (int i = 1; i < V; i++) {
            minCost += (parent[i] != -1 ? dist[i][V - 1] : 0);
        }
        System.out.println("\nMinimum Cost: " + minCost);
    }

    // Method to compute Prim's MST and print the result
    static void primMST(int[][] graph, int V) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];
        int[][] dist = new int[V][V];
        int[] vertex = new int[V];

        for (int i = 0; i < V; i++) {
            key[i] = INF;
            parent[i] = -1;
            mstSet[i] = false;
        }
        key[0] = 0;

        for (int count = 0; count < V; count++) {
            int u = minKey(key, mstSet, V);
            if (u == -1) break;

            mstSet[u] = true;
            vertex[count] = u;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }

            for (int i = 0; i < V; i++) {
                dist[i][count] = key[i];
            }
        }

        printTable(parent, dist, vertex, V);

        System.out.println("\nFinal MST:");
        for (int i = 1; i < V; ++i) {
            System.out.println("Edge: (" + (char)(parent[i] + 'a') + "," + (char)(i + 'a') + ") Weight: " + graph[i][parent[i]]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices and edges
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter the edges (start vertex, end vertex, weight):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph[u][v] = weight;
            graph[v][u] = weight;
        }

        primMST(graph, V);
    }
}
