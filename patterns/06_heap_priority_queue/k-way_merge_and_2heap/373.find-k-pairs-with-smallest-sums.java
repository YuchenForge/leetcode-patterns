/*
 * @lc app=leetcode id=373 lang=java
 *
 * [373] Find K Pairs with Smallest Sums
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // [sum, i, j] and order by sum
        PriorityQueue<int[]> minHeap = 
            new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // pair each nums1[i] with nums2[0]
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[] {nums1[i] + nums2[0], i, 0});
        }

        while (!minHeap.isEmpty() && result.size() < k) {
            // grab smallest sum
            int[] curr = minHeap.poll();
            int i = curr[1];
            int j = curr[2];
            
            result.add(Arrays.asList(nums1[i], nums2[j]));
            
            if (j + 1 < nums2.length) {
                // add next element on the right of num2
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }
        
        return result;

    }
}

// Time: O(k log k)
// Space: O(k)
// @lc code=end

