
/*
 * @lc app=leetcode id=303 lang=java
 *
 * [303] Range Sum Query - Immutable
 */

// @lc code=start
class NumArray {

    private final int[] prefix;

    public NumArray(int[] nums) {
        // prefix sum
        int n = nums.length;
        prefix = new int[n+1];
        prefix[0] = 0;

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }

    }
    
    public int sumRange(int left, int right) {
        // sum(l,r) = prefix[r] - prefix[l-1]
        return prefix[right+1] - prefix[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */

// Time: O(n)
// Space: O(n)
// @lc code=end

