import java.util.*;

public class KnapsackValueBasedDP2D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter number of items and capacity: ");
        int n = sc.nextInt();
        int c = sc.nextInt();

        int[] value = new int[n];
        int[] weight = new int[n];

        System.out.print("Enter values: ");
        for (int i = 0; i < n; i++) value[i] = sc.nextInt();

        System.out.print("Enter weights: ");
        for (int i = 0; i < n; i++) weight[i] = sc.nextInt();

        int MAX_TOTAL_VALUE = n * 100; // max single item value = 100 (assumption)
        long INF = (long) 1e18;

        long[][] dp = new long[n + 1][MAX_TOTAL_VALUE + 1];

        // Initialize DP
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], INF);
        dp[0][0] = 0;

        // DP computation
        for (int i = 1; i <= n; i++) {
            for (int v = 0; v <= MAX_TOTAL_VALUE; v++) {
                dp[i][v] = dp[i - 1][v]; // exclude current item
                if (v >= value[i - 1] && dp[i - 1][v - value[i - 1]] + weight[i - 1] <= c) {
                    dp[i][v] = Math.min(dp[i][v], dp[i - 1][v - value[i - 1]] + weight[i - 1]); // include
                }
            }
        }

        // Find maximum value within weight capacity
        int ans = 0;
        for (int v = 0; v <= MAX_TOTAL_VALUE; v++) {
            if (dp[n][v] <= c)
                ans = v;
        }

        System.out.println("Maximum value that can be taken: " + ans);
    }
}
