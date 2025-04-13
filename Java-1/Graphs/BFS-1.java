//GRAPH REPRESENTATION, SHORTEST PATH 

import java.util.Scanner;

public class GraphSimple1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] adj = new int[100][100];
        int[] visited = new int[100];
        int[] parent = new int[100];
        int[] distance = new int[100];
        int[] queue = new int[100];

        System.out.print("Enter number of vertices and edges: ");
        int v = sc.nextInt();
        int e = sc.nextInt();

        System.out.print("Enter 1 for Undirected or 0 for Directed graph: ");
        int isUndirected = sc.nextInt();

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

        for (int i = 0; i < v; i++) {
            visited[i] = 0;
            parent[i] = -1;
            distance[i] = -1;
        }

        int front = 0, rear = 0;
        queue[rear++] = src;
        visited[src] = 1;
        distance[src] = 0;

        System.out.print("BFS Traversal Order: ");
        while (front < rear) {
            int node = queue[front++];
            System.out.print(node + " ");

            for (int i = 0; i < v; i++) {
                if (adj[node][i] == 1 && visited[i] == 0) {
                    queue[rear++] = i;
                    visited[i] = 1;
                    parent[i] = node;
                    distance[i] = distance[node] + 1;
                }
            }
        }

        System.out.println("\n\nNode\tDistance\tParent");
        for (int i = 0; i < v; i++) {
            System.out.println(i + "\t" + distance[i] + "\t\t" + parent[i]);
        }
    }
}
