import java.util.*;

class KruskalMST {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static int findParent(int[] parent, int v) {
        if (parent[v] == v) return v;
        return parent[v] = findParent(parent, parent[v]);
    }

    static void kruskal(int V, List<Edge> edges) {
        edges.sort(Comparator.comparingInt(e -> e.weight));

        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        int minCost = 0;
        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> cycleEdges = new ArrayList<>();

        System.out.printf("%-6s%-10s%-15s%-20s%-15s\n", "Step", "Edge", "Connected Components", "Edges Considered", "Cycle Detected");
        System.out.println("--------------------------------------------------------------");

        int step = 1;

        for (Edge e : edges) {
            int srcParent = findParent(parent, e.src);
            int destParent = findParent(parent, e.dest);

            System.out.printf("%-6d%-10s", step, e.src + " - " + e.dest);

            if (srcParent != destParent) {
                mstEdges.add(e);
                minCost += e.weight;
                parent[srcParent] = destParent;
                System.out.printf("%-15s%-20s%-15s\n", srcParent + " - " + destParent, "", "");
            } else {
                cycleEdges.add(e);
                System.out.printf("%-15s%-20s%-15s\n", srcParent + " - " + destParent, "Edge Ignored", "Cycle Detected");
            }

            step++;
        }

        System.out.println("\nMinimum Cost of MST: " + minCost);
        System.out.println("\nFinal MST Edges:");
        for (Edge e : mstEdges) {
            System.out.println("Edge: " + e.src + " - " + e.dest + " Weight: " + e.weight);
        }

        // Printing edges that form cycles
        if (!cycleEdges.isEmpty()) {
            System.out.println("\nEdges that form cycles:");
            for (Edge e : cycleEdges) {
                System.out.println("Edge: " + e.src + " - " + e.dest + " Weight: " + e.weight);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices and edges: ");
        int V = sc.nextInt();
        int E = sc.nextInt();

        List<Edge> edges = new ArrayList<>();
        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            edges.add(new Edge(src, dest, weight));
        }

        kruskal(V, edges);
    }
}
