/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);
        subsets(result, candidates, target, new ArrayList<>(), 0);

        return result;
    }

    private void subsets(List<List<Integer>> result, int[] candidates, 
        int target, List<Integer> temp, int start) {

        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            temp.add(candidates[i]);
            subsets(result, candidates, target - candidates[i], temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
// @lc code=end

// Time: O(2^n)