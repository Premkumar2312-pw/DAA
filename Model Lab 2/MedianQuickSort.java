import java.util.Scanner;

public class MedianQuickSort {
    static int comparisons = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt(), arr[] = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        
        quickSort(arr, 0, n - 1);
        
        System.out.println("\nFinal Sorted Array:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println("\nTotal Comparisons: " + comparisons);
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIdx = medianOfThree(arr, low, high);
            System.out.println("Median-of-Three Pivot selected: " + arr[pivotIdx]);
            pivotIdx = partition(arr, low, high, pivotIdx);
          
            System.out.print("Array after partitioning: ");
            printArray(arr, low, high);
            System.out.println("Comparisons so far: " + comparisons + "\n--------------------------------------------------");
            quickSort(arr, low, pivotIdx - 1);
            quickSort(arr, pivotIdx + 1, high);
        }
    }

    static int medianOfThree(int[] arr, int low, int high) {
        int mid = (low + high) / 2;
        if (arr[low] > arr[mid]) swap(arr, low, mid);
        if (arr[low] > arr[high]) swap(arr, low, high);
        if (arr[mid] > arr[high]) swap(arr, mid, high);
        swap(arr, mid, high - 1);
        return high - 1;
    }

    static int partition(int[] arr, int low, int high, int pivotIdx) {
        int pivot = arr[pivotIdx];
        swap(arr, pivotIdx, high - 1);
        int i = low;
        for (int j = low; j < high - 1; j++) {
            comparisons++;
            if (arr[j] < pivot) swap(arr, i++, j);
        }
        swap(arr, i, high - 1);
        return i;
    }

    static void swap(int[] arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }

    static void printArray(int[] arr, int low, int high) {
        for (int i = 0; i < arr.length; i++)
            System.out.print((i >= low && i <= high) ? arr[i] + " " : "_ ");
        System.out.println();
    }
}
