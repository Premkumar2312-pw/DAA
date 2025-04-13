import java.util.*;

public class CoinChangeSimple {

    // Function to find minimum number of coins using greedy
    static int getMinCoins(int[] coins, int amount) {
        int count = 0;
        for (int coin : coins) {
            while (amount >= coin) {
                amount -= coin;
                count++;
            }
        }
        return (amount == 0) ? count : Integer.MAX_VALUE; // If not possible
    }

    // Function to reverse the array (for descending order)
    static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // User input
        System.out.print("Enter number of denominations: ");
        int n = sc.nextInt();
        int[] coins = new int[n];
        System.out.println("Enter the denominations:");
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        System.out.print("Enter the amount: ");
        int amount = sc.nextInt();

        // Copy arrays for different strategies
        int[] asc = coins.clone();
        int[] desc = coins.clone();
        int[] rand = coins.clone();

        // Ascending order
        Arrays.sort(asc);
        int coinsAsc = getMinCoins(asc, amount);

        // Descending order (best for greedy)
        Arrays.sort(desc);
        reverse(desc);
        int coinsDesc = getMinCoins(desc, amount);

        // Random order
        Random r = new Random();
        for (int i = 0; i < rand.length; i++) {
            int j = r.nextInt(rand.length);
            int temp = rand[i];
            rand[i] = rand[j];
            rand[j] = temp;
        }
        int coinsRand = getMinCoins(rand, amount);

        // Display results
        System.out.println("\nCoins needed in Ascending Order   : " + (coinsAsc == Integer.MAX_VALUE ? "Not Possible" : coinsAsc));
        System.out.println("Coins needed in Descending Order  : " + (coinsDesc == Integer.MAX_VALUE ? "Not Possible" : coinsDesc));
        System.out.println("Coins needed in Random Order      : " + (coinsRand == Integer.MAX_VALUE ? "Not Possible" : coinsRand));

        // Find best
        int minCoins = Math.min(coinsAsc, Math.min(coinsDesc, coinsRand));
        String strategy = "";
        if (minCoins == coinsAsc) strategy = "Ascending Order";
        else if (minCoins == coinsDesc) strategy = "Descending Order";
        else strategy = "Random Order";

        if (minCoins == Integer.MAX_VALUE) {
            System.out.println("\nNo solution possible with the given denominations.");
        } else {
            System.out.println("\nBest strategy: " + strategy + " with " + minCoins + " coins.");
        }
    }
}
