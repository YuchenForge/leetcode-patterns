/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);

        backtrack(result, new ArrayList<>(), nums, used);

        return result;
    }

    private void backtrack(List<List<Integer>> result, 
        List<Integer> temp, int[] nums, boolean[] used) {

        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // skip duplicates 
            if ((i > 0 && nums[i] == nums[i-1]) && !used[i-1]) {
                continue;
            }

            used[i] = true;

            temp.add(nums[i]);
            backtrack(result, temp, nums, used);
            temp.remove(temp.size() - 1);

            used[i] = false;
        }
    }
}
// @lc code=end

// Time: O(n · n!)
// Space: O(n)