/*
 * @lc app=leetcode id=785 lang=java
 *
 * [785] Is Graph Bipartite?
 */

// @lc code=start
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }
        
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!dfs(graph, color, i, color[i])) {
                    return false;  // Found a conflict
                }
            }
        }
        
        return true;
    }
    
    private boolean dfs(int[][] graph, int[] color, int node, int currentColor) {
        color[node] = currentColor;
        
        for (int neighbor : graph[node]) {
            int nextColor = 1 - currentColor;  // Opposite color

            if (color[neighbor] == -1) {
                // Unvisited:
                if (!dfs(graph, color, neighbor, nextColor)) {
                    return false;
                }
            } else if (color[neighbor] != nextColor) {
                // wrong color
                return false;
            }
        }

        return true; 
    }
}
// @lc code=end


// Time Complexity: O(n + e)
// Space Complexity: O(n)
