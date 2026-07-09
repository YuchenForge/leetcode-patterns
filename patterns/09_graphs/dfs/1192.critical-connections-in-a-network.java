/*
 * @lc app=leetcode id=1192 lang=java
 *
 * [1192] Critical Connections in a Network
 */

import java.util.*;

// @lc code=start
class Solution {

    private int[] disc;
    private int[] low;
    private int time;

    private boolean[] visited;
    private List<List<Integer>> criticals;
    private List<Integer>[] graph;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        disc = new int[n];
        low = new int[n];
        time = 0;

        visited = new boolean[n];
        criticals = new ArrayList<>();

        // build adjacency list
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < connections.size(); i++) {
            int a = connections.get(i).get(0);
            int b = connections.get(i).get(1);
            
            // Both directions (undirected)
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(0, -1);

        return criticals;
    }

    private void dfs(int node, int parent) {
        visited[node] = true;
        disc[node] = time;
        low[node] = time;
        time++;

        for (int neighbor : graph[node]) {
            if (neighbor == parent) {
                continue;
            }

            if (!visited[neighbor]) {
                dfs(neighbor, node);
                low[node] = Math.min(low[node], low[neighbor]);

                if (low[neighbor] > disc[node]) {
                    criticals.add(new ArrayList(Arrays.asList(node, neighbor)));
                }
            }

            if (visited[neighbor]) {
                low[node] = Math.min(low[node], disc[neighbor]);
            }
        
        }
    }
}
// @lc code=end

/**
 * Brute Force: For each edge, remove it and check if the graph 
 * is still connected, if yes, then it's a critical edge
 * 
 * Optimized: Going from parent to child, can child return to parent
 * without using this edge, if no, then it's a critical edge
 * 
 * Edge (parent -> child) is a critical connection if:
 *    low[child] > disc[parent]
 * 
 * disc[node] = When did I first discover this node during DFS?
 * low[node] = What is the earliest discovered node that I (or 
 * any node in my DFS subtree) can reach without going through 
 * my parent?
 */