package pers.cjg.bs;

/**
 * @Author: chenjingang@guazi.com  2020-10-24 20:29
 */
public class BinarySearch {


    public static void main(String[] args) {
        Integer[] nums = {0, 1, 2, 4, 4, 4, 5};
        Integer target = 4;
        // 3 3 5
        System.out.println(binarySearch(nums, target));
        System.out.println(binarySearchLeft(nums, target));
        System.out.println(binarySearchRight(nums, target));
    }


    public static Integer binarySearch(Integer[] nums, Integer target) {
        Integer left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid].equals(target)) {
                return mid;
            }
        }
        return -1;
    }

    public static Integer binarySearchLeft(Integer[] nums, Integer target) {
        Integer left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid].equals(target)) {
                right = mid - 1;
            }
        }

        if (right+1 < 0 || nums[right+1] != target)
            return -1;
        return right+1;
    }

    public static Integer binarySearchRight(Integer[] nums, Integer target) {
        Integer left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid].equals(target)) {
                left = mid + 1;
            }
        }
        if (right >= nums.length || nums[right] != target)
            return -1;
        return right;
    }

}
