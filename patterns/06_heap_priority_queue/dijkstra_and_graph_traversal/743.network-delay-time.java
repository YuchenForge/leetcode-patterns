/*
 * @lc app=leetcode id=743 lang=java
 *
 * [743] Network Delay Time
 */

import java.util.*;

// @lc code=start
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Directed edges [u, v, weight]
        for (int[] edge: times) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
        }

        // Track shortest distance
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;

        // Prioirity queue {distance, node}, shortest dist on top
        PriorityQueue<int[]> heap = 
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        heap.offer(new int[]{0, k});

        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int d = curr[0];
            int u = curr[1];

            // Skip if no shorter distance found
            if (d > distance[u]) {
                continue;
            }

            // Explore neighbors
            for (int[] neighbors: graph.get(u)) {
                int v = neighbors[0];
                int weight = neighbors[1];

                // Update if shorter path found
                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    heap.offer(new int[]{distance[v], v});
                }
            }
        }

        // Find max in distance
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1; // Unreachable node
            }
            maxTime = Math.max(maxTime, distance[i]);
        }
        
        return maxTime;

    }
}

// Time: O(E log n)
// Space: O(n + E)
// @lc code=end

