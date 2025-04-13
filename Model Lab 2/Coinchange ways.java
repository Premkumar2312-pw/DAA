import java.util.*;

public class Main {
    static int[][] result = new int[100][100];
    static int[] sizes = new int[100];
    static int count = 0;

    static void findWays(int[] coins, int n, int sum) {
        int[] current = new int[100];
        findAllCombinations(coins, n, sum, 0, current, 0);
    }

    static void findAllCombinations(int[] coins, int n, int amount, int index, int[] current, int len) {
        if (amount == 0) {
            for (int i = 0; i < len; i++) {
                result[count][i] = current[i];
            }
            sizes[count] = len;
            count++;
            return;
        }
        if (amount < 0 || index == n) return;

        // Include current coin
        current[len] = coins[index];
        findAllCombinations(coins, n, amount - coins[index], index, current, len + 1);

        // Exclude current coin
        findAllCombinations(coins, n, amount, index + 1, current, len);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount: ");
        int sum = sc.nextInt();
        System.out.print("Enter number of coin types: ");
        int n = sc.nextInt();

        int[] coins = new int[n];
        System.out.print("Enter the coin denominations: ");
        for (int i = 0; i < n; i++) coins[i] = sc.nextInt();

        Arrays.sort(coins);
        findWays(coins, n, sum);

        System.out.println("\nTotal number of ways to make change for " + sum + " = " + count);
        System.out.println("\nWays (as table):\n");

        // Print header
        System.out.printf("%-10s", "Way No.");
        for (int i = 0; i < sum; i++) System.out.printf("%-5s", "Coin");
        System.out.println();

        // Print combinations as table
        for (int i = 0; i < count; i++) {
            System.out.printf("%-10d", (i + 1));
            for (int j = 0; j < sizes[i]; j++) {
                System.out.printf("%-5d", result[i][j]);
            }
            System.out.println();
        }

        sc.close();
    }
}
