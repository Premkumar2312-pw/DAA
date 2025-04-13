import java.util.Scanner;

public class ActivitySelection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of activities: ");
        int n = sc.nextInt();

        int[] start = new int[n];
        int[] end = new int[n];

        System.out.println("Enter start and end times:");
        for (int i = 0; i < n; i++) {
            start[i] = sc.nextInt();
            end[i] = sc.nextInt();
        }

        // Sort activities by end time
        for (int i = 0; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                if (end[i] > end[j]) {
                    int temp = end[i]; end[i] = end[j]; end[j] = temp;
                    temp = start[i]; start[i] = start[j]; start[j] = temp;
                }
            }
        }

        System.out.println("\nSelected Activities:");
        int count = 1;
        System.out.println("Activity 0: (" + start[0] + ", " + end[0] + ")");
        int lastEnd = end[0];

        for (int i = 1; i < n; i++) {
            if (start[i] >= lastEnd) {
                System.out.println("Activity " + i + ": (" + start[i] + ", " + end[i] + ")");
                lastEnd = end[i];
                count++;
            }
        }

        System.out.println("\nMaximum number of non-overlapping activities: " + count);
    }
}
