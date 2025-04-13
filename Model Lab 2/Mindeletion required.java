import java.util.Scanner;

public class MinDeletionsWithMinTracking {

    // Function to find min and max index using Divide & Conquer
    public static int[] findMinMax(int[] nums, int left, int right) {
        if (left == right) {
            return new int[]{left, left};
        }

        int mid = (left + right) / 2;
        int[] leftPair = findMinMax(nums, left, mid);
        int[] rightPair = findMinMax(nums, mid + 1, right);

        int minIdx = nums[leftPair[0]] < nums[rightPair[0]] ? leftPair[0] : rightPair[0];
        int maxIdx = nums[leftPair[1]] > nums[rightPair[1]] ? leftPair[1] : rightPair[1];

        System.out.println("Comparing range [" + left + ", " + right + "]: Min = " + nums[minIdx]);

        return new int[]{minIdx, maxIdx};
    }

    public static int minimumDeletions(int[] nums) {
        int n = nums.length;
        int[] minMax = findMinMax(nums, 0, n - 1);
        int minIndex = minMax[0];
        int maxIndex = minMax[1];

        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        int front = maxIndex + 1;
        int back = n - minIndex;
        int both = (minIndex + 1) + (n - maxIndex);

        return Math.min(front, Math.min(back, both));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter " + n + " distinct elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = minimumDeletions(nums);
        System.out.println("Minimum deletions needed: " + result);
    }
}
