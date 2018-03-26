package example.study;

import java.util.Arrays;

/**
 * Created by wuruixuan on 2017/12/29.
 */

public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] nums = {20, 34, 30, 45, 54, 60};
        Arrays.sort(nums);
//        int index = Arrays.binarySearch(nums, 30);
        int index = binarySearch(nums, 30);
    }

    public static int binarySearch(int[] nums, int key) {
        int start = 0;
        int end = nums.length - 1;
        int mid = -1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == key) {
                return mid;
            }
            else if (nums[mid] < key) {
                start = mid + 1;
            }
            else if (nums[mid] > key) {
                end = mid - 1;
            }
        }

        return -1;
    }
}


