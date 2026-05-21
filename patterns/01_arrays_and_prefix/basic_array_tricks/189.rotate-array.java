/*
 * @lc app=leetcode id=189 lang=java
 *
 * [189] Rotate Array
 */

// @lc code=start
class Solution {
    /**
     * 7654321 reverse 0:n-1
     * 5674321 reverse 0:k-1
     * 5671234 reverse k:n-1
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        // rotate n times is the same as rotate 0 times
        k = k % n;

        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
        
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

// Time complexity: O(n) because each element reversed at most twice
// Space complexity: O(1)  
// @lc code=end



