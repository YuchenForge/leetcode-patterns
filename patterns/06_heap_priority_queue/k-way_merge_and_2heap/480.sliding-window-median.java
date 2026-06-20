/*
 * @lc app=leetcode id=480 lang=java
 *
 * [480] Sliding Window Median
 */

import java.util.*;

// @lc code=start
class Solution {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    HashMap<Integer, Integer> removed;

    private int leftSize;
    private int rightSize;

    public double[] medianSlidingWindow(int[] nums, int k) {
        init();

        int n = nums.length;
        double[] res = new double[n - k + 1];

        int index = 0;

        for (int i = 0; i < n; i++) {

            addNum(nums[i]);

            if (i >= k) {
                deleteNum(nums[i - k]);
            }

            if (i >= k - 1) {
                res[index] = findMedian(k);
                index++;
            }
        }

        return res;
    }

    private void init() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();

        removed = new HashMap<>();

        leftSize = 0;
        rightSize = 0;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
            leftSize++;
            return;
        }

        if (num > maxHeap.peek()) {
            minHeap.offer(num);
            rightSize++;
        } else {
            maxHeap.offer(num);
            leftSize++;
        }

        rebalance();
    }

    private void deleteNum(int num) {
        removed.put(num, removed.getOrDefault(num, 0) + 1);

        if (num <= maxHeap.peek()) {
            leftSize--;

            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            rightSize--;

            if (!minHeap.isEmpty() && num == minHeap.peek()) {
                prune(minHeap);
            }
        }

        rebalance();
    }

    private void rebalance() {
        if (leftSize - rightSize > 1) {
            minHeap.offer(maxHeap.poll());

            leftSize--;
            rightSize++;

            prune(maxHeap);
        } else if (rightSize > leftSize) {
            maxHeap.offer(minHeap.poll());

            leftSize++;
            rightSize--;

            prune(minHeap);
        } 
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();

            if (!removed.containsKey(num)) {
                break;
            } else {
                int count = removed.get(num);
                
                if (count == 1) {
                    removed.remove(num);
                } else {
                    removed.put(num, count - 1);
                }
            }

            heap.poll();
        }
    }

    private double findMedian(int k) {
        if (k % 2 == 0) {
            return ((long) maxHeap.peek() + (long) minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}

// Time: O(n log k)
// Space: O(k)
// @lc code=end
