package search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = new int[]{-1, 3, 7, 9, 12, 13, 14, 16};
        System.out.println(search(array, 9));
    }

    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
