/*
 * @lc app=leetcode id=39 lang=java
 *
 * [39] Combination Sum
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        subsets(result, candidates, target, new ArrayList<>(), 0);

        return result;
    }

    private void subsets(List<List<Integer>> result, int[] candidates, 
        int target, List<Integer> temp, int start) {

        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            subsets(result, candidates, target - candidates[i], temp, i);
            temp.remove(temp.size() - 1);
        }
    }
}
// @lc code=end

// Time: O(n^T)