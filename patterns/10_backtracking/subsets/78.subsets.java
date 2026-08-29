/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(result, nums, new ArrayList<>(), 0);

        return result;
    }

    private void backtrack(List<List<Integer>> result, int[] nums,
        List<Integer> temp, int start) {

        result.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(result, nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
// @lc code=end

// Time: O(n * 2^n)
// Space: O(n)