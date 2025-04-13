import java.util.Scanner;

public class GraphDFS2 {
    static int[][] adj = new int[100][100];
    static int[][] revAdj = new int[100][100];
    static int[] visited = new int[100];
    static int[] discovery = new int[100];
    static int[] finish = new int[100];
    static int[] inDegree = new int[100];
    static int[] outDegree = new int[100];
    static int time = 0, v;

    public static void dfs(int u) {
        visited[u] = 1;
        discovery[u] = ++time;
        for (int i = 0; i < v; i++) {
            if (adj[u][i] == 1) {
                if (visited[i] == 0) {
                    System.out.println("Tree Edge: " + u + " -> " + i);
                    dfs(i);
                } else if (finish[i] == 0) {
                    System.out.println("Back Edge: " + u + " -> " + i);
                } else if (discovery[u] < discovery[i]) {
                    System.out.println("Forward Edge: " + u + " -> " + i);
                } else {
                    System.out.println("Cross Edge: " + u + " -> " + i);
                }
            }
        }
        finish[u] = ++time;
    }

    public static boolean isCyclic(int u) {
        visited[u] = 1;
        for (int i = 0; i < v; i++) {
            if (adj[u][i] == 1) {
                if (visited[i] == 1) return true;
                else if (visited[i] == 0 && isCyclic(i)) return true;
            }
        }
        visited[u] = 2;
        return false;
    }

    public static void topologicalSortDFS(int u, int[] stack, int[] idx) {
        visited[u] = 1;
        for (int i = 0; i < v; i++) {
            if (adj[u][i] == 1 && visited[i] == 0)
                topologicalSortDFS(i, stack, idx);
        }
        stack[idx[0]--] = u;
    }

    public static void topologicalSortByIndegree() {
        int[] tempIndegree = new int[100];
        for (int i = 0; i < v; i++) tempIndegree[i] = inDegree[i];

        System.out.print("Topological Order (In-degree): ");
        for (int c = 0; c < v; c++) {
            boolean found = false;
            for (int i = 0; i < v; i++) {
                if (tempIndegree[i] == 0) {
                    System.out.print(i + " ");
                    tempIndegree[i] = -1;
                    for (int j = 0; j < v; j++) {
                        if (adj[i][j] == 1) tempIndegree[j]--;
                    }
                    found = true;
                    break;
                }
            }
            if (!found) break;
        }
        System.out.println();
    }

    public static void reverseDFS(int u) {
        visited[u] = 1;
        for (int i = 0; i < v; i++) {
            if (revAdj[u][i] == 1 && visited[i] == 0)
                reverseDFS(i);
        }
    }

    public static void findSCCs() {
        int[] stack = new int[100];
        int[] idx = new int[1];
        idx[0] = v - 1;

        for (int i = 0; i < v; i++) visited[i] = 0;

        for (int i = 0; i < v; i++)
            if (visited[i] == 0) topologicalSortDFS(i, stack, idx);

        for (int i = 0; i < v; i++) visited[i] = 0;

        int count = 0;
        for (int i = 0; i < v; i++) {
            int node = stack[i];
            if (visited[node] == 0) {
                reverseDFS(node);
                count++;
            }
        }

        System.out.println("Number of Strongly Connected Components: " + count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices and edges: ");
        v = sc.nextInt();
        int e = sc.nextInt();

        System.out.println("Enter directed edges (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int vv = sc.nextInt();
            adj[u][vv] = 1;
            revAdj[vv][u] = 1;
            inDegree[vv]++;
            outDegree[u]++;
        }

        // Print In-degree & Out-degree
        System.out.println("\nVertex\tIn-Degree\tOut-Degree");
        for (int i = 0; i < v; i++) {
            System.out.println(i + "\t" + inDegree[i] + "\t\t" + outDegree[i]);
        }

        // DFS + Edge Classification
        for (int i = 0; i < v; i++) visited[i] = 0;
        time = 0;
        System.out.println("\n--- DFS + Edge Classification ---");
        for (int i = 0; i < v; i++)
            if (visited[i] == 0) dfs(i);

        // Cycle Detection
        for (int i = 0; i < v; i++) visited[i] = 0;
        boolean hasCycle = false;
        for (int i = 0; i < v; i++) {
            if (visited[i] == 0 && isCyclic(i)) {
                hasCycle = true;
                break;
            }
        }

        if (hasCycle) {
            System.out.println("\nCycle Detected! Topological Sort not possible.");
        } else {
            // Topo Sort - DFS
            for (int i = 0; i < v; i++) visited[i] = 0;
            int[] stack = new int[100];
            int[] idx = new int[1];
            idx[0] = v - 1;
            for (int i = 0; i < v; i++)
                if (visited[i] == 0) topologicalSortDFS(i, stack, idx);

            System.out.print("\nTopological Order (DFS): ");
            for (int i = 0; i < v; i++) System.out.print(stack[i] + " ");
            System.out.println();

            // Topo Sort - In-degree
            topologicalSortByIndegree();
        }

        // SCCs
        findSCCs();
    }
}
