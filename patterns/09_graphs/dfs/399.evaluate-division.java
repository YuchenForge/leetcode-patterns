/*
 * @lc app=leetcode id=399 lang=java
 *
 * [399] Evaluate Division
 */

import java.util.*;

// @lc code=start
class Solution {
    public double[] calcEquation(List<List<String>> equations, 
        double[] values, List<List<String>> queries) {

        // Build graph 
        HashMap<String, HashMap<String, Double>> graph = buildGraph(equations, values);
        double[] results = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0);
            String divisor = query.get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                results[i] = -1.0;
            } else {
                results[i] = dfs(dividend, divisor, new HashSet<>(), graph);
            }
        }

        return results;


    }

    // Time: O(E)
    // Space: O(V + E) where V = # of unique variables
    private HashMap<String, HashMap<String, Double>> buildGraph(
        List<List<String>> equations, double[] values) {
        
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0);
            String divisor = equation.get(1);
            double value = values[i];

            graph.putIfAbsent(dividend, new HashMap<>());
            graph.putIfAbsent(divisor, new HashMap<>());
            graph.get(dividend).put(divisor, value);
            graph.get(divisor).put(dividend, 1.0 / value);
        }
        
        return graph;
    }

    // Time: O(V + E) per query
    // Total time for Q queries: O(Q(V + E))
    // Space: O(V) per query
    private double dfs(String start, String end, HashSet<String> visited,
        HashMap<String, HashMap<String, Double>> graph) {

        if (start.equals(end)) return 1.0;

        visited.add(start);

        HashMap<String, Double> neighbors = graph.get(start);

        for (String neighbor : neighbors.keySet()) {
            if (visited.contains(neighbor)) {
                continue;
            }

            // dfs(start, end) returns the value of start / end
            double result = dfs(neighbor, end, visited, graph);
            
            if (result != -1.0) {
                double weight = neighbors.get(neighbor);
                return result * weight;
            }
        }

        return -1.0;
    }
}
// @lc code=end

// a/b = 2 is same as a --2--> b
//                    b -1/2-> a


// Overall Time: O(E + Q(V + E))
// Overall Space: O(V + E)