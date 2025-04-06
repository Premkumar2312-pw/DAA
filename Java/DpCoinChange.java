import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of coin denominations: ");
        int n = scanner.nextInt();
        int[] coins = new int[n];

        System.out.println("Enter the coin denominations: ");
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        System.out.print("Enter the amount: ");
        int amount = scanner.nextInt();

        int[][] dp = new int[n + 1][amount + 1];
        int[][] coinTracker = new int[n + 1][amount + 1];

        for (int j = 1; j <= amount; j++) {
            dp[0][j] = amount + 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == 0) {
                    dp[i][j] = 0;
                } else if (coins[i - 1] <= j) {
                    if (dp[i - 1][j] <= 1 + dp[i][j - coins[i - 1]]) {
                        dp[i][j] = dp[i - 1][j];
                        coinTracker[i][j] = coinTracker[i - 1][j];
                    } else {
                        dp[i][j] = 1 + dp[i][j - coins[i - 1]];
                        coinTracker[i][j] = coins[i - 1];
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                    coinTracker[i][j] = coinTracker[i - 1][j];
                }
            }
        }

        System.out.println("\nDP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        int result = dp[n][amount];
        if (result > amount) {
            System.out.println("No solution possible.");
        } else {
            System.out.println("Minimum coins required: " + result);
            System.out.print("Coins used: ");
            int i = n, j = amount;
            while (j > 0) {
                int coin = coinTracker[i][j];
                if (coin == 0) break;
                System.out.print(coin + " ");
                j -= coin;
            }
            System.out.println();
        }

        scanner.close();
    }
}
