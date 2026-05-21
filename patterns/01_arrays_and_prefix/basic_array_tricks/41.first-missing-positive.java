/*
 * @lc app=leetcode id=41 lang=java
 *
 * [41] First Missing Positive
 */

// @lc code=start
class Solution {
    public int firstMissingPositive(int[] nums) {
        // if array has length n, ans in range [1, n+1]
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1 && 
                   nums[i] <= n && 
                   // if nums[x-1] != x
                   nums[nums[i] - 1] != nums[i]) {

                // swap with correct index
                int index = nums[i]-1;
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }
        
        return n+1;
    }
}

// Time: O(n)
// Space: O(1)
// @lc code=end

