import java.util.Scanner;

public class MaxBipartiteMatching {
    static int[][] bpGraph = new int[100][100]; // adjacency matrix
    static boolean[] seen = new boolean[100];
    static int[] match = new int[100];
    static int n, m; // n = left side, m = right side

    // Helper function to print the augmenting path
    static void printAugmentingPath(int u) {
        System.out.print("Augmenting Path: " + u);
        int v = match[u];
        while (v != -1) {
            System.out.print(" -> " + v);
            u = v;
            v = match[u];
        }
        System.out.println();
    }

    // DFS for finding the augmenting path
    static boolean bpm(int u) {
        for (int v = 0; v < m; v++) {
            if (bpGraph[u][v] == 1 && !seen[v]) {
                seen[v] = true;

                if (match[v] < 0 || bpm(match[v])) {
                    match[v] = u;
                    printAugmentingPath(v); // Print augmenting path
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes in Left and Right set: ");
        n = sc.nextInt(); // Left
        m = sc.nextInt(); // Right

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter edges (u v) [u in left (0 to n-1), v in right (0 to m-1)]:");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            bpGraph[u][v] = 1;
        }

        for (int i = 0; i < m; i++) match[i] = -1;

        int result = 0;
        for (int u = 0; u < n; u++) {
            for (int i = 0; i < m; i++) seen[i] = false;

            if (bpm(u)) result++;
        }

        System.out.println("Maximum Bipartite Matching: " + result);
    }
}
