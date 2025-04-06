import java.util.*;

public class coinchangeDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of denominations: ");
        int n = scanner.nextInt();
        int[] denominations = new int[n];
        System.out.println("Enter denominations:");
        for (int i = 0; i < n; i++) {
            denominations[i] = scanner.nextInt();
        }
        System.out.print("Enter target amount: ");
        int amount = scanner.nextInt();
        int[][] dp = new int[n + 1][amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = amount + 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j < denominations[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] =  Math.min(dp[i-1][j], 1+ dp[i][j-denominations[i-1]]);
                }
            }
        }
        printTable(dp, denominations, amount);
        if (dp[n][amount] == amount + 1) {
            System.out.println("\nNo solution possible with given denominations.");
            scanner.close();
            return;
        }
        System.out.println("Minimum number of coins needed: " + dp[n][amount]);
        List<Integer> usedCoins = new ArrayList<>();
        int i = n, j = amount;
        while (j > 0 && i > 0) {
            if (dp[i][j] != dp[i-1][j]) {
                usedCoins.add(denominations[i-1]);
                j -= denominations[i-1];
            } else {
                i--;
            }
        }
        System.out.print("Denominations used: {");
        for (int k = 0; k < usedCoins.size(); k++) {
            System.out.print(usedCoins.get(k));
            if (k < usedCoins.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");

        scanner.close();
    }
    private static void printTable(int[][] dp, int[] denominations, int amount) {
        System.out.print("  T  ");
        for (int j = 0; j <= amount; j++) {
            System.out.printf("%3d", j);
        }
        System.out.println();
        for (int i = 0; i <= denominations.length; i++) {
            if (i == 0) {
                System.out.print("  0  ");
            } else {
                System.out.printf("%3d  ", denominations[i - 1]);
            }
            for (int j = 0; j <= amount; j++) {
                if (dp[i][j] == amount + 1) {
                    System.out.printf("%3d", amount + 1);
                } else {
                    System.out.printf("%3d", dp[i][j]);
                }
            }
            System.out.println();
        }
    }
}
