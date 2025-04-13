import java.util.Scanner;

public class SearchComparison {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter sorted elements:");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.print("Enter element to search: ");
        int key = sc.nextInt();

        // Interpolation Search
        long startTime = System.nanoTime();
        int interpIndex = interpolationSearch(arr, key);
        long endTime = System.nanoTime();
        System.out.println("Interpolation Search Result:");
        System.out.println(interpIndex != -1 ? "Found at index " + interpIndex : "Not found");
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
        // Binary Search
        startTime = System.nanoTime();
        int binaryIndex = binarySearch(arr, key);
        endTime = System.nanoTime();
        System.out.println("Binary Search Result:");
        System.out.println(binaryIndex != -1 ? "Found at index " + binaryIndex : "Not found");
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
    }

    // Interpolation Search
    static int interpolationSearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high && key >= arr[low] && key <= arr[high]) {
            if (arr[high] == arr[low]) {
                if (arr[low] == key) return low;
                else break;
            }
            int pos = low + ((key - arr[low]) * (high - low)) / (arr[high] - arr[low]);
            if (pos < low || pos > high) break;

            if (arr[pos] == key) return pos;
            if (arr[pos] < key) low = pos + 1;
            else high = pos - 1;
        }
        return -1;
    }
    // Binary Search
    static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == key) return mid;
            if (arr[mid] < key) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
