import java.util.Scanner;

public class MinMaxDivideConquer {
    static class MinMax {
        int min;
        int max;
    }

    static int step = 1;

    static void printTable(int step, int left, int right, int[] arr, MinMax result) {
        StringBuilder range = new StringBuilder();
        for (int i = left; i <= right; i++) {
            range.append(arr[i]).append(" ");
        }

        System.out.printf("| %2d  | %-5d | %-5d | %-15s | %-5s | %-5s |\n",
                step, left, right, range.toString().trim(),
                result.min == Integer.MAX_VALUE ? "-" : result.min,
                result.max == Integer.MIN_VALUE ? "-" : result.max);
    }

    static MinMax findMinMax(int[] arr, int left, int right) {
        MinMax result = new MinMax();
        result.min = Integer.MAX_VALUE;
        result.max = Integer.MIN_VALUE;

        if (left == right) {
            result.min = result.max = arr[left];
            printTable(step++, left, right, arr, result);
            return result;
        }

        if (right == left + 1) {
            if (arr[left] < arr[right]) {
                result.min = arr[left];
                result.max = arr[right];
            } else {
                result.min = arr[right];
                result.max = arr[left];
            }
            printTable(step++, left, right, arr, result);
            return result;
        }

        int mid = (left + right) / 2;

        MinMax leftResult = findMinMax(arr, left, mid);
        MinMax rightResult = findMinMax(arr, mid + 1, right);

        result.min = Math.min(leftResult.min, rightResult.min);
        result.max = Math.max(leftResult.max, rightResult.max);

        printTable(step++, left, right, arr, result);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("\n|Step | Left | Right | Elements in Range | Min  | Max  |");
        System.out.println("|-----|-------|-------|-------------------|------|------|");

        MinMax result = findMinMax(arr, 0, n - 1);

        System.out.println("\nMinimum element: " + result.min);
        System.out.println("Maximum element: " + result.max);

        scanner.close();
    }
}
