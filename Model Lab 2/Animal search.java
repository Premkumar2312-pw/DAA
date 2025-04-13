import java.util.Scanner;

public class AnimalSearch {
public static void main(String[] args) {  
    Scanner sc = new Scanner(System.in);  

    // Input  
    System.out.print("Enter number of animals: ");  
    int n = sc.nextInt();  
    sc.nextLine(); // consume newline  
    String[] animals = new String[n];  

    System.out.println("Enter animal names:");  
    for (int i = 0; i < n; i++) {  
        animals[i] = sc.nextLine();  
    }  

    // Sort using Merge Sort  
    mergeSort(animals, 0, n - 1);  

    // Output sorted list  
    System.out.println("Sorted animal list:");  
    for (String a : animals)  
        System.out.print(a + " ");  
    System.out.println();  

    // Search  
    System.out.print("Enter animal name to search: ");  
    String target = sc.nextLine();  
    int index = binarySearch(animals, target);  

    // Result  
    if (index != -1)  
        System.out.println(target + " found at index " + index);  
    else  
        System.out.println(target + " not found");  
}  

// Merge Sort  
static void mergeSort(String[] arr, int left, int right) {  
    if (left < right) {  
        int mid = (left + right) / 2;  
        mergeSort(arr, left, mid);  
        mergeSort(arr, mid + 1, right);  
        merge(arr, left, mid, right);  
    }  
}  

static void merge(String[] arr, int left, int mid, int right) {  
    int n1 = mid - left + 1, n2 = right - mid;  
    String[] L = new String[n1], R = new String[n2];  
  
    for (int i = 0; i < n1; i++) L[i] = arr[left + i];  
    for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];  

    int i = 0, j = 0, k = left;  
    while (i < n1 && j < n2) {  
        if (L[i].compareTo(R[j]) <= 0)  
            arr[k++] = L[i++];  
        else  
            arr[k++] = R[j++];  
    }  
    while (i < n1) arr[k++] = L[i++];  
    while (j < n2) arr[k++] = R[j++];  
}  

// Binary Search  
static int binarySearch(String[] arr, String key) {  
    int left = 0, right = arr.length - 1;  
    while (left <= right) {  
        int mid = (left + right) / 2;  
        int cmp = key.compareTo(arr[mid]);  
        if (cmp == 0) return mid;  
        else if (cmp < 0) right = mid - 1;  
        else left = mid + 1;  
    }  
    return -1;  
  }
} 
