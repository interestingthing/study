package pers.cjg.backtrack;

import java.util.Arrays;

/**
 * @Author: chenjingang@guazi.com  2020-10-23 17:29
 */
public class Backtrack {

    static Integer[] nums = {1, 2, 3};

    public static void backTrack(Integer[] nums, Integer index) {

        if (index == 3) {
            System.out.println(Arrays.asList(nums));
            return;
        }

        for (int i = index; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
                swap(nums, i, index);
                backTrack(nums, index + 1);
                swap(nums, i, index);
//            }
        }
    }

    private static void swap(Integer[] nums, int i, int j) {
        if (i==j)
            return;
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }

    public static void main(String[] args) {
        swap(nums,0,1);
        System.out.println(Arrays.asList(nums));

        backTrack(nums, 0);
    }
}
