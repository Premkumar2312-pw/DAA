import java.util.*;

public class HamiltonianCycle {
    static int hcStep = 1;
    static int[] path;
    static int[][] graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take the number of vertices as input
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        // Initialize the path array and graph
        path = new int[V];
        graph = new int[V][V];

        // Take the adjacency matrix as input
        System.out.println("Enter the adjacency matrix (0 or 1 values): ");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        // Fill the path array with -1
        Arrays.fill(path, -1);
        path[0] = 0;  // Start with the first vertex
        System.out.println("\nFinding Hamiltonian Cycle...");

        // Call the function to find the Hamiltonian Cycle
        if (!hamCycleUtil(1, V)) {
            System.out.println("No Hamiltonian Cycle found");
        } else {
            printCycle(V);
        }

        scanner.close();
    }

    static boolean hamCycleUtil(int pos, int V) {
        if (pos == V) {
            return (graph[path[pos - 1]][path[0]] == 1); // Check if last vertex is connected to the first
        }

        for (int v = 1; v < V; v++) {
            if (isSafe(v, pos, V)) {
                path[pos] = v;
                System.out.println("Step " + hcStep++ + ": Placed vertex " + v + " at position " + pos);
                if (hamCycleUtil(pos + 1, V)) return true; // Recur for the next position
                path[pos] = -1;  // Backtrack
                System.out.println("Step " + hcStep++ + ": Backtracked from vertex " + v);
            }
        }
        return false;
    }

    static boolean isSafe(int v, int pos, int V) {
        if (graph[path[pos - 1]][v] == 0) return false; // Check if the vertex is adjacent to the last vertex
        for (int i = 0; i < pos; i++) if (path[i] == v) return false; // Check if the vertex is already in the path
        return true;
    }

    static void printCycle(int V) {
        System.out.print("Hamiltonian Cycle Found: ");
        for (int i = 0; i < V; i++) System.out.print(path[i] + " ");
        System.out.println(path[0]);
    }
}
