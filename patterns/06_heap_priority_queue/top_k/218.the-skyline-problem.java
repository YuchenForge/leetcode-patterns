/*
 * @lc app=leetcode id=218 lang=java
 *
 * [218] The Skyline Problem
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // build events
        List<int[]> events = new ArrayList<>();
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]});  // start: negative height
            events.add(new int[]{b[1],  b[2]});  // end: positive height
        }

        // sort events first by x then by height in increasing order
        events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        // max heap to track highest building at x
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        HashMap<Integer, Integer> removed = new HashMap<>();
        heap.offer(0);  // ground level

        List<List<Integer>> result = new ArrayList<>();
        int prevMax = 0;

        for (int[] event : events) {
            int x = event[0];
            int h = event[1];

            if (h < 0) {
                heap.offer(-h);
            } else {
                removed.put(h, removed.getOrDefault(h, 0) + 1);
            }

            // lazy deletion at O(log n)
            while(!heap.isEmpty() && removed.getOrDefault(heap.peek(), 0) > 0) {
                int top = heap.poll();
                removed.put(top, removed.get(top) - 1);

                if (removed.get(top) == 0) {
                    removed.remove(top);
                }
            }

            int currMax = heap.peek();
            if (currMax != prevMax) {
                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
}

// Time: O(B log B)
// Space: O(B)
// @lc code=end

