/*
 * @lc app=leetcode id=1971 lang=java
 *
 * [1971] Find if Path Exists in Graph
 */

import java.util.*;

// @lc code=start
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Build adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            // bi-directional
            graph[u].add(v);
            graph[v].add(u);
        }

        // DFS to find path
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>(); 

        stack.push(source);
        visited[source] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current == destination) {
                return true;
            }

            for (int neighbor: graph[current]) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return false;
    }
}
// @lc code=end

// Time: O(n + e)
// Space: O(n + e)