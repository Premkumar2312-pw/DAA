import java.util.Scanner;

public class InversionCountUserInput {

public static void main(String[] args) {  
    Scanner sc = new Scanner(System.in);   
    System.out.print("Enter the size of the array: ");  
    int n = sc.nextInt();  

    int[] arr = new int[n];  
    System.out.println("Enter the array elements:");  
    for(int i = 0; i < n; i++) {  
        arr[i] = sc.nextInt();  
    }  

    int result = countInversions(arr);  
    System.out.println("Total Number of inversions: " + result);  
}  

public static int countInversions(int[] arr) {  
    return mergeSort(arr, 0, arr.length - 1);  
}  

private static int mergeSort(int[] arr, int left, int right) {  
    int count = 0;  
    if (left < right) {  
        int mid = (left + right) / 2;  

        count += mergeSort(arr, left, mid);  
        count += mergeSort(arr, mid + 1, right);  

        count += merge(arr, left, mid, right);  
    }  
    return count;  
}  

private static int merge(int[] arr, int left, int mid, int right) {  
    int[] temp = new int[right - left + 1];  
    int i = left, j = mid + 1, k = 0;  
    int invCount = 0;  

    System.out.print("Merging left = [");  
    for (int x = left; x <= mid; x++) System.out.print(arr[x] + (x < mid ? ", " : ""));  
    System.out.print("], right = [");  
    for (int x = mid + 1; x <= right; x++) System.out.print(arr[x] + (x < right ? ", " : ""));  
    System.out.println("]");  

    while (i <= mid && j <= right) {  
        if (arr[i] <= arr[j]) {  
            temp[k++] = arr[i++];  
        } else {  
            temp[k++] = arr[j++];  
            invCount += (mid - i + 1); // All remaining elements in left are greater than arr[j]  
        }  
    }  

    while (i <= mid)  
        temp[k++] = arr[i++];  

    while (j <= right)  
        temp[k++] = arr[j++];  

    for (int x = 0; x < temp.length; x++) {  
        arr[left + x] = temp[x];  
    }  

    System.out.println("Inversions in this merge: " + invCount);  
    System.out.print("Array after merge: [");  
    for (int x = left; x <= right; x++) System.out.print(arr[x] + (x < right ? ", " : ""));  
    System.out.println("]\n");  

    return invCount;  
  }
}
