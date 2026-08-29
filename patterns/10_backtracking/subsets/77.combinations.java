/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        subsets(result, n, k, new ArrayList<>(), 1);

        return result;
    }

    private void subsets(List<List<Integer>> result,  
        int n, int k, List<Integer> temp, int start) {

        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i <= n; i++) {
            temp.add(i);
            subsets(result, n, k, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
// @lc code=end

// Time: O(C(n,k) × k)
// Space: O(k)