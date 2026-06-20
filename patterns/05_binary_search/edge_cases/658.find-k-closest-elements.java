/*
 * @lc app=leetcode id=658 lang=java
 *
 * [658] Find K Closest Elements
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;

        List<Integer> res = new LinkedList<>();

        while (hi - lo >= k) {
            if (Math.abs(arr[lo] - x) <= Math.abs(arr[hi] - x)) {
                hi--;
            } else {
                lo++;
            }
        }

        for (int i = lo; i <= hi; i++) {
            res.add(arr[i]);
        }

        return res;
    }
}

// Time: O(n)
// Space: O(k)
// @lc code=end

