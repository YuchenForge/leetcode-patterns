/*
 * @lc app=leetcode id=1514 lang=java
 *
 * [1514] Path with Maximum Probability
 */

import java.util.*;

// @lc code=start
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add undirected edges with probabilities
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            
            // Both directions (undirected)
            graph.get(u).add(new int[]{v, i});
            graph.get(v).add(new int[]{u, i});
        }

        // Track maximum probability to reach each node
        double[] maxProb = new double[n];
        maxProb[start_node] = 1.0;

         // Max heap: {probability, node}
        PriorityQueue<double[]> maxHeap = 
            new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        maxHeap.offer(new double[]{1.0, start_node});

        while (!maxHeap.isEmpty()) {
            double[] curr = maxHeap.poll();
            double prob = curr[0];
            int u = (int) curr[1];

            if (prob < maxProb[u]) {
                continue;
            }

            // Explore neighbors
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                int edgeIdx = neighbor[1];
                double edgeProb = succProb[edgeIdx];
                
                // Calculate new probability
                double newProb = prob * edgeProb;
                
                // Update if found better probability
                if (newProb > maxProb[v]) {
                    maxProb[v] = newProb;
                    maxHeap.offer(new double[]{newProb, v});
                }
            }
        }

        return maxProb[end_node];

    }
}

// Time: O(E log n)
// Space: O(n + E)
// @lc code=end

