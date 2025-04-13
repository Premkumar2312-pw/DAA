import java.util.Scanner;

public class MaxFlowMinCutSimple {
    static boolean dfs(int[][] rGraph, int u, int t, int[] parent, boolean[] visited) {
        visited[u] = true;
        if (u == t) return true;
        for (int v = 0; v < rGraph.length; v++) {
            if (!visited[v] && rGraph[u][v] > 0) {
                parent[v] = u;
                if (dfs(rGraph, v, t, parent, visited)) return true;
            }
        }
        return false;
    }

    static int fordFulkerson(int[][] graph, int s, int t) {
        int n = graph.length;
        int[][] rGraph = new int[n][n];
        for (int i = 0; i < n; i++)
            System.arraycopy(graph[i], 0, rGraph[i], 0, n);

        int[] parent = new int[n];
        int maxFlow = 0;

        while (true) {
            boolean[] visited = new boolean[n];
            if (!dfs(rGraph, s, t, parent, visited)) break;

            int flow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                flow = Math.min(flow, rGraph[u][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                rGraph[u][v] -= flow;
                rGraph[v][u] += flow;
            }

            maxFlow += flow;

            System.out.print("Augmenting Path: ");
            for (int v = t; v != s; v = parent[v]) System.out.print(v + " <- ");
            System.out.println(s + " (Flow added: " + flow + ")");
        }

        // Find min-cut edges
        boolean[] visited = new boolean[n];
        dfs(rGraph, s, t, parent, visited);
        int minCutCapacity = 0;

        System.out.println("\nMin Cut Edges:");
        for (int u = 0; u < n; u++) {
            if (visited[u]) {
                for (int v = 0; v < n; v++) {
                    if (!visited[v] && graph[u][v] > 0) {
                        System.out.println(u + " -> " + v + " (Capacity: " + graph[u][v] + ")");
                        minCutCapacity += graph[u][v];
                    }
                }
            }
        }

        System.out.println("Total Min-Cut Capacity = " + minCutCapacity);
        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        int[][] graph = new int[n][n];

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        System.out.println("Enter edges as: u v capacity");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cap = sc.nextInt();
            graph[u][v] = cap;
        }

        System.out.print("Enter source: ");
        int s = sc.nextInt();
        System.out.print("Enter sink: ");
        int t = sc.nextInt();

        int maxFlow = fordFulkerson(graph, s, t);
        System.out.println("\nMaximum Flow = " + maxFlow);
    }
}
