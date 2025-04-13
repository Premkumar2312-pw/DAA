import java.util.Scanner;

public class FractionalKnapsack {

    // Method to calculate maximum value based on profit
    public static double knapsackByProfit(int[] weight, int[] value, int n, int W) {
        System.out.println("\nKnapsack By Profit:");
        // Sort items by value (descending)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (value[j] < value[j + 1]) {
                    // Swap values
                    int tempValue = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = tempValue;

                    // Swap weights
                    int tempWeight = weight[j];
                    weight[j] = weight[j + 1];
                    weight[j + 1] = tempWeight;
                }
            }
        }

        double totalValue = 0;
        System.out.println("Item | Weight | Profit | Remaining Capacity | Profit So Far");
        for (int i = 0; i < n; i++) {
            if (W == 0) break;
            double profit = 0;
            if (weight[i] <= W) {
                W -= weight[i];
                profit = value[i];
            } else {
                profit = value[i] * ((double) W / weight[i]);
                W = 0;
            }
            totalValue += profit;
            System.out.printf("%-4d | %-6d | %-6d | %-17d | %-13.2f\n", i + 1, weight[i], value[i], W, totalValue);
        }
        return totalValue;
    }

    // Method to calculate maximum value based on weight
    public static double knapsackByWeight(int[] weight, int[] value, int n, int W) {
        System.out.println("\nKnapsack By Weight:");
        // Sort items by weight (ascending)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (weight[j] > weight[j + 1]) {
                    // Swap weights
                    int tempWeight = weight[j];
                    weight[j] = weight[j + 1];
                    weight[j + 1] = tempWeight;

                    // Swap values
                    int tempValue = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = tempValue;
                }
            }
        }

        double totalValue = 0;
        System.out.println("Item | Weight | Profit | Remaining Capacity | Profit So Far");
        for (int i = 0; i < n; i++) {
            if (W == 0) break;
            double profit = 0;
            if (weight[i] <= W) {
                W -= weight[i];
                profit = value[i];
            } else {
                profit = value[i] * ((double) W / weight[i]);
                W = 0;
            }
            totalValue += profit;
            System.out.printf("%-4d | %-6d | %-6d | %-17d | %-13.2f\n", i + 1, weight[i], value[i], W, totalValue);
        }
        return totalValue;
    }

    // Method to calculate maximum value based on profit/weight ratio
    public static double knapsackByProfitWeightRatio(int[] weight, int[] value, int n, int W) {
        System.out.println("\nKnapsack By Profit/Weight Ratio:");
        // Calculate profit/weight ratio for each item
        double[] ratio = new double[n];
        for (int i = 0; i < n; i++) {
            ratio[i] = (double) value[i] / weight[i];
        }

        // Sort items based on ratio (descending)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ratio[j] < ratio[j + 1]) {
                    // Swap ratios
                    double tempRatio = ratio[j];
                    ratio[j] = ratio[j + 1];
                    ratio[j + 1] = tempRatio;

                    // Swap weights and values based on the ratio
                    int tempWeight = weight[j];
                    weight[j] = weight[j + 1];
                    weight[j + 1] = tempWeight;
                    int tempValue = value[j];
                    value[j] = value[j + 1];
                    value[j + 1] = tempValue;
                }
            }
        }

        double totalValue = 0;
        System.out.println("Item | Weight | Profit | Remaining Capacity | Profit So Far");
        for (int i = 0; i < n; i++) {
            if (W == 0) break;
            double profit = 0;
            if (weight[i] <= W) {
                W -= weight[i];
                profit = value[i];
            } else {
                profit = value[i] * ((double) W / weight[i]);
                W = 0;
            }
            totalValue += profit;
            System.out.printf("%-4d | %-6d | %-6d | %-17d | %-13.2f\n", i + 1, weight[i], value[i], W, totalValue);
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read number of items and knapsack capacity
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        System.out.print("Enter the capacity of the knapsack: ");
        int W = sc.nextInt();
        
        // Arrays to store weights and values
        int[] weight = new int[n];
        int[] value = new int[n];
        
        // Read items' weights and values
        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and value of item " + (i + 1) + ": ");
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        // Compute maximum values for each strategy
        double maxProfit = knapsackByProfit(weight, value, n, W);
        double maxWeight = knapsackByWeight(weight, value, n, W);
        double maxRatio = knapsackByProfitWeightRatio(weight, value, n, W);

        // Print results
        System.out.println("\nMax Value based on Profit: " + maxProfit);
        System.out.println("Max Value based on Weight: " + maxWeight);
        System.out.println("Max Value based on Profit/Weight Ratio: " + maxRatio);
        
        // Determine and print the optimal strategy
        double maxValue = Math.max(maxProfit, Math.max(maxWeight, maxRatio));
        
        if (maxValue == maxProfit) {
            System.out.println("Optimal Strategy: Based on Profit");
        } else if (maxValue == maxWeight) {
            System.out.println("Optimal Strategy: Based on Weight");
        } else {
            System.out.println("Optimal Strategy: Based on Profit/Weight Ratio");
        }

        sc.close();
    }
}
