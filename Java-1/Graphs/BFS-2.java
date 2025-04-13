import java.util.Scanner;

public class GraphSimple2 {
    static int[][] adj = new int[100][100];
    static int[] visited = new int[100];
    static int[] parent = new int[100];
    static int[] distance = new int[100];
    static int v, isUndirected;

    public static void bfs(int src) {
        int[] queue = new int[100];
        int front = 0, rear = 0;

        for (int i = 0; i < v; i++) {
            visited[i] = 0;
            distance[i] = -1;
            parent[i] = -1;
        }

        queue[rear++] = src;
        visited[src] = 1;
        distance[src] = 0;

        while (front < rear) {
            int node = queue[front++];
            for (int i = 0; i < v; i++) {
                if (adj[node][i] == 1 && visited[i] == 0) {
                    visited[i] = 1;
                    distance[i] = distance[node] + 1;
                    parent[i] = node;
                    queue[rear++] = i;
                }
            }
        }
    }

    public static void shortestPath(int dest) {
        if (distance[dest] == -1) {
            System.out.println("No path to destination");
            return;
        }

        System.out.println("Shortest Distance: " + distance[dest]);
        System.out.print("Path: ");
        int[] path = new int[100];
        int len = 0, cur = dest;
        while (cur != -1) {
            path[len++] = cur;
            cur = parent[cur];
        }
        for (int i = len - 1; i >= 0; i--)
            System.out.print(path[i] + " ");
        System.out.println();
    }

    public static boolean dfsCycle(int node, int par) {
        visited[node] = 1;
        for (int i = 0; i < v; i++) {
            if (adj[node][i] == 1) {
                if (visited[i] == 0) {
                    if (dfsCycle(i, node))
                        return true;
                } else if (i != par)
                    return true;
            }
        }
        return false;
    }

    public static int countConnected() {
        int count = 0;
        for (int i = 0; i < v; i++)
            visited[i] = 0;

        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) {
                dfsCycle(i, -1);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices and edges: ");
        v = sc.nextInt();
        int e = sc.nextInt();

        System.out.print("Enter 1 for Undirected or 0 for Directed graph: ");
        isUndirected = sc.nextInt();

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int vv = sc.nextInt();
            adj[u][vv] = 1;
            if (isUndirected == 1)
                adj[vv][u] = 1;
        }

        System.out.print("Enter source for BFS: ");
        int src = sc.nextInt();
        bfs(src);

        System.out.print("Enter destination to find shortest path: ");
        int dest = sc.nextInt();
        shortestPath(dest);

        if (isUndirected == 1) {
            for (int i = 0; i < v; i++) visited[i] = 0;
            if (dfsCycle(src, -1))
                System.out.println("Cycle is present");
            else
                System.out.println("No cycle found");

            int comp = countConnected();
            System.out.println("Number of connected components: " + comp);
        } else {
            System.out.println("Cycle detection and component count is only for undirected graphs.");
        }
    }
}
