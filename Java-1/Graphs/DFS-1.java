import java.util.*;

public class GraphDFS1 {
    static int[][] adjMat = new int[100][100];
    static int[][] adjList = new int[100][100];
    static int[] listSize = new int[100];
    static int[] visited = new int[100];
    static int[] discovery = new int[100];
    static int[] finish = new int[100];
    static int[] traversalOrder = new int[100];
    static int traversalIndex = 0;
    static int time = 0;
    static int v;

    public static void dfs(int u) {
        visited[u] = 1;
        discovery[u] = ++time;
        traversalOrder[traversalIndex++] = u;
        for (int i = 0; i < v; i++) {
            if (adjMat[u][i] == 1 && visited[i] == 0) {
                dfs(i);
            }
        }
        finish[u] = ++time;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices and edges: ");
        v = sc.nextInt();
        int e = sc.nextInt();

        System.out.print("Is the graph directed? (1 for Yes, 0 for No): ");
        int isDirected = sc.nextInt();

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int vv = sc.nextInt();
            adjMat[u][vv] = 1;
            adjList[u][listSize[u]++] = vv;

            if (isDirected == 0) {
                adjMat[vv][u] = 1;
                adjList[vv][listSize[vv]++] = u;
            }
        }

        Arrays.fill(visited, 0);

        System.out.println("\nDFS Traversal with Discovery & Finish Time:");
        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) dfs(i);
        }

        System.out.println("\nTraversal Order:");
        for (int i = 0; i < traversalIndex; i++) {
            System.out.print(traversalOrder[i] + " ");
        }

        System.out.println("\n\nVertex\tDiscovery\tFinish");
        for (int i = 0; i < v; i++) {
            System.out.println(i + "\t" + discovery[i] + "\t\t" + finish[i]);
        }
    }
}
