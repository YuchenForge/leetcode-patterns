/*
 * @lc app=leetcode id=692 lang=java
 *
 * [692] Top K Frequent Words
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> freq = new HashMap<>();

        // Count frequency
        for (String word : words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        // Min heap
        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> {
                int freqCompare = freq.get(a) - freq.get(b);

                // Smaller frequency first
                if (freqCompare != 0) {
                    return freqCompare;
                }

                return b.compareTo(a);
            }
        );

        // Keep only top k
        for (String word : freq.keySet()) {
            pq.offer(word);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }

        Collections.reverse(result);

        return result;
    }
}

// Time: O(N + U log k)
// Space: O(U)
// @lc code=end