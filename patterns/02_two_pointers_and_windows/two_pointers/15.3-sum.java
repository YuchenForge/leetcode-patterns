/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 */

import java.util.Arrays;
import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        // sort the array
        Arrays.sort(nums);

        List<List<Integer>> output = new ArrayList<>();

        for (int i = 0; i < nums.length-2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            // Skip the same number for the 'fixed' pointer
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            while (left < right) {
                // Calculate current state
                int totalSum = nums[i] + nums[left] + nums[right];

                if (totalSum == 0) {
                    List<Integer> arr = new ArrayList(Arrays.asList(nums[left], nums[right], nums[i]));
                    output.add(arr);

                    // Move pointers and skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (totalSum < 0) {
                    left++; // Need a larger value
                } else {
                    right--; // Need a smaller value
                }
            }
        }

        return output;
    }
}
// @lc code=end

