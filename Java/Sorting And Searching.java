import java.io.*; 
import java.util.*; 

public class SortingAndSearching { 

    static String generateRandomFilename() { 
        return "numbers_" + System.currentTimeMillis() + ".txt"; 
    } 

    static void generateRandomFile(String filename, int size) throws IOException { 
        Random random = new Random(); 
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename)); 
        for (int i = 0; i < size; i++) { 
            writer.write(random.nextInt(1000) + " "); 
        } 
        writer.close(); 
    } 

    static int[] readFromFile(String filename) throws IOException { 
        List<Integer> numbers = new ArrayList<>(); 
        BufferedReader br = new BufferedReader(new FileReader(filename)); 
        String line; 
        while ((line = br.readLine()) != null) { 
            String[] parts = line.split("\\s+"); 
            for (String part : parts) { 
                numbers.add(Integer.parseInt(part)); 
            } 
        } 
        br.close(); 
        return numbers.stream().mapToInt(i -> i).toArray(); 
    } 

    static void mergeSort(int[] array, int left, int right) { 
        if (left < right) { 
            int mid = left + (right - left) / 2; 
            mergeSort(array, left, mid); 
            mergeSort(array, mid + 1, right); 
            merge(array, left, mid, right); 
        } 
    } 

    static void merge(int[] array, int left, int mid, int right) { 
        int n1 = mid - left + 1; 
        int n2 = right - mid; 
        int[] L = new int[n1]; 
        int[] R = new int[n2]; 
        System.arraycopy(array, left, L, 0, n1); 
        System.arraycopy(array, mid + 1, R, 0, n2); 
        int i = 0, j = 0, k = left; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) { 
                array[k++] = L[i++]; 
            } else { 
                array[k++] = R[j++]; 
            } 
        } 
        while (i < n1) array[k++] = L[i++]; 
        while (j < n2) array[k++] = R[j++]; 
    } 

    static void quickSort(int[] array, int low, int high) { 
        if (low < high) { 
            int pivotIndex = partition(array, low, high); 
            quickSort(array, low, pivotIndex - 1); 
            quickSort(array, pivotIndex + 1, high); 
        } 
    } 

    static int partition(int[] array, int low, int high) { 
        int pivot = array[high]; 
        int i = low - 1; 
        for (int j = low; j < high; j++) { 
            if (array[j] <= pivot) { 
                i++; 
                swap(array, i, j); 
            } 
        } 
        swap(array, i + 1, high); 
        return i + 1; 
    } 

    static void swap(int[] array, int i, int j) { 
        int temp = array[i]; 
        array[i] = array[j]; 
        array[j] = temp; 
    } 

    static int linearSearch(int[] array, int key) { 
        for (int i = 0; i < array.length; i++) { 
            if (array[i] == key) return i; 
        } 
        return -1; 
    } 

    static int binarySearch(int[] array, int key) { 
        return Arrays.binarySearch(array, key); 
    } 

    static int interpolationSearch(int[] array, int low, int high, int key) { 
        if (low <= high && key >= array[low] && key <= array[high]) { 
            if (array[high] == array[low]) 
                return (array[low] == key) ? low : -1; 

            int pos = low + ((key - array[low]) * (high - low) / (array[high] - array[low])); 

            if (pos < low || pos > high) 
                return -1; 

            if (array[pos] == key) 
                return pos; 

            return (array[pos] < key) 
                    ? interpolationSearch(array, pos + 1, high, key) 
                    : interpolationSearch(array, low, pos - 1, key); 
        } 
        return -1; 
    } 

    public static void main(String[] args) { 
        try { 
            String filename = generateRandomFilename(); 
            generateRandomFile(filename, 20); 
            System.out.println("File created: " + filename); 
            int[] array = readFromFile(filename); 
            int key = array[new Random().nextInt(array.length)]; 

            int[] mergeArray = Arrays.copyOf(array, array.length); 
            long startMerge = System.nanoTime(); 
            mergeSort(mergeArray, 0, mergeArray.length - 1); 
            long mergeTime = (System.nanoTime() - startMerge) / 1000; 

            int[] quickArray = Arrays.copyOf(array, array.length); 
            long startQuick = System.nanoTime(); 
            quickSort(quickArray, 0, quickArray.length - 1); 
            long quickTime = (System.nanoTime() - startQuick) / 1000; 

            long startLinear = System.nanoTime(); 
            int linearIndex = linearSearch(array, key); 
            long linearTime = (System.nanoTime() - startLinear) / 1000; 

            long startBinary = System.nanoTime(); 
            int binaryIndex = binarySearch(mergeArray, key); 
            long binaryTime = (System.nanoTime() - startBinary) / 1000; 

            long startInterpolation = System.nanoTime(); 
            int interpolationIndex = interpolationSearch(mergeArray, 0, mergeArray.length - 1, key); 
            long interpolationTime = (System.nanoTime() - startInterpolation) / 1000; 

            System.out.println("Original Array:"); 
            System.out.println(Arrays.toString(array)); 

            System.out.println("\nSorted Array (Merge Sort):"); 
            System.out.println(Arrays.toString(mergeArray)); 

            System.out.println("\nSorted Array (Quick Sort):"); 
            System.out.println(Arrays.toString(quickArray)); 

            System.out.println("\nSorting Times (microseconds):"); 
            System.out.println("Merge Sort: " + mergeTime); 
            System.out.println("Quick Sort: " + quickTime); 

            System.out.println("\nSearching for key: " + key); 
            System.out.println("Linear Search -> Index: " + 
                (linearIndex >= 0 ? linearIndex : "Not found") + ", Time: " + linearTime + " µs"); 

            System.out.println("Binary Search -> Index: " + 
                (binaryIndex >= 0 ? binaryIndex : "Not found") + ", Time: " + binaryTime + " µs"); 

            System.out.println("Interpolation Search -> Index: " + 
                (interpolationIndex >= 0 ? interpolationIndex : "Not found") + ", Time: " + interpolationTime + " µs"); 

        } catch (IOException e) { 
            System.out.println("Error: " + e.getMessage()); 
        } 
    } 
}