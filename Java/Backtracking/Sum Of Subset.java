import java.util.*;

public class SumOfSubsets {
    static int sosStep = 1;
    static int solutionCount = 0;
    static int[] set;
    static int target;
    static int[][] solutions = new int[100][100];  // Maximum 100 subsets for simplicity
    static int[] currentSubset = new int[100];  // For storing current subset

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements in the set: ");
        int n = sc.nextInt();
        set = new int[n];

        System.out.print("Enter the elements of the set: ");
        for (int i = 0; i < n; i++) {
            set[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        target = sc.nextInt();

        System.out.println("\nFinding subsets summing to " + target + "...\n");

        sumOfSubsets(0, 0, 0); // Start recursion

        if (solutionCount == 0) {
            System.out.println("No solution exists.");
        } else {
            System.out.println("\nTotal solutions found: " + solutionCount);
            System.out.println("All valid subsets:");
            for (int i = 0; i < solutionCount; i++) {
                System.out.print("[");
                for (int j = 0; solutions[i][j] != 0; j++) {
                    System.out.print(solutions[i][j] + " ");
                }
                System.out.println("]");
            }
        }
    }

    static void sumOfSubsets(int index, int currentSum, int currentSize) {
        // If the current sum matches the target, store the current subset
        if (currentSum == target) {
            System.out.print("Step " + sosStep++ + ": Subset found -> [");
            for (int i = 0; i < currentSize; i++) {
                solutions[solutionCount][i] = currentSubset[i];
                System.out.print(currentSubset[i] + " ");
            }
            System.out.println("]");
            solutionCount++;
            return;
        }

        if (index >= set.length) return;

        // Include current element in the subset
        currentSubset[currentSize] = set[index];
        System.out.println("Step " + sosStep++ + ": Include " + set[index] + " => Subset: " + printCurrentSubset(currentSize + 1) + ", Sum: " + (currentSum + set[index]));
        sumOfSubsets(index + 1, currentSum + set[index], currentSize + 1);

        // Exclude current element from the subset
        System.out.println("Step " + sosStep++ + ": Exclude " + set[index] + " => Subset: " + printCurrentSubset(currentSize) + ", Sum: " + currentSum);
        sumOfSubsets(index + 1, currentSum, currentSize);
    }

    static String printCurrentSubset(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(currentSubset[i]).append(" ");
        }
        return sb.toString();
    }
}
