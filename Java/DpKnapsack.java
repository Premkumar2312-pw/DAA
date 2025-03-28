import java.util.*;

public class knapsackDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out
        .print("Enter number of items: ");
        int n = scanner.nextInt();
        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("Enter weights and values of items:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int W = scanner.nextInt();

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }

        printTable(dp, weights, values, W);

        System.out.println("Maximum value that can be obtained: " + dp[n][W]);

        List<Integer> selectedItems = new ArrayList<>();
        int i = n, j = W;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i - 1][j]) {
                selectedItems.add(i);
                j -= weights[i - 1];
            }
            i--;
        }

        System.out.print("Selected items : {");
        for (int k = selectedItems.size() - 1; k >= 0; k--) {
            System.out.print(selectedItems.get(k));
            if (k > 0) {
                System.out.print(", ");
            }
        }
        System.out.println("}");

        scanner.close();
    }

    private static void printTable(int[][] dp, int[] weights, int[] values, int W) {
        System.out.print("\t   T");
        for (int j = 0; j <= W; j++) {
            System.out.printf("%3d", j);
        }
        System.out.println();
        System.out.print("\t  ");
        for (int i = 0; i <= weights.length; i++) {
            if (i == 0) {
                System.out.print(" 0");
            } else {
                System.out.printf("w%d=%d v%d=%d  ", i, weights[i - 1], i, values[i - 1]);
            }

            for (int j = 0; j <= W; j++) {
                System.out.printf("%3d", dp[i][j]);
            }
            System.out.println();
        }
    }
}
