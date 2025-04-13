import java.util.*;

public class FloydWarshall {
static final int INF = 99999;

static void floydWarshall(int graph[][], int V) {  
    int dist[][] = new int[V + 1][V + 1];  
    int parent[][] = new int[V + 1][V + 1];  

    // Initialize distance and parent matrices  
    for (int i = 1; i <= V; i++) {  
        for (int j = 1; j <= V; j++) {  
            dist[i][j] = graph[i][j];  
            if (i == j || graph[i][j] == INF)  
                parent[i][j] = -1;  
            else  
                parent[i][j] = i;  
        }  
    }  

    // Floyd-Warshall Algorithm  
    for (int k = 1; k <= V; k++) {  
        System.out.println("\nIteration " + k + " (Using Vertex " + k + " as Intermediate):");  
        for (int i = 1; i <= V; i++) {  
            for (int j = 1; j <= V; j++) {  
                if (dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j]) {  
                    dist[i][j] = dist[i][k] + dist[k][j];  
                    parent[i][j] = parent[k][j]; // Update parent matrix  
                }  
            }  
        }  

        // Print matrices after each iteration  
        printMatrix("Distance Matrix:", dist, V);  
        printMatrix("Parent Matrix:", parent, V);  

        // Check for negative cycle  
        for (int i = 1; i <= V; i++) {  
            if (dist[i][i] < 0) {  
                System.out.println("\nNegative cycle detected!");  
                return;  
            }  
        }  
    }  

    // Print all-pairs shortest path  
    System.out.println("\nAll-Pairs Shortest Paths:");  
    for (int i = 1; i <= V; i++) {  
        for (int j = 1; j <= V; j++) {  
            if (i != j) {  
                if (dist[i][j] == INF) {  
                    System.out.println(i + " to " + j + ": No path");  
                } else {  
                    System.out.print(i + " to " + j + ": ");  
                    printPath(i, j, parent);  
                    System.out.println(" (Distance: " + dist[i][j] + ")");  
                }  
            }  
        }  
    }  
}  

// Function to print matrix  
static void printMatrix(String title, int matrix[][], int V) {  
    System.out.println(title);  
    for (int i = 1; i <= V; i++) {  
        for (int j = 1; j <= V; j++) {  
            if (matrix[i][j] == INF)  
                System.out.print("INF\t");  
            else  
                System.out.print(matrix[i][j] + "\t");  
        }  
        System.out.println();  
    }  
}  

// Function to reconstruct and print path  
static void printPath(int u, int v, int parent[][]) {  
    if (parent[u][v] == -1) {  
        System.out.print("No path");  
        return;  
    }  
    List<Integer> path = new ArrayList<>();  
    path.add(v);  
    while (u != v) {  
        v = parent[u][v];  
        path.add(v);  
    }  
    Collections.reverse(path);  
    for (int i = 0; i < path.size(); i++) {  
        System.out.print(path.get(i));  
        if (i < path.size() - 1)  
            System.out.print(" -> ");  
    }  
}  

public static void main(String args[]) {  
    Scanner sc = new Scanner(System.in);  
    System.out.print("Enter number of vertices: ");  
    int V = sc.nextInt();  
    int graph[][] = new int[V + 1][V + 1];  

    // Initialize graph with INF  
    for (int i = 1; i <= V; i++)  
        Arrays.fill(graph[i], INF);  

    for (int i = 1; i <= V; i++)  
        graph[i][i] = 0;  

    System.out.print("Enter number of edges: ");  
    int E = sc.nextInt();  

    System.out.println("Enter edges in the format (u v weight):");  
    for (int i = 0; i < E; i++) {  
        int u = sc.nextInt();  
        int v = sc.nextInt();  
        int w = sc.nextInt();  
        graph[u][v] = w;  
    }  
    floydWarshall(graph, V);  
    sc.close();  
}
} 
