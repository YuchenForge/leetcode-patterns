/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        backtrack(result, nums, new ArrayList<>(), 0);

        return result;
    }

    private void backtrack(List<List<Integer>> result, int[] nums,
        List<Integer> temp, int start) {

        result.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            temp.add(nums[i]);
            backtrack(result, nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
// @lc code=end

// Time: O(n * 2^n)
// Space: O(n)