import java.util.Scanner;

public class TSPBranchBound {
    private static final int INF = Integer.MAX_VALUE;
    private static int minCost = INF;
    private static int[] bestPath = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of cities (vertices): ");
        int n = sc.nextInt();

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dist[i][j] = (i == j) ? 0 : INF;

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        System.out.println("Enter edges (u v w): ");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            dist[u][v] = w;
            dist[v][u] = w;
        }

        TSPBranchBound tsp = new TSPBranchBound();
        tsp.solveTSP(dist, n);

        System.out.println("\nMinimum cost: " + minCost);
        System.out.print("Path: ");
        for (int city : bestPath)
            System.out.print(city + " ");
        System.out.println("0"); // Return to start
    }

    public void solveTSP(int[][] dist, int n) {
        int[] visited = new int[n];
        int[] path = new int[n];
        visited[0] = 1;
        path[0] = 0;
        branchAndBound(dist, visited, path, 0, 1, 0, n);
    }

    private void branchAndBound(int[][] dist, int[] visited, int[] path, int currCity, int count, int cost, int n) {
        if (count == n && dist[currCity][0] != INF) {
            int totalCost = cost + dist[currCity][0];
            System.out.print("Path tried: ");
            for (int i = 0; i < n; i++) System.out.print(path[i] + " ");
            System.out.println("0 | Cost: " + totalCost);

            if (totalCost < minCost) {
                minCost = totalCost;
                bestPath = path.clone();
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0 && dist[currCity][i] != INF) {
                visited[i] = 1;
                path[count] = i;
                branchAndBound(dist, visited, path, i, count + 1, cost + dist[currCity][i], n);
                visited[i] = 0; // No print for backtrack
            }
        }
    }
}
