/*
 * @lc app=leetcode id=216 lang=java
 *
 * [216] Combination Sum III
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        int[] candidates = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        subsets(result, candidates, n, k, new ArrayList<>(), 0);

        return result;
    }

    private void subsets(List<List<Integer>> result, int[] candidates, 
        int target, int k, List<Integer> temp, int start) {

        if (target == 0 && temp.size() == k) {
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

            if (temp.size() > k + 1) {
                break;
            }

            temp.add(candidates[i]);
            subsets(result, candidates, target - candidates[i], k, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
// @lc code=end

// Time: O(2^9) = O(1)
// Space: O(k)