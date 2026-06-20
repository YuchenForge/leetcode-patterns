/*
 * @lc app=leetcode id=787 lang=java
 *
 * [787] Cheapest Flights Within K Stops
 */

import java.util.*;

// @lc code=start
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Build adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        
        // State: {cost, node, stops_used}
        PriorityQueue<int[]> heap = 
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        heap.offer(new int[]{0, src, 0});
        
        // Track best cost to reach node with exactly s stops
        // best[node][stops] = min cost
        int[][] best = new int[n][k + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(best[i], Integer.MAX_VALUE);
        }
        best[src][0] = 0;
        
        int answer = Integer.MAX_VALUE;
        
        while (!heap.isEmpty()) {
            int[] curr = heap.poll();
            int cost = curr[0];
            int node = curr[1];
            int stops = curr[2];
            
            // Reached destination
            if (node == dst) {
                answer = Math.min(answer, cost);
                continue;
            }
            
            // Stop if exceeded k stops or cost is worse
            if (stops > k || cost > best[node][stops]) {
                continue;
            }
            
            // Explore neighbors
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int price = neighbor[1];
                int newCost = cost + price;
                int newStops = stops + 1;
                
                // Update if found cheaper path with fewer/same stops
                if (newCost < best[nextNode][newStops]) {
                    best[nextNode][newStops] = newCost;
                    heap.offer(new int[]{newCost, nextNode, newStops});
                }
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}


// Time: O(E k log(nk))
// Space: O(nk + E)
// @lc code=end


class Solution2 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        // k stops = k+1 edges max, relax k+1 times
        for (int stop = 0; stop <= k; stop++) {
            int[] temp = dist.clone();
            
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];
                
                if (dist[u] != Integer.MAX_VALUE) {
                    temp[v] = Math.min(temp[v], dist[u] + price);
                }
            }
            
            dist = temp;
        }
        
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
