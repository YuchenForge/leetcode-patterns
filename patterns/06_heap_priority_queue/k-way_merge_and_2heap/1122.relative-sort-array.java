/*
 * @lc app=leetcode id=1122 lang=java
 *
 * [1122] Relative Sort Array
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num: arr2) {
            map.put(num, 0);
        }

        for (int num: arr1) {
            if (map.containsKey(num)) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else {
                minHeap.offer(num);
            }
        }

        int i = 0;

        for (int j = 0; j < arr2.length; j++) {
            if (map.containsKey(arr2[j])) {
                for (int k = map.get(arr2[j]); k > 0; k--) {
                    arr1[i++] = arr2[j];
                }
            }
        }

        while (!minHeap.isEmpty()) {
            arr1[i++] = minHeap.poll();
        }

        return arr1;
    }
}

// Time: O(m + u log u)
// Space: O(m)
// @lc code=end

