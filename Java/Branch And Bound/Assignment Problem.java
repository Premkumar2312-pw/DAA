import java.util.Scanner;

public class AssignmentProblem {
    private static final int INF = Integer.MAX_VALUE;
    private static int[] finalAssignment;
    private static int[][] costMatrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of tasks: ");
        int n = scanner.nextInt();

        costMatrix = new int[n][n];
        System.out.println("Enter cost matrix: ");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                costMatrix[i][j] = scanner.nextInt();

        AssignmentProblem ap = new AssignmentProblem();
        int minCost = ap.branchAndBound(costMatrix, n);
        System.out.println("Minimum cost: " + minCost);

        System.out.println("Task Assignments:");
        for (int i = 0; i < n; i++) {
            char agent = (char) ('A' + i);
            int task = finalAssignment[i];
            int cost = costMatrix[i][task];
            System.out.println("Agent " + agent + " -> Task " + task + " (Cost: " + cost + ")");
        }
    }

    public int branchAndBound(int[][] cost, int n) {
        int[] assigned = new int[n];
        for (int i = 0; i < n; i++) assigned[i] = -1;

        finalAssignment = new int[n];
        return solve(cost, n, 0, 0, assigned, INF);
    }

    private int solve(int[][] cost, int n, int task, int sum, int[] assigned, int minCost) {
        if (task == n) {
            if (sum < minCost) {
                minCost = sum;
                // Convert assigned[task] = agent → finalAssignment[agent] = task
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (assigned[j] == i) {
                            finalAssignment[i] = j;
                            break;
                        }
                    }
                }
            }
            return sum;
        }

        for (int i = 0; i < n; i++) {
            if (assigned[i] == -1) {
                assigned[i] = task;
                int newCost = sum + cost[task][i];
                if (newCost < minCost) {
                    minCost = Math.min(minCost, solve(cost, n, task + 1, newCost, assigned, minCost));
                }
                assigned[i] = -1;
            }
        }
        return minCost;
    }
}
